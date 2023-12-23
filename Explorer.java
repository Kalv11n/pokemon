import java.util.List;
import java.util.Scanner;

import entity.Attack;
import entity.Monster;
import entity.Player;
import entity.state.FloodedGroundState;
import instanciator.ObjectInstanciator;
import reader.InputReader;

public class Explorer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m"; //player
    public static final String ANSI_GREEN = "\u001B[32m";//indication
    public static final String ANSI_YELLOW = "\u001B[33m";//attack
    public static final String ANSI_BLUE = "\u001B[34m";//monster
    public static final String ANSI_PURPLE = "\u001B[35m";//return
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";//object


    private Scanner sc;
    private InputReader reader;
    private Player[] players = {new Player(), new Player()};
    // private FloodedState flooded;

    public Explorer() {

    }
    
    public void run(){
        this.reader = new InputReader();
        this.sc  = new Scanner(System.in);

        this.init();
        this.play();

        this.sc.close();
    }

    private void init(){
        clearScreen();
        String choice;

        // Instanciate object
        ObjectInstanciator.instanciate();

        // Instanciate new FloodedGroundState 
        new FloodedGroundState();

        // Instanciate Attack with hands for all
        // new Attack().setName("Mains");

        // Players selection
        for(Player player : players){
            System.out.println("\n" + ANSI_RED +"======== JOUEUR " + player.getId() + " ========"+ ANSI_RESET);
            System.out.println("== CHOISISSEZ 3 POKEMONS ==");

            for(int i=0; i < 3; i++){
                this.printMonsters();

                // Select monster
                do {
                    System.out.print("\n" + ANSI_GREEN + "Veuillez saisir un id de monstre:" + ANSI_RESET + " ");
                    choice = sc.nextLine();
                    
                } while(!this.reader.checkInterval(1, Monster.findAll().size(), choice));    
                
                // Add monster to player monsters
                Monster monster = Monster.find(Integer.parseInt(choice) - 1);
                player.addMonster(monster);
                
                System.out.println(ANSI_PURPLE + "-> " + monster.getName() + ANSI_RESET +"\n");

                // Remove monster from list
                Monster.remove(monster);

                // Print attacks by type
                List<Attack> attacks = Attack.findByType(monster.getType());
                
                for(int j=0; j < attacks.size(); j++){
                    System.out.println("[" + ANSI_RED +  (j + 1) + ANSI_RESET + "]\t" + attacks.get(j) + ANSI_RESET);
                }

                for(int j=0; j < 4; j++){
                    // Select attacks
                    do {
                        System.out.print("\n  " + ANSI_GREEN + "Veuillez saisir un id d'attaque:" + ANSI_RESET + " ");
                        choice = sc.nextLine();

                    } while(!this.reader.checkInterval(1, attacks.size(), choice));

                    // Add attack to monster
                    Attack attack = attacks.get(Integer.parseInt(choice) - 1);
                    monster.setAttackInCollection(attack, j);

                    System.out.println("  " + ANSI_PURPLE + "-> " + attack.getName() + ANSI_RESET);
                }
                clearScreen();
            }
            System.out.println("\n== CHOISISSEZ 5 OBJETS ==\n");
            for(int w=0; w < 5; w++) {
                printObjects();
                // Select object
                do {
                    System.out.print("\n" + ANSI_GREEN + "Veuillez saisir un id d'objet: " + ANSI_RESET);
                    choice = sc.nextLine();
                    
                } while(!this.reader.checkInterval(1, 4, choice));    
                
                // Add object to player objects
                entity.objects.Object object = entity.objects.Object.find(Integer.parseInt(choice) - 1);
                player.addObject(object);
                System.out.println(ANSI_PURPLE + "-> " + object.getName() + ANSI_RESET +"\n");
            }

            clearScreen();
            
            // Show deck
            System.out.println(ANSI_GREEN + "\n\nTon deck:" + ANSI_RESET + "\n");

            Monster[] deckMonsters = player.getPlayerMonsters();
            for(int i=0; i < deckMonsters.length; i++){
                System.out.println(ANSI_CYAN + "-> " + deckMonsters[i].toStringWithAttacks() + ANSI_RESET);
            }
            System.out.print(ANSI_ORANGE + "\nObjets: [");
            Object[] deckObjects = player.getPlayerObjects();
            for(int i=0; i < deckObjects.length - 1; i++){
                System.out.print(ANSI_ORANGE + deckObjects[i] + ", ");
            }
            System.out.print(ANSI_ORANGE + deckObjects[deckObjects.length - 1]);
            System.out.print(ANSI_ORANGE + "]\n" + ANSI_RESET );

            this.sc.nextLine();
            clearScreen();
        }   

        System.out.println(ANSI_PURPLE + "\nLe terrain est neutre.\n");
        System.out.println(ANSI_RED + "======== LE JEU VA COMMENCER =======" + ANSI_RESET);

        // Init monsters by default
        players[0].setInUseMonster(players[0].getPlayerMonsters()[0]);
        players[1].setInUseMonster(players[1].getPlayerMonsters()[0]);

        this.sc.nextLine();
    }

    private void play() {
        // Main loop
        do {
            // Player interactions
            for (Player player : players) {
                this.interact(player);
            }

            clearScreen();

            // Player distribution
            Player[] playersOrder;
            if(players[0].getInUseMonster().getSpeed() >= players[1].getInUseMonster().getSpeed()){
                System.out.println("\n" + ANSI_RED +"======== JOUEUR " + players[0].getId() + " PREND L'AVANTAGE ========" + ANSI_RESET);
                playersOrder = new Player[]{players[0], players[1]};
            } else {
                System.out.println("\n" + ANSI_RED +"======== JOUEUR " + players[1].getId() + " PREND L'AVANTAGE ========" + ANSI_RESET);
                playersOrder = new Player[]{players[1], players[0]};
            }

            for (Player player : playersOrder) {
                if (player.getAllowAttacking() && player.getInUseMonster().getHp() != 0) {
                    // Player monster
                    Player currentPlayer = player;
                    Monster currentMonster = currentPlayer.getInUseMonster();

                    // Ennemy and ennemy monster
                    Player ennemyPlayer = players[1];
                    if (currentPlayer.getId() == 2) {
                        ennemyPlayer = players[0];
                    }
                    Monster ennemyMonster = ennemyPlayer.getInUseMonster();

                    // Attack Player Monster
                    System.out.println("\n" + ANSI_BLUE + currentMonster.getName() + ANSI_RESET + " attaque " + ANSI_BLUE + ennemyMonster.getName() + ANSI_RESET + " avec " 
                        + ANSI_YELLOW + currentMonster.getInUseAttack().getName() + ANSI_RESET + " !");
                    
                    currentMonster.attack(ennemyMonster);

                    // Printer
                    if (currentMonster.hasAdvantage(ennemyMonster)) {
                        System.out.println("C'est très efficace !");
                    } else if (currentMonster.hasWeakness(ennemyMonster)) {
                        System.out.println("C'est n'est pas très efficace !");
                    }

                    System.out.println(ANSI_BLUE + ennemyMonster.getName() + ANSI_RESET + " (PV restants : " + ANSI_GREEN + ennemyMonster.getHp() + ANSI_RESET + ")");
                }
            }

            this.sc.nextLine();
            clearScreen();

            // Flooded instruction
            if (FloodedGroundState.flooded) {
                System.out.println(ANSI_PURPLE + "Le terrain est innondé." + ANSI_RESET);
                FloodedGroundState.decrementTurn();
            }

        } while (!this.haveWinner());
    }

    //---- Others
    public void interact(Player player) {
        System.out.println("\n" + ANSI_RED +"======== JOUEUR " + player.getId() + " ========"+ ANSI_RESET);
        System.out.println("\nMonstre actuel : " + player.getInUseMonster().toString() + " Etat actuel : " + player.getInUseMonster().getCurrentState());  
        String choice;

        // Change monster
        this.changeMonster(player);
        
        if (player.getAllowAttacking()) {
            // Not changing monster, maybe use an object
            this.useObject(player);

            Monster monster = player.getInUseMonster();

            // Attacking
            int i = 0;
            for (; i<4; i++) {
                System.out.println("[" + ANSI_RED + (i + 1) + ANSI_RESET + "] " + ANSI_CYAN + monster.getAttacks()[i] + ANSI_RESET);
            }

            // Check ability to attack
            do {
                // Check input
                do {
                    System.out.print("\n" + ANSI_GREEN + "Veuillez saisir un id d'attaque: " + ANSI_RESET);
                    choice = sc.nextLine();

                } while(!this.reader.checkInterval(1, (i + 1), choice));

                monster.setInUseAttack(monster.getAttacks()[Integer.parseInt(choice) - 1]);

            } while(!monster.getInUseAttack().canUse());
        }

        clearScreen();
    }

    public void changeMonster(Player player) {
        String availableChoices = "ON";
        String choice = "O"; // Default to "O" to force changing if Monster is KO

        // If monster HP > 0, available choice oh changing monster
        if (player.getInUseMonster().getHp() != 0) {
            do {
                System.out.print("\n" + ANSI_GREEN + "CHANGER DE MONSTRE" + ANSI_RESET + ": [" + ANSI_RED + "O" + ANSI_RESET +"]: oui "+"[" + ANSI_RED + "N"  + ANSI_RESET +"]: non ");
                choice = sc.nextLine();

            } while(!this.reader.checkChoice(availableChoices, choice));

            if(choice.toUpperCase().equals("O")){
                player.setAllowAttacking(false);
            } else {
                player.setAllowAttacking(true);
            }
        } else {
            System.out.print("\n" + ANSI_RED + player.getInUseMonster().getName() + " est KO, vous devez changer de monstre." + ANSI_RESET + "\n");
            player.setAllowAttacking(true);
        }
        
        // Change monster if is choiced or if monster HP = 0
        if(choice.toUpperCase().equals("O")){
            boolean changeApproved; // If selected another KO monster

            do {
                changeApproved = true;

                // Select new monster
                int i = 0;
                for(; i < player.getPlayerMonsters().length; i++){
                    System.out.println("[" + ANSI_RED + (i + 1) + ANSI_RESET + "] " + ANSI_CYAN + player.getPlayerMonsters()[i] + ANSI_RESET);
                }

                // Check input
                do {
                    System.out.print("\n" + ANSI_GREEN + "Veuillez saisir un id de monstre: " + ANSI_RESET);
                    choice = sc.nextLine();

                } while(!this.reader.checkInterval(1, i, choice));

                Monster monster = player.getPlayerMonsters()[Integer.parseInt(choice) - 1];

                // Check choiced monster is available
                if(monster.getHp() == 0){
                    System.out.println("\n" + ANSI_RED + monster.getName() + " est KO !" +  ANSI_RESET);
                    changeApproved = false;
                    continue;
                }

                // Check flooder monster
                if (FloodedGroundState.monster == player.getInUseMonster()) {
                    FloodedGroundState.killFlood();
                    System.out.println(ANSI_PURPLE + "Le terrain n'est plus innondé." + ANSI_RESET);
                }

                // Update in use monster
                System.out.println("\n" + ANSI_GREEN + monster.getName() + " ! A toi de jouer !" + ANSI_RESET);
                player.setInUseMonster(monster);

                this.sc.nextLine();

            } while (!changeApproved);
        }
    }

    public void useObject(Player player) {
        String availableChoices = "ON";
        String choice;

        do {
            System.out.print("\n" + ANSI_GREEN +"UTILISER UN OBJET"+ ANSI_RESET +": [" + ANSI_RED + "O" + ANSI_RESET +"]: oui "+"[" + ANSI_RED + "N"  + ANSI_RESET +"]: non ");
            choice = sc.nextLine();
            System.out.print("\n");
        } while(!this.reader.checkChoice(availableChoices, choice));

        // Use object
        if(choice.toUpperCase().equals("O")){
            int i = 0;
            for(; i < player.getPlayerObjects().length; i++){
                System.out.println("[" + ANSI_RED + (i + 1) + ANSI_RESET + "] " + ANSI_ORANGE + player.getPlayerObjects()[i] + ANSI_RESET);
            }
            // Check input
            do {
                System.out.print("\n" + ANSI_GREEN + "Veuillez saisir un id d'objet: " + ANSI_RESET);
                choice = sc.nextLine();

            } while(!this.reader.checkInterval(1, (i + 1), choice));

            entity.objects.Object object = (entity.objects.Object) player.getPlayerObjects()[Integer.parseInt(choice) - 1];
            System.out.println("\n" + ANSI_PURPLE + "Joueur " + player.getId() + " utilise l'objet : " + object.getName() + ANSI_RESET + "\n");
            object.useObject(player.getInUseMonster());
            player.removeObject(Integer.parseInt(choice) - 1);
        }
    }

    public boolean haveWinner() {
        if (this.players[0].allMonstersKO()) {
            System.out.println("\n" + ANSI_RED + "Joueur " + players[0].getId() + ", tous les monstres sont KO." + ANSI_RESET);
            System.out.println("\n" + ANSI_GREEN + "Joueur " + players[1].getId() + " a gagné !!!" + ANSI_RESET + "\n");
            return true;
        } else if(this.players[1].allMonstersKO()) {
            System.out.println("\n" + ANSI_RED +"Joueur " + players[1].getId() + ", tous les monstres sont KO." + ANSI_RESET + "\n");
            System.out.println("\n" + ANSI_GREEN +"Joueur " + players[0].getId() + " à gagné !!!" + ANSI_RESET + "\n");
            return true;
        }

        return false;
    }

    public void printMonsters() {
        System.out.println();
        
        for(int i=1; i <= Monster.findAll().size(); i++){
            System.out.println("[" + ANSI_RED + i + ANSI_RESET + "]\t" + Monster.find((i - 1)));
        }
    }

    public void printObjects() {
        for(int i=1; i <= entity.objects.Object.objects.size(); i++){
            System.out.println("[" + ANSI_RED + i + ANSI_RESET + "]\t" + entity.objects.Object.find((i - 1)));
        }
    }

    public static void clearScreen() {  
        // System.out.print("\033[H\033[2J");  
        // System.out.flush();  
    } 
}
