package entity.types;

public class TypeInsect extends TypeNature {
    private float poison;

    public TypeInsect() {
        this.setName("Insect");
        this.poison = 0;
    }

    public float getPoison() {
        return poison;
    }

    public void setPoison(float poison) {
        this.poison = poison;
    }

    

}
