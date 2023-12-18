package entity.types;

public class TypePlant extends TypeNature {
    private float cure;

    public TypePlant() {
        this.setName("Plante");
    }

    public float getCure() {
        return cure;
    }

    public void setCure(float cure) {
        this.cure = cure;
    }

}
