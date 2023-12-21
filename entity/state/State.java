package entity.state;

import entity.Monster;

public abstract class State {
    public static boolean flooded = false;

    public abstract void endureCapacity(Monster monster);
}
