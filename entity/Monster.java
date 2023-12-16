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

    @Override
    public String toString() {
        if (name.length() < 8) {
            return this.name + "\t\t[hp=" + hp + ", speed=" + speed + ", attack=" + attack + ", defense=" + defense + "]";
        } 

        return this.name + "\t[hp=" + hp + ", speed=" + speed + /*", attacks="
                + Arrays.toString(attacks) + */", attack=" + attack + ", defense=" + defense + "]";
    }   
    public String toStringWithAttacks() {
        if (name.length() < 8) {
            return this.name + "\t\t[hp=" + hp + ", speed=" + speed + ", attack=" + attack + ", defense=" + defense + "]" + "\n\t\t" + attacks[0].getName() +", " + attacks[1].getName() +", " + attacks[2].getName() +", " + attacks[3].getName()+ "]";
        } 

        return this.name + "\t[hp=" + hp + ", speed=" + speed + /*", attacks="
                + Arrays.toString(attacks) + */", attack=" + attack + ", defense=" + defense + "]" + "\n\t\t[" + attacks[0].getName() +", " + attacks[1].getName() +", " + attacks[2].getName() +", " + attacks[3].getName()+ "]";
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
