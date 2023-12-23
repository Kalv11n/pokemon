package entity.state;

import java.util.Random;

import entity.Monster;

public class FloodedGroundState extends State {
    public static boolean flooded = false;
    public static int nbTurn = 0;
    public static Monster monster = null;

    public static void setFlooded(boolean flooded){
        FloodedGroundState.flooded = flooded;
    }

    public static void decrementTurn() {
        FloodedGroundState.nbTurn --;

        // Kill flooded
        if (nbTurn == 0) {
            FloodedGroundState.killFlood();
        }
    }

    public static void updateFloodedTurn() {
        Random rand = new Random();
        FloodedGroundState.nbTurn = rand.nextInt(3) + 2; // + 2 for rendering
    }

    public static void saveFlooderMonster(Monster monster) {
        FloodedGroundState.monster = monster;
    } 

    public static void killFlood() {
        FloodedGroundState.flooded = false;
        FloodedGroundState.monster = null;
        System.out.println("\nLe terrain n'est plus innondé !");
    }

    @Override
    public void endureCapacity(Monster monster){
    }
}
