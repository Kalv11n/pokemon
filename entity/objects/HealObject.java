package entity.objects;

import entity.Monster;
import entity.state.NormalState;
import entity.state.State;

public class HealObject extends Object {
    
    public HealObject(){
        this.setName("Potion de guérison (Soigne les alitérations d'état)");
        Object.objects.add(this);
    }

    @Override
    public void useObject(Monster monster){
        monster.setCurrentState(new NormalState());
    }
}
