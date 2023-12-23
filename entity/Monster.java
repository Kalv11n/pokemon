package entity;

import java.util.ArrayList;
import java.util.List;

import entity.state.BurnedState;
import entity.state.FloodedGroundState;
import entity.state.HealthState;
import entity.state.HidedState;
import entity.state.NormalState;
import entity.state.ParalyzedState;
import entity.state.PoisonedState;
import entity.state.State;
import entity.types.TypeEarth;
import entity.types.TypeElectric;
import entity.types.TypeFire;
import entity.types.TypeInsect;
import entity.types.TypeNature;
import entity.types.TypePlant;
import entity.types.TypeWater;

public class Monster extends Card{
    private static List<Monster> monsters = new ArrayList<Monster>();
    private String name;
    private int hp;
    private int speed;
    private Attack[] attacks;
    private int attack;
    private int defense;
    // Others
    private Attack inUseAttack;
    private State currentState;
    private int damageReceived;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";
    public static final String ANSI_BROWN = "\u001B[38;2;98;42;15m";;
    public static final String ANSI_GRAY = "\u001B[90m";


    public Monster() {
        this.currentState = new NormalState();
        this.attacks = new Attack[4];
        this.damageReceived = 0;
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

    public Attack getInUseAttack(){
        return this.inUseAttack;
    }
    
    public void setInUseAttack(Attack attack){
        this.inUseAttack = attack;
    }
    
    //---- toString
    @Override
    public String toString() {
        String output = "";
        switch (this.getType().getName()) {
            case "Plant":
                output = ANSI_RESET + "(" + ANSI_GREEN + this.getType().getName() + ANSI_RESET + ")\t";
                break;
            case "Fire":
                output = ANSI_RESET + "(" + ANSI_RED + this.getType().getName() + ANSI_RESET + ")\t";
                break;
            case "Water":
                output = ANSI_RESET + "(" + ANSI_BLUE + this.getType().getName() + ANSI_RESET + ")\t";
                break;
            case "Earth":
                output = ANSI_RESET + "(" + ANSI_BROWN + this.getType().getName() + ANSI_RESET + ")\t";
                break;
            case "Electric":
                output = ANSI_RESET + "(" + ANSI_YELLOW + this.getType().getName() + ANSI_RESET + ")\t";
                break;
            case "Insect":
                output = ANSI_RESET + "(" + ANSI_PURPLE + this.getType().getName() + ANSI_RESET + ")\t";
                break;  
            default:
                output = ANSI_RESET + "(" + ANSI_GRAY + this.getType().getName() + ANSI_RESET + ")\t";
                break;
        }

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
    public void attack(Monster monster) {
        double randomCoef = 0.85 + Math.random() * (1.0 - 0.85);
        double damageCoef = 1.0;
        double damage = 0.0;

        // Check advantage or weakness
        if (this.hasAdvantage(monster)) {
            damageCoef = 2.0;
        } else if (this.hasWeakness(monster)) {
            damageCoef = 0.5;
        }

        // Attack with hand
        if (this.inUseAttack.getName().equals("Mains")) {
            damage = (20 * (this.attack / monster.getDefense()) * randomCoef);
        } else {
            // Attack with special attack
            damage = ((11 * this.attack * this.inUseAttack.getPower()) / (25 * monster.getDefense()) + 2) * damageCoef * randomCoef;
        }

        // Flooded condition
        boolean fall = false;
        if (FloodedGroundState.flooded && !(this.getType() instanceof TypeWater)) { // All apart Water Type
            double fallRate = Math.random();

            TypeWater flooder = (TypeWater) FloodedGroundState.monster.getType(); 
            if (fallRate <= flooder.getFall()) {
                fall = true;
            }
        }

        // Heal condition
        if (this.getType() instanceof TypeNature && FloodedGroundState.flooded) { 
            this.setCurrentState(new HealthState());
        }

        // Normal attack
        if (!fall) {
            // Fail attack
            if (!this.failAttack(false)) {
                // Make damage
                monster.reduceHp((int) Math.round(damage));
                System.out.println("Dégats infligés : " + ANSI_RED + monster.getDamageReceived() + ANSI_RESET);

                // Capacity
                if (this.getCapacity() != 0) { // Check if have a capacity rate
                    double randomCapacity = Math.random();
                    double capacityRate = this.getCapacity();

                    // If capacity success
                    if (randomCapacity <= capacityRate) {
                        this.applyCapacity(monster);
                    }
                }
            }
        } else { // Flooded failure
            int damageReceived = (int) Math.round(damage * 0.25);
            this.reduceHp(damageReceived);
            System.out.println(this.getName() + " a glissé ! (Dégats subis : " + ANSI_RED + damageReceived + ANSI_RESET + ")");

            this.failAttack(true);
            this.setDamageReceived(0); // damage received = 0 for printing 
        }

        // Endure capacity
        this.currentState.endureCapacity(this);
    }

    public boolean failAttack(boolean forceFailure) {     
        // Calcul failure attack
        double randomFail = Math.random();

        if (randomFail <= this.inUseAttack.getFail() || forceFailure) {
            System.out.println(this.getName() + " rate son attaque !");
            this.setDamageReceived(0);
            return true;
        }

        return false;
    }

    public void applyCapacity(Monster monster) {
        if (this.getType() instanceof TypeFire) {
            monster.setCurrentState(new BurnedState());
            System.out.println(this.getName() + " a brulé " + monster.getName() + " !");
        } else if (this.getType() instanceof TypeWater) {
            if (!FloodedGroundState.flooded) {
                // Flooded update
                FloodedGroundState.setFlooded(true);
                FloodedGroundState.updateFloodedTurn();
                FloodedGroundState.saveFlooderMonster(this);
                System.out.println(this.getName() + " a innondé le terrain !");
            }
        } else if (this.getType() instanceof TypeElectric) {
            monster.setCurrentState(new ParalyzedState());
            System.out.println(this.getName() + " a paralysé " + monster.getName() + " !");
        } else if (this.getType() instanceof TypeEarth) {
            this.setCurrentState(new HidedState());
            System.out.println(this.getName() + " se cache !");
        } else if (this.getType() instanceof TypeInsect) {
            monster.setCurrentState(new PoisonedState());
            System.out.println(this.getName() + " a empoisonné " + monster.getName() + " !");
        } else if (this.getType() instanceof TypePlant) {
            if (!(this.getCurrentState() instanceof NormalState)) {
                System.out.println(this.getName() + " n'est plus altéré !");
                this.setCurrentState(new NormalState());
            }
        }
    }

    public double getCapacity() {
        if (this.getType() instanceof TypeFire) {
            TypeFire type = (TypeFire) this.getType();

            if (type.getBurn() != 0) {
                return type.getBurn();
            }

        } else if (this.getType() instanceof TypeWater) {
            TypeWater type = (TypeWater) this.getType();

            if (type.getFlood() != 0) {
                return type.getFlood();
            }

        } else if (this.getType() instanceof TypeElectric) {
            TypeElectric type = (TypeElectric) this.getType();

            if (type.getParalysis() != 0) {
                return type.getParalysis();
            }

        } else if (this.getType() instanceof TypeEarth) {
            TypeEarth type = (TypeEarth) this.getType();

            if (type.getHide() != 0) {
                return type.getHide();
            }

        } else if (this.getType() instanceof TypeInsect) {
            TypeInsect type = (TypeInsect) this.getType();

            if (type.getPoison() != 0) {
                return type.getPoison();
            }

        } else if (this.getType() instanceof TypePlant) {
            TypePlant type = (TypePlant) this.getType();

            if (type.getCure() != 0) {
                return type.getCure();
            }

        }

        return 0;
    }

    public void reduceHp(int damage) {
        this.setDamageReceived(damage);

        if (this.hp - damage < 0) {
            this.hp = 0;
        } else {
            this.hp -= damage;
        }
    }

    public boolean hasAdvantage(Monster monster) {
        String typeName = monster.getType().getName();

        if (typeName.equals("Insect") || typeName.equals("Plant")) {
            typeName = "Nature";
        }
        
        return (this.getType().getAdvantageType().equals(typeName));
    }

    public boolean hasWeakness(Monster monster) {
        String typeName = monster.getType().getName();

        if (typeName.equals("Insect") || typeName.equals("Plant")) {
            typeName = "Nature";
        }
        
        return (this.getType().getWeaknessType().equals(typeName));
    }

    public int getDamageReceived() {
        return this.damageReceived;
    }

    public void setDamageReceived(int damage) {
        this.damageReceived = damage;
    }


    //---- Monsters List
    public static Monster find(int position) {
        return monsters.get(position);
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

    public static void remove(Monster monster) {
        monsters.remove(monster);
    }
}
