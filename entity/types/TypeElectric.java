package entity.types;

public class TypeElectric extends Type {
    private float paralysis;

    public TypeElectric() {
        super("Water", "Earth");
        this.setName("Electric");
    }

    public float getParalysis() {
        return paralysis;
    }

    public void setParalysis(float paralysis) {
        this.paralysis = paralysis;
    }

    @Override
    public String toString() {
        return "TypeElectric [paralysis=" + paralysis + "]";
    }
    
}
