package entity;



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
}
