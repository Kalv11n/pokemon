package entity.types;

public abstract class Type {
    private String name;
    private Type advantageType;
    private Type weaknessType;

    public final String[] nameType = { "Earth", "Electric", "Fire", "Nature", "Water" };

    public Type(Type advantageType, Type weaknessType) {
        this.advantageType = advantageType;
        this.weaknessType = weaknessType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getAdvantageType(){
        return this.advantageType;
    } 

    public Type getWeaknessType(){
        return this.weaknessType;
    }
}
