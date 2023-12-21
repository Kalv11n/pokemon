package entity.types;

public class TypeWater extends Type{
    private float flood;
    private float fall;
    
    public TypeWater() {
        super("Fire", "Electric");
        this.setName("Water");
        this.flood = 0;
        this.fall = 0;
    }

    public float getFlood() {
        return flood;
    }

    public void setFlood(float flood) {
        this.flood = flood;
    }

    public float getFall() {
        return fall;
    }
    
    public void setFall(float fall) {
        this.fall = fall;
    }

    @Override
    public String toString() {
        return "TypeWater [flood=" + flood + ", fall=" + fall + "]";
    }    
}
