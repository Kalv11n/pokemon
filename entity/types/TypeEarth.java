package entity.types;

public class TypeEarth extends Type{
    private float hide;
    
    public TypeEarth() {
        super(new TypeElectric(), new TypeNature());
        this.setName("Terre");
    }

    public float getHide() {
        return hide;
    }

    public void setHide(float hide) {
        this.hide = hide;
    }

    
}
