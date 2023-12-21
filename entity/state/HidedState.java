package entity.state;

import java.util.Random;

import entity.Monster;

public class HidedState extends State {
    private int nbturn = 0;

    public HidedState() {
        Random rand = new Random();
        this.nbturn = rand.nextInt(3) + 1;
    }

    @Override
    public void endureCapacity(Monster monster){
        if(this.nbturn <= 0) {
            monster.setCurrentState(new NormalState());
            System.out.println(monster.getName() + " n'est plus caché !");
        } else {
            this.nbturn -= 1; //decrement remaining turn
            System.out.println(monster.getName() + " est caché !");
        }
    }
}
