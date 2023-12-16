package entity.types;

public abstract class Type {
    private String name;

    public final String[] nameType = { "Earth", "Electric", "Fire", "Nature", "Water" };

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
