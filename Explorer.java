import java.util.List;
import java.util.Scanner;

import entity.Attack;
import entity.Monster;
import entity.Player;
import instanciator.ObjectInstanciator;
import reader.InputReader;

public class Explorer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";


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
        String choice;

        // Instanciate object
        ObjectInstanciator.instanciate();

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
                Monster monster = Monster.find(Integer.parseInt(choice));
                player.addMonster(monster);
                
                System.out.println(ANSI_PURPLE + "-> " + monster.getName() + ANSI_RESET +"\n");

                // Remove monster from list
                Monster.remove(monster);

                // Print attacks by type
                List<Attack> attacks = Attack.findByType(monster.getType());
                
                for(int j=0; j < attacks.size(); j++){
                    System.out.println("[" + ANSI_RED +  (j + 1) + ANSI_RESET + "]\t" + attacks.get(j));
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
            }
            System.out.println("\n== CHOISISSEZ 5 OBJETS ==");
            for(int w=0; w < 5; w++) {
                printObjects();
                // Select object
                do {
                    System.out.print("\n" + ANSI_GREEN + "Veuillez saisir un id d'objet:" + ANSI_RESET + " ");
                    choice = sc.nextLine();
                    
                } while(!this.reader.checkInterval(1, 4, choice));    
                
                // Add object to player objects
                entity.objects.Object object = entity.objects.Object.find(Integer.parseInt(choice) - 1);
                player.addObject(object);
                System.out.println(ANSI_PURPLE + "-> " + object.getName() + ANSI_RESET +"\n");
            }

            
            
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
            System.out.print("]\n" + ANSI_RESET );
        }   

        System.out.println(ANSI_PURPLE + "\nLe terrain est neutre.\n");
        System.out.println(ANSI_RED + "======== LE JEU VA COMMENCER =======" + ANSI_RESET);

        // Init monsters by default
        players[0].setInUseMonster(players[0].getPlayerMonsters()[0]);
        players[1].setInUseMonster(players[1].getPlayerMonsters()[0]);
    }

    private void play() {
        // Main loop
        do {
            // Player interactions
            for (Player player : players) {
                updateAction(player);
            }

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
                if (player.getAllowAttacking()) {
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
                    System.out.println("\n" + currentMonster.getName() + " attaque " + ennemyMonster.getName() + " avec " 
                        + currentMonster.getInUseAttack().getName() + " !");
                    currentMonster.attack(ennemyMonster);

                    System.out.println("C'est très efficace !");
                    System.out.println("Dégats infligés : " + ennemyMonster.getDammageReceived());
                    System.out.println(ennemyMonster.getName() + " (PV restants : " + ennemyMonster.getHp() + ")");
                } else {
                    player.setAllowAttacking(true);
                }
            }

        } while (!this.haveWinner());
    }

    //---- Others
    public void updateAction(Player player){
        System.out.println("\n" + ANSI_RED +"======== JOUEUR " + player.getId() + " ========"+ ANSI_RESET);
        System.out.println("Monstre actuel : " + player.getInUseMonster().toString());  
        boolean allowAttacking;

        // Check monster is KO
        if (player.getInUseMonster().getHp() == 0) {
            allowAttacking = this.changeMonster(player);
        } else { // Monster is available to fight
            allowAttacking = this.interact(player);
        }    

        // Allow player to attack
        if (allowAttacking) {
            player.setAllowAttacking(true);
        } else {
            player.setAllowAttacking(false);
        }
    }

    public boolean interact(Player player) {
        String choice;

        // Change monster
        if (!this.changeMonster(player)) {
            // Not changing monster, maybe use an object
            this.useObject(player);

            Monster monster = player.getInUseMonster();

            // Attacking
            int i = 0;
            for (; i<4; i++) {
                System.out.println("[" + ANSI_RED + (i + 1) + ANSI_RESET + "] " + ANSI_CYAN + monster.getAttacks()[i] + ANSI_RESET);
            }

            // Check input
            do {
                System.out.print("\n" + ANSI_GREEN + "Veuillez saisir un id d'attaque:" + ANSI_RESET + " ");
                choice = sc.nextLine();

            } while(!this.reader.checkInterval(1, (i + 1), choice));
            
            monster.setInUseAttack(monster.getAttacks()[Integer.parseInt(choice) - 1]);

            // Allow attack
            return true;
        }

        // Has change monster, not allow attack
        return false; 
    }

    public boolean changeMonster(Player player) {
        String availableChoices = "ON";
        String choice = "O"; // Default to "O" to force changing if Monster is KO
        boolean confirmChange; // If selected another KO monster
        boolean hasChangingMonster = false; // If not KO, not allow attack after changing monster

        // If monster HP > 0, available choice oh changing monster
        if (player.getInUseMonster().getHp() != 0) {
            do {
                System.out.print("\n" + ANSI_GREEN + "CHANGER DE MONSTRE" + ANSI_RESET + ": [" + ANSI_RED + "O" + ANSI_RESET +"]: oui "+"[" + ANSI_RED + "N"  + ANSI_RESET +"]: non ");
                choice = sc.nextLine();

            } while(!this.reader.checkChoice(availableChoices, choice));
        } else {
            System.out.print("\n" + ANSI_RED + player.getInUseMonster().getName() + " est KO, vous devez changer de monstre." + ANSI_RESET + "\n");
        }
        
        // Change monster if is choiced or if monster HP = 0
        if(choice.toUpperCase().equals("O")){
            do {
                confirmChange = true;

                // Select new monster
                int i = 0;
                for(; i < player.getPlayerMonsters().length; i++){
                    System.out.println("[" + ANSI_RED + (i + 1) + ANSI_RESET + "] " + ANSI_CYAN + player.getPlayerMonsters()[i] + ANSI_RESET);
                }

                // Check input
                do {
                    System.out.print("\n" + ANSI_GREEN + "Veuillez saisir un id de monstre:" + ANSI_RESET + "");
                    choice = sc.nextLine();

                } while(!this.reader.checkInterval(1, (i + 1), choice));

                Monster monster = player.getPlayerMonsters()[Integer.parseInt(choice) - 1];

                // Check choiced monster is available
                if(monster.getHp() == 0){
                    System.out.println("\n" + ANSI_RED + monster.getName() + " est KO !" +  ANSI_RESET);
                    confirmChange = false;
                    continue;
                }

                // Update in use monster
                System.out.println("\n" + ANSI_GREEN + monster.getName() + " ! A toi de jouer !" + ANSI_RESET);
                player.setInUseMonster(monster);
                
                hasChangingMonster = true;

            } while (!confirmChange);
        }

        return hasChangingMonster;
    }

    public void useObject(Player player) {
        String availableChoices = "ON";
        String choice;

        do {
            System.out.print("\n" + ANSI_GREEN +"UTILISER UN OBJET"+ ANSI_RESET +": [" + ANSI_RED + "O" + ANSI_RESET +"]: oui "+"[" + ANSI_RED + "N"  + ANSI_RESET +"]: non ");
            choice = sc.nextLine();

        } while(!this.reader.checkChoice(availableChoices, choice));

        System.out.println(); // TMP

        // Use object
        if(choice.toUpperCase().equals("O")){
            int i = 0;
            for(; i < player.getPlayerObjects().length; i++){
                System.out.println("[" + ANSI_RED + (i + 1) + ANSI_RESET + "] " + ANSI_ORANGE + player.getPlayerObjects()[i] + ANSI_RESET);
            }
            // Check input
            do {
                System.out.print("\n" + ANSI_GREEN + "Veuillez saisir un id d'objet:" + ANSI_RESET + "");
                choice = sc.nextLine();

            } while(!this.reader.checkInterval(1, (i + 1), choice));

            entity.objects.Object object = (entity.objects.Object) player.getPlayerObjects()[Integer.parseInt(choice) - 1];
            System.out.println("\n" + ANSI_GREEN +"JOUEUR " + player.getId() + " utilise " + object.getName() + ANSI_RESET);
            object.useObject(player.getInUseMonster());
            player.removeObject(Integer.parseInt(choice) - 1);
        }
    }

    public boolean haveWinner() {
        if (this.players[0].allMonstersKO()) {
            System.out.println("\n" + ANSI_GREEN +"Joueur " + players[0].getId() + ", tout les monstres sont KO." + ANSI_RESET + "\n");
            System.out.println("\n" + ANSI_GREEN +"Joueur " + players[1].getId() + " à gagné !!!" + ANSI_RESET + "\n");
            return true;
        } else if(this.players[1].allMonstersKO()) {
            System.out.println("\n" + ANSI_GREEN +"Joueur " + players[1].getId() + ", tout les monstres sont KO." + ANSI_RESET + "\n");
            System.out.println("\n" + ANSI_GREEN +"Joueur " + players[0].getId() + " à gagné !!!" + ANSI_RESET + "\n");
            return true;
        }

        return false;
    }

    public void printMonsters() {
        System.out.println();
        
        for(int i=1; i <= Monster.findAll().size(); i++){
            System.out.println("[" + ANSI_RED + i + ANSI_RESET + "]\t" + Monster.find(i));
        }
    }

    public void printObjects() {
        System.out.println();
        
        for(int i=1; i <= entity.objects.Object.objects.size(); i++){
            System.out.println("[" + ANSI_RED + i + ANSI_RESET + "]\t" + entity.objects.Object.find((i - 1)));
        }
    }
}
