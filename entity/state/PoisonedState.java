package entity.state;

import entity.Monster;

public class PoisonedState extends State {

    @Override
    public void endureCapacity(Monster monster){
        if(FloodedGroundState.flooded) {
            monster.setCurrentState(new NormalState());
            System.out.println(monster.getName() + " n'est plus empoisonné !");
        }
        else {
            int damage = (monster.getInUseAttack().getPower()) / 10;
            monster.setHp(monster.getHp() - damage);
            System.out.println(monster.getName() + " est empoisonné ! (Dégats subis : " + damage + ")");
        }
    }
}
