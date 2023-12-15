package entity;

public class Attack extends Card {
    private int nbuse;
    private int power;
    private float fail;
    
    public Attack() {
        
    }

    public int getNbuse() {
        return nbuse;
    }

    public void setNbuse(int nbuse) {
        this.nbuse = nbuse;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public float getFail() {
        return fail;
    }

    public void setFail(float fail) {
        this.fail = fail;
    }

    @Override
    public String toString() {
        return "Attack [nbuse=" + nbuse + ", power=" + power
                + ", fail=" + fail + "]";
    }
    
}
