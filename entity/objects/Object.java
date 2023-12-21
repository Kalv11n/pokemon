package entity.objects;

import java.util.ArrayList;
import java.util.List;

import entity.Monster;

public abstract class Object {
    private String name;
    public static List<Object> objects = new ArrayList<Object>();

    public abstract void useObject(Monster monster);

    @Override
    public String toString() {
        String output = this.getName();
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
