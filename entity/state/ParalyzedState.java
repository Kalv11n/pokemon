package entity.state;

import java.util.Random;

import entity.Monster;

public class ParalyzedState extends State {
    private int nbturn = 0;

    public ParalyzedState() {
        this.nbturn = 6;
    }

    @Override
    public void endureCapacity(Monster monster){
        Random rand = new Random();
        boolean val = rand.nextInt(6) < (7-getNbturn()); //(7-getNbturn)/6 de probabilité pour true
        if(val) {
            monster.setCurrentState(new NormalState());
            System.out.println(monster.getName() + " n'est plus paralysé !");
        }
        else {
            this.nbturn -= 1; //decrement remaining turn
            val = rand.nextInt(4) < (1);
            if(!val){
                monster.failAttack(true);
                System.out.println(monster.getName() + " est paralysé !");
            }
            
        }
    }

    public int getNbturn(){
        return this.nbturn;
    }
}
