package entity;

import java.util.ArrayList;
import java.util.List;

import entity.types.Type;
import entity.types.TypeNormal;

public class Attack extends Card {
    private static List<Attack> attacks = new ArrayList<Attack>();
    private String name;
    private int nbuse;
    private int power;
    private float fail;
    
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

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
        if (this.name.equals("Mains")) {
            return ANSI_YELLOW + this.name + ANSI_RESET + "\t\t[nbuse=infinite, fail=0]";
        }

        if (this.name.length() < 8) {
            return ANSI_YELLOW + this.name + ANSI_RESET + "\t\t[nbuse=" + nbuse + ", power=" + power
                + ", fail=" + fail + "]";
        }

        return ANSI_YELLOW + this.name + ANSI_RESET + "\t[nbuse=" + nbuse + ", power=" + power
            + ", fail=" + fail + "]";
    }

    //---- Others
    public boolean canUse() {
        // Check uses
        if (this.nbuse == 0) {
            System.out.println(ANSI_YELLOW + this.getName() + ANSI_RESET + " ne peux plus être utilisé !");
            return false;
        }

        // Decrement
        this.nbuse --;

        return true;
    }

    public static Attack find(int position) {
        return attacks.get(position);
    }

    public static List<Attack> findAll() {
        return attacks;
    }

    public static Attack findLast() {
        return attacks.get(attacks.size() - 1);
    }

    public static List<Attack> findByType(Type type) {
        List<Attack> attacksByType = new ArrayList<>();
        
        for(Attack attack : attacks) {
            if (attack.getType().getClass() == type.getClass() && !(attack.getType() instanceof TypeNormal)) {
                attacksByType.add(attack);
            }
            if (attack.getType() instanceof TypeNormal) {
                attacksByType.add(attack);
            }
        }

        return attacksByType;
    }

    public static void removeLast() {
        attacks.remove(attacks.size() - 1);
    }
    
}
