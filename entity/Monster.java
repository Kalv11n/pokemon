package entity;

import entity.types.Type;

public class Monster {
    private String name;
    private Type type;
    private int hp;
    private int speed;
    private Attack[] attacks;
    private int defense;

    public Monster(String name, Type type, int hp, int speed, int defense) {
        this.attacks = new Attack[4];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAttack(Attack attack, int id) {
        this.attacks[id] = attack;
    }
    
     public Attack[] getAttacks() {
        return attacks;
    }
    
    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    
}
