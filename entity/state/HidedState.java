package entity.state;

import java.util.Random;

import entity.Monster;

public class HidedState extends State {
    private int nbturn = 0;
    public HidedState(){
        Random rand = new Random();
        this.nbturn = rand.nextInt(3) + 1;
    }

    public void subirCapacity(Monster monster){
        if(this.nbturn <= 0) {
            monster.setCurrentState(new NormalState());
        }
        else {
            this.nbturn -= 1; //decrement remaining turn
        }
    }
}
