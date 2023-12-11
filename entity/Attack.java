package entity;

import entity.types.Type;

public class Attack {
    private String name;
    private Type type;
    private int limitUse;
    private int power;
    private float failureProbability;
    
    public Attack(String name, Type type, int limitUse, int power, float failureProbability) {
        this.name = name;
        this.type = type;
        this.limitUse = limitUse;
        this.power = power;
        this.failureProbability = failureProbability;
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

    public int getLimitUse() {
        return limitUse;
    }

    public void setLimitUse(int limitUse) {
        this.limitUse = limitUse;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public float getFailureProbability() {
        return failureProbability;
    }

    public void setFailureProbability(float failureProbability) {
        this.failureProbability = failureProbability;
    }
    
}
