package entity.types;

public class TypeFire extends Type{
    private float burn;

    public TypeFire() {
        this.setName("Feu");
    }

    public float getBurn() {
        return burn;
    }

    public void setBurn(float burn) {
        this.burn = burn;
    }

    
}
