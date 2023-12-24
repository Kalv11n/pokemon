package entity.state;

import entity.Monster;
import printer.CommentaryPrinter;

public class BurnedState extends State {

    @Override
    public void endureCapacity(Monster monster){
        if(FloodedGroundState.flooded) {
            monster.setCurrentState(new NormalState());
            CommentaryPrinter.printNotBurn(monster);
        } else {
            int damage = (monster.getInUseAttack().getPower()) / 10;
            monster.setHp(monster.getHp() - damage);
            CommentaryPrinter.printBurn(monster, damage);
        }
    }

}
