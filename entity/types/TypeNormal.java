package entity.types;

public class TypeNormal extends Type {
    public TypeNormal() {
        super(new TypeNormal(), new TypeNormal());
        this.setName("Normal");
    }
}
