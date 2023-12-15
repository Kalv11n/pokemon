import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Explorer {
    
    public Explorer() {

    }
    
    public void run(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> monster = new ArrayList<>();
        monster.add(1);
        monster.add(2);
        monster.add(3);
        System.out.println("==========Pokémon==========");
        System.out.println("==========Player 1=========");
        System.out.println("=====Choose 1 Monster=====");
        for(int i=0;i < monster.size() ; i++){
            System.out.println(monster.get(i));
        }
        System.out.println("Veuillez saisir un id :");
        String idString = sc.nextLine();
        int id = Integer.parseInt(idString);
        // findmonster(id){
        //     return Monster
        // }
    }
}