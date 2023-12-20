package entity.state;

import java.util.Random;

import entity.Monster;

public class ParalyzedState extends State {
    private int nbturn = 0;
    public ParalyzedState(){
        this.nbturn = 6;
    }

    public void subirCapacity(Monster monster){
        Random rand = new Random();
        boolean val = rand.nextInt(6) < (7-getNbturn()); //(7-getNbturn)/6 de probabilité pour true
        if(val == true) {
            monster.setCurrentState(new NormalState());
        }
        else {
            this.nbturn -= 1; //decrement remaining turn
            val = rand.nextInt(4) < (1);
            if(!val){
                monster.failAttack();
            }
            
        }
    }

    public State getState(){
        return this;
    }

    public int getNbturn(){
        return this.nbturn;
    }
}
