package entity.state;

import entity.Monster;

public abstract class State {
    public static boolean flooded = false;

    public static void setFlooded(boolean flooded){
        State.flooded = flooded;
    }
    
}
