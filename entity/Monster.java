package entity;

import java.util.ArrayList;
import java.util.List;

import entity.state.NormalState;
import entity.state.State;

public class Monster extends Card{
    private static List<Monster> monsters = new ArrayList<Monster>();
    private String name;
    private int hp;
    private int speed;
    private Attack[] attacks;
    private int attack;
    private int defense;
    private Attack inUseAttack;
    private State currentState;

    public Monster() {
        this.currentState = new NormalState();
        this.attacks = new Attack[4];
        monsters.add(this);
    }

    //---- getters & setters
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

    public State getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(State state) {
        this.currentState = state;
    }


    //---- toString
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


    //---- Fight functions
    public boolean attack(Monster monster) {
        double randomCoef = 0.85 + Math.random() * (1.0 - 0.85);
        double dammageCoef = 1.0;
        double dammage = 0.0;
        double capacityRate;

        // Check advantage or weakness
        if (this.hasAdvantage(monster)) {
            dammageCoef = 2.0;
        } else if (this.hasWeakness(monster)) {
            dammageCoef = 0.5;
        }

        // Normal State
        if (this.getCurrentState() instanceof NormalState) {
            dammage = ((11 * this.attack * this.inUseAttack.getPower()) / (25 * monster.getDefense()) + 2) * dammageCoef * randomCoef;
        }

        // Fail attack
        double randomFail = Math.random();
        
        if (randomFail <= this.inUseAttack.getFail()) {
            return false;
        }

        // Make dammage
        monster.reduceHp((int) Math.round(dammage));
        return true;
    }

    public void reduceHp(int dammage) {
        if (this.hp - dammage < 0) {
            this.hp = 0;
        } else {
            this.hp -= dammage;
        }
    }

    public boolean hasAdvantage(Monster monster) {
        return (this.getType().getAdvantageType() == monster.getType().getName());
    }

    public boolean hasWeakness(Monster monster) {
        return (this.getType().getWeaknessType() == monster.getType().getName());
    }


    //---- Monsters List
    public static Monster find(int position) {
        return monsters.get(position - 1);
    }

    public static List<Monster> findAll() {
        return monsters;
    }

    public static Monster findLast() {
        return monsters.get(monsters.size() - 1);
    }

    public List<Attack> findAttacksByType() {
        return Attack.findByType(this.getType());
    }

    public static void removeLast() {
        monsters.remove(monsters.size() - 1);
    }
}
