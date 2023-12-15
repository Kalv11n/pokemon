package entity;

import java.util.ArrayList;
import java.util.List;

public class Attack extends Card {
    private static List<Attack> attacks = new ArrayList<Attack>();
    private String name;
    private int nbuse;
    private int power;
    private float fail;
    
    public Attack() {
        attacks.add(this);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbuse() {
        return nbuse;
    }

    public void setNbuse(int nbuse) {
        this.nbuse = nbuse;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public float getFail() {
        return fail;
    }

    public void setFail(float fail) {
        this.fail = fail;
    }

    @Override
    public String toString() {

        if (name.length() < 8) {
            return this.name + "\t\t[nbuse=" + nbuse + ", power=" + power
                + ", fail=" + fail + "]";
        }

        return this.name + "\t[nbuse=" + nbuse + ", power=" + power
            + ", fail=" + fail + "]";
    }

    //---- Others
    public Attack find(int position) {
        return attacks.get(position);
    }

    public List<Attack> findAll() {
        return attacks;
    }
    
}
