package entity;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Card{
    private static List<Monster> monsters = new ArrayList<Monster>();
    private String name;
    private int hp;
    private int speed;
    private Attack[] attacks;
    private int attack;
    private int defense;
    private Attack inUseAttack;

    public Monster() {
        this.attacks = new Attack[4];
        monsters.add(this);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    public Attack[] getAttacks() {
        return attacks;
    }

    public void setAttackInCollection(Attack attack, int id) {
        this.attacks[id] = attack;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
    
    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Attack getInUseAttack(){
        return this.inUseAttack;
    }

    public void setInUseAttack(Attack attack){
        this.inUseAttack = attack;
    }

    @Override
    public String toString() {
        String output = "(" + this.getType().getName() + ")\t";

        if (this.getType().getName().length() < 6) {
            output += "\t";
        } 

        output += this.name + "\t";

        if (name.length() < 8) {
            output += "\t";
        }

        output += "[hp=" + hp + ", speed=" + speed + ", attack=" + attack + ", defense=" + defense + "]";

        return output; 
    }   

    public String toStringWithAttacks() {
        String output = "(" + this.getType().getName() + ")\t";

        output += this.name + "\t";

        if (name.length() < 8) {
            output += "\t";
        }

        output += "[hp=" + hp + ", speed=" + speed + ", attack=" + attack + ", defense=" + defense + "]"
            + "\tAttacks: [" + attacks[0].getName() +", " + attacks[1].getName() +", " + attacks[2].getName() +", " + attacks[3].getName()+ "]";

        return output;
    }  

    //---- Others
    public static Monster find(int position) {
        return monsters.get(position - 1);
    }

    public static List<Monster> findAll() {
        return monsters;
    }

    public List<Attack> findAttacksByType() {
        return Attack.findByType(getType());
    }
}
