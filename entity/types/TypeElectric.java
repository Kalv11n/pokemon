package entity.types;

public class TypeElectric extends Type {
    private float paralysis;

    public TypeElectric() {
        super(new TypeWater(), new TypeEarth());
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
