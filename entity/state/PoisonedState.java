package entity.state;

import entity.Monster;
import printer.CommentaryPrinter;

public class PoisonedState extends State {

    @Override
    public void endureCapacity(Monster monster){
        if(FloodedGroundState.flooded) {
            monster.setCurrentState(new NormalState());

            CommentaryPrinter.printNotPoison(monster);
        }
        else {
            int damage = (monster.getInUseAttack().getPower()) / 10;
            monster.setHp(monster.getHp() - damage);

            CommentaryPrinter.printPoison(monster, damage);
        }
    }
}
