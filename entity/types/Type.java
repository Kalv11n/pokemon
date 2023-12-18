package entity.types;

public abstract class Type {
    private String name;
    private String advantageType;
    private String weaknessType;

    public Type(String advantageType, String weaknessType) {
        this.advantageType = advantageType;
        this.weaknessType = weaknessType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdvantageType(){
        return this.advantageType;
    } 

    public void setAdvantageType(String type) {
        this.advantageType = type;
    }

    public String getWeaknessType(){
        return this.weaknessType;
    }

    public void setWeaknessType(String type) {
        this.weaknessType = type;
    }
}
