package entity.state;

public class State {
    public static boolean flooded = false;

    public State(){

    }

    public static void setFlooded(boolean flooded){
        State.flooded = flooded;
    }
    
}
