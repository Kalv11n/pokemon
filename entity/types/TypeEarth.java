package entity.types;

public class TypeEarth extends Type{
    private float hide;
    
    public TypeEarth() {
        super("Electric", "Nature");
        this.setName("Earth");
        this.hide = 0;
    }

    public float getHide() {
        return hide;
    }

    public void setHide(float hide) {
        this.hide = hide;
    }    
}
