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
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.speed = speed;
        this.attacks = new Attack[4];
    }
}
