package entity.objects;

import entity.Monster;
import entity.state.State;

public class SpongeObject extends Object{

    public SpongeObject(){
        this.setName("Sponge");
        Object.objects.add(this);
    }
    
    @Override
    public void useObject(Monster monster){
        //State.setFlooded(false);
    }
}
