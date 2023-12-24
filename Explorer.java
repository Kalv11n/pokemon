import java.util.List;
import java.util.Scanner;

import entity.Attack;
import entity.Monster;
import entity.Player;
import entity.state.FloodedGroundState;
import entity.types.TypeNormal;
import instanciator.ObjectInstanciator;
import parser.ConfigParser;
import printer.CommentaryPrinter;
import reader.ConfigReader;
import reader.InputReader;

public class Explorer {    
    private Scanner sc;
    private InputReader reader;
    private Player[] players = {new Player(), new Player()};
    
    public static void main(String[] args) {
        ConfigParser parser = new ConfigParser();
        ConfigReader reader = new ConfigReader(parser);
        Explorer explorer = new Explorer();

        // Instanciate object
        ObjectInstanciator.instanciate();

        // Instanciate new FloodedGroundState 
        new FloodedGroundState();

        // Instanciate Attack with hands for all
        Attack mains = new Attack();
        mains.setName("Mains");
        mains.setNbuse(-1); // Nbuse -1 for infinite
        mains.setFail(0);
        mains.setType(new TypeNormal());

        // Config reader
        reader.read("monster");
        reader.read("attack");

        explorer.run();
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
        CommentaryPrinter.printLaunch();

        String choice;

        // Players selection
        for(Player player : players){
            CommentaryPrinter.printCurrentPlayer(player.getId());
            CommentaryPrinter.printSelectMonster();

            for(int i=0; i < 3; i++){
                CommentaryPrinter.printMonsters();

                // Select monster
                do {
                    CommentaryPrinter.printSelectMonsterRequest();
                    choice = sc.nextLine();
                    
                } while(!this.reader.checkInterval(1, Monster.findAll().size(), choice));    
                
                // Add monster to player monsters
                Monster monster = Monster.find(Integer.parseInt(choice) - 1);
                player.addMonster(monster);
                
                CommentaryPrinter.printSelectResponse(monster.getName());

                // Remove monster from list
                Monster.remove(monster);

                // Print attacks by type
                List<Attack> attacks = Attack.findByType(monster.getType());
                
                CommentaryPrinter.printAttacks(monster);

                for(int j=0; j < 4; j++){
                    // Select attacks
                    do {
                        CommentaryPrinter.printSelectAttackRequest();
                        choice = sc.nextLine();

                    } while(!this.reader.checkInterval(1, attacks.size(), choice));

                    // Copy attack 
                    Attack attack = attacks.get(Integer.parseInt(choice) - 1);
                    Attack copy = new Attack();
                    copy.setName(attack.getName());
                    copy.setFail(attack.getFail());
                    copy.setPower(attack.getPower());
                    copy.setType(attack.getType());
                    copy.setNbuse(attack.getNbuse());

                    // Add attack to monster
                    monster.setAttackInCollection(copy, j);

                    // Remove copy from global list
                    Attack.removeLast();

                    CommentaryPrinter.printSelectResponse(attack.getName());
                }
                clearScreen();
            }

            CommentaryPrinter.printSelectObject();

            for(int w=0; w < 5; w++) {
                CommentaryPrinter.printObjects();
                // Select object
                do {
                    CommentaryPrinter.printSelectObjectRequest();
                    choice = sc.nextLine();
                    
                } while(!this.reader.checkInterval(1, 4, choice));    
                
                // Add object to player objects
                entity.objects.Object object = entity.objects.Object.find(Integer.parseInt(choice) - 1);
                player.addObject(object);
                
                CommentaryPrinter.printSelectResponse(object.getName());
            }

            clearScreen();
            
            // Show deck
            CommentaryPrinter.printDeck(player);

            this.sc.nextLine();
            clearScreen();
        }   

        CommentaryPrinter.printStarting();
        CommentaryPrinter.printGroundInfo();

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
                CommentaryPrinter.printPlayerAdvantage(players[0]);
                playersOrder = new Player[]{players[0], players[1]};
            } else {
                CommentaryPrinter.printPlayerAdvantage(players[0]);
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
                    CommentaryPrinter.printAttack(currentMonster, ennemyMonster);
                    
                    currentMonster.attack(ennemyMonster);

                    // Printer
                    if (!currentMonster.getInUseAttack().getName().equals("Mains")) {
                        if (currentMonster.hasAdvantage(ennemyMonster)) {
                            CommentaryPrinter.printIsEfficient();
                        } else if (currentMonster.hasWeakness(ennemyMonster)) {
                            CommentaryPrinter.printIsNotEfficient();
                        }
                    }

                    CommentaryPrinter.printRemainHp(ennemyMonster);
                }
            }

            this.sc.nextLine();
            clearScreen();

            // Flooded instruction
            if (FloodedGroundState.flooded) {
                FloodedGroundState.decrementTurn();
            }

        } while (!this.haveWinner());
    }

    //---- Others
    public void interact(Player player) {
        CommentaryPrinter.printCurrentPlayer(player.getId());
        CommentaryPrinter.printCurrentMonster(player);

        String choice;

        // Change monster
        this.changeMonster(player);
        
        if (player.getAllowAttacking()) {
            // Not changing monster, maybe use an object
            this.useObject(player);

            if (player.getAllowAttacking()) {
                // Not using object, attack
                Monster monster = player.getInUseMonster();

                // Attacking
                CommentaryPrinter.printMonsterAttacks(monster);

                // Check ability to attack
                do {
                    // Check input
                    do {
                        CommentaryPrinter.printSelectAttackRequest();
                        choice = sc.nextLine();

                    } while(!this.reader.checkInterval(1, monster.getAttacks().length, choice));

                    monster.setInUseAttack(monster.getAttacks()[Integer.parseInt(choice) - 1]);

                } while(!monster.getInUseAttack().canUse());
            }
        }

        clearScreen();
    }

    public void changeMonster(Player player) {
        String availableChoices = "ON";
        String choice = "O"; // Default to "O" to force changing if Monster is KO

        // If monster HP > 0, available choice oh changing monster
        if (player.getInUseMonster().getHp() != 0) {
            do {
                CommentaryPrinter.printChangeMonster();
                choice = sc.nextLine();

            } while(!this.reader.checkChoice(availableChoices, choice));

            if(choice.toUpperCase().equals("O")){
                player.setAllowAttacking(false);
            } else {
                player.setAllowAttacking(true);
            }
        } else {
            CommentaryPrinter.printKoChange(player);
            player.setAllowAttacking(true);
        }
        
        // Change monster if is choiced or if monster HP = 0
        if(choice.toUpperCase().equals("O")){
            boolean changeApproved; // If selected another KO monster

            do {
                changeApproved = true;

                // Select new monster
                CommentaryPrinter.printPlayerMonsters(player);

                // Check input
                do {
                    CommentaryPrinter.printSelectMonsterRequest();
                    choice = sc.nextLine();

                } while(!this.reader.checkInterval(1, 3, choice));

                Monster monster = player.getPlayerMonsters()[Integer.parseInt(choice) - 1];

                // Check choiced monster is available
                if(monster.getHp() == 0){
                    CommentaryPrinter.printKo(monster);
                    changeApproved = false;
                    continue;
                }

                // Check flooder monster
                if (FloodedGroundState.monster == player.getInUseMonster()) {
                    FloodedGroundState.killFlood();
                }

                // Update in use monster
                CommentaryPrinter.printChangeMonsterResponse(monster);
                player.setInUseMonster(monster);

                this.sc.nextLine();

            } while (!changeApproved);
        }
    }

    public void useObject(Player player) {
        String availableChoices = "ON";
        String choice;

        do {
            CommentaryPrinter.printUseObject();
            choice = sc.nextLine();
        } while(!this.reader.checkChoice(availableChoices, choice));

        // Use object
        if(choice.toUpperCase().equals("O")){
            CommentaryPrinter.printPlayerObjects(player);

            // Check input
            do {
                CommentaryPrinter.printSelectObjectRequest();
                choice = sc.nextLine();

            } while(!this.reader.checkInterval(1, 4, choice));

            entity.objects.Object object = (entity.objects.Object) player.getPlayerObjects()[Integer.parseInt(choice) - 1];
            CommentaryPrinter.printUseObjectResponse(player, object);

            // Use object
            object.useObject(player.getInUseMonster());
            player.removeObject(Integer.parseInt(choice) - 1);

            player.setAllowAttacking(false);

            this.sc.nextLine();
        }
    }

    public boolean haveWinner() {
        if (this.players[0].allMonstersKO()) {
            CommentaryPrinter.printKoAll(players[0]);
            CommentaryPrinter.printWinner(players[1]);
            return true;
        } else if(this.players[1].allMonstersKO()) {
            CommentaryPrinter.printKoAll(players[1]);
            CommentaryPrinter.printWinner(players[0]);
            return true;
        }

        return false;
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 
}
