package entity.types;

public class TypeFire extends Type{
    private float burn;

    public TypeFire() {
        super("Nature", "Water");
        this.setName("Fire");
        this.burn = 0;
    }

    public float getBurn() {
        return burn;
    }

    public void setBurn(float burn) {
        this.burn = burn;
    }

    
}
