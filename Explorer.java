import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Attack;
import entity.Monster;
import entity.Player;

public class Explorer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private Scanner sc;
    private Player[] players = {new Player(), new Player()};
    private boolean terrainFlooded = false;

    public Explorer() {

    }
    
    public void run(){
        this.sc  = new Scanner(System.in);

        this.init();
        this.start();

        this.sc.close();
    }

    private void init(){
        System.out.println("======== Pokémon ========") ;
        for(Player player : players){
            System.out.println("\n" + ANSI_RED +"======== JOUEUR " + player.getId() + " ========"+ ANSI_RESET);
            System.out.println("== CHOISISSEZ 3 POKEMONS ==\n");

            int nbMonster = Monster.findAll().size();
            for(int i=1; i <= nbMonster; i++){
                System.out.println("[" + ANSI_RED + i + ANSI_RESET + "]\t" + Monster.find(i));
            }

            for(int i=0; i < 3; i++){
                System.out.print("\n" + ANSI_GREEN + "Veuillez saisir un id de monstre:" + ANSI_RESET + " ");
                String monsterString = sc.nextLine();
                
                int idmonster = Integer.parseInt(monsterString);
                Monster monster = Monster.find(idmonster);
                player.addMonster(monster);
                
                System.out.println(ANSI_PURPLE + "-> " + monster.getName() + ANSI_RESET +"\n");

                List<Attack> attacks = Attack.findByType(monster.getType());
                
                for(int j=0; j < attacks.size(); j++){
                    System.out.println("[" + ANSI_RED +  (j + 1) + ANSI_RESET + "]\t" + attacks.get(j));
                }

                for(int j=0; j < 4; j++){
                    System.out.print("\n  " + ANSI_GREEN + "Veuillez saisir un id d'attaque:" + ANSI_RESET + " ");
                    String attackString = sc.nextLine();

                    int id = Integer.parseInt(attackString);
                    Attack attack = attacks.get(id - 1);
                    monster.setAttackInCollection(attack, j);

                    System.out.println("  " + ANSI_PURPLE + "-> " + attack.getName() + ANSI_RESET);
                }
            }

            Monster[] deck = player.getPlayerMonsters();
            System.out.println(ANSI_GREEN + "\n\nTon deck:" + ANSI_RESET + "\n");
            for(int i=0; i < deck.length; i++){
                System.out.println(ANSI_CYAN + "-> " + deck[i].toStringWithAttacks() + ANSI_RESET);
            }
        }   

        System.out.println(ANSI_PURPLE + "\nLe terrain est neutre.\n");
        System.out.println(ANSI_RED + "======== LE JEU VA COMMENCER =======" + ANSI_RESET + "\n");
    }

    private void start() {
        
    }
}