package entity.types;

public class TypeWater extends Type{
    private float flood;
    private float fall;
    
    public TypeWater() {
        super(new TypeFire(), new TypeElectric());
        this.setName("Eau");
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
