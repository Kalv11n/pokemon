import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Monster;
import entity.Player;

public class Explorer {
    private Scanner sc = new Scanner(System.in);
    private Player[] players = {new Player(), new Player()};
    public Explorer() {

    }
    
    public void run(){
        this.init();
    }

    private void init(){
        System.out.println("==========Pokémon==========");
        for(Player player : players){
            System.out.println("==========Player " + player.getId() + "=========");
            System.out.println("=====Choose 3 Monster=====");
            int nbMonster = Monster.findAll().size();
            for(int i=1; i < nbMonster; i++){
                System.out.println(Monster.find(i));
            }

            for(int i=0; i < 3; i++){
                System.out.println("Veuillez saisir un id :");
                String idString = sc.nextLine();

                int id = Integer.parseInt(idString);
                Monster test = Monster.find(id);
                player.addMonster(test);
            }

            Monster[] deck = player.getPlayerMonsters();
            System.out.println("Ton deck");
            for(int i=0; i < deck.length; i++){
                System.out.println(deck[i]);
            }
        }   
    }
}