package entity.types;

public class TypePlant extends TypeNature {
    private float cure;

    public TypePlant() {
        this.setName("Plant");
        this.cure = 0;
    }

    public float getCure() {
        return cure;
    }

    public void setCure(float cure) {
        this.cure = cure;
    }

}
