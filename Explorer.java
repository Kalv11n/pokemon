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
    private Scanner sc = new Scanner(System.in);
    private Player[] players = {new Player(), new Player()};
    public Explorer() {

    }
    
    public void run(){
        this.init();
    }

    private void init(){
        Asciiart draw = new Asciiart();
        draw.draw();
        for(Player player : players){
            System.out.println(ANSI_RED +"==========Player " + player.getId() + "========="+ ANSI_RESET);
            System.out.println("=====Choose 3 Monster======");
            int nbMonster = Monster.findAll().size();
            for(int i=1; i <= nbMonster; i++){
                System.out.println("[" + i + "]\t" + Monster.find(i));
            }

            for(int i=0; i < 3; i++){
                System.out.println(ANSI_GREEN + "Veuillez saisir un id de monstre:" + ANSI_RESET);
                String monsterString = sc.nextLine();

                int idmonster = Integer.parseInt(monsterString);
                Monster monster = Monster.find(idmonster);
                player.addMonster(monster);
                List<Attack> attacks = Attack.findByType(monster.getType());
                System.out.println(attacks.size());
                for(int j=0; j < attacks.size(); j++){
                    System.out.println("[" + (j + 1) + "]\t" + attacks.get(j));
                }
                for(int j=0; j < 4; j++){
                    System.out.println("Veuillez saisir un id d'attaque:");
                    String attackString = sc.nextLine();
                    int id = Integer.parseInt(attackString);
                    Attack attack = attacks.get(id - 1);
                    monster.setAttackInCollection(attack, j);
                }
            }

            Monster[] deck = player.getPlayerMonsters();
            System.out.println("Ton deck");
            for(int i=0; i < deck.length; i++){
                System.out.println(deck[i].toStringWithAttacks());
            }
        }   
    }
}