package entity.objects;

import java.util.ArrayList;
import java.util.List;

import entity.Monster;

public abstract class Object {
    private String name;
    public static List<Object> objects = new ArrayList<Object>();

    public abstract void useObject(Monster monster);

    public static final String ANSI_ORANGE = "\u001B[38;5;208m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Override
    public String toString() {
        String output =  ANSI_ORANGE + this.getName() + ANSI_RESET;
        return output; 
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Object find(int position) {
        return objects.get(position);
    }
}
