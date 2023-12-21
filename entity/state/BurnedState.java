package entity.state;

import entity.Monster;

public class BurnedState extends State {

    @Override
    public void endureCapacity(Monster monster){
        if(State.flooded) {
            monster.setCurrentState(new NormalState());
            System.out.println(monster.getName() + " n'est plus brulé !");
        } else {
            int damage = (monster.getInUseAttack().getPower()) / 10;
            monster.setHp(monster.getHp() - damage);
            System.out.println(monster.getName() + " est brulé ! (Dégats subis : " + damage + ")");
        }
    }

}
