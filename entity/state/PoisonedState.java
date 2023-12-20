package entity.state;

import entity.Monster;

public class PoisonedState extends State {
    

    public void subirCapacity(Monster monster){
        if(State.flooded) {
            monster.setCurrentState(new NormalState());
        }
        else {
            int damage = (monster.getInUseAttack().getPower()) / 10;
            monster.setHp(monster.getHp() - damage);
        }
    }
}
