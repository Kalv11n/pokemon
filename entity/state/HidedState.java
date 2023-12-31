package entity.state;

import java.util.Random;

import entity.Monster;
import printer.CommentaryPrinter;

public class HidedState extends State {
    private int nbturn = 0;
    private boolean hasProtection = false;
    private int oldDefense;

    public HidedState() {
        Random rand = new Random();
        this.nbturn = rand.nextInt(3) + 1;
    }

    @Override
    public void endureCapacity(Monster monster){
        if(this.nbturn <= 0) {
            // Remain old defense
            monster.setDefense(this.oldDefense);

            // Change state
            monster.setCurrentState(new NormalState());
            
            CommentaryPrinter.printNotHide(monster);
        } else {
            // Apply protection
            if (!this.hasProtection) {
                this.oldDefense = monster.getDefense();
                monster.setDefense(this.oldDefense * 2);
                this.hasProtection = true;
            }
            this.nbturn -= 1; //decrement remaining turn
            
            CommentaryPrinter.printHide(monster);
        }
    }
}
