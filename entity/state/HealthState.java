package entity.state;

import entity.Monster;

public class HealthState extends State {

    @Override
    public void endureCapacity(Monster monster){
        // If new HealthState(this) (TypeNature monster only), save 5% of current HP
        int hp = (int) Math.round(monster.getHp() * 1.05) + 1;
        System.out.println(monster.getName() + " a récupéré " + (hp - monster.getHp()) + " HP ! ");
        monster.setHp(hp);

        // Set monster state to Normal
        monster.setCurrentState(new NormalState());
    }
}
