package entity.objects;

import entity.Monster;
import entity.state.FloodedGroundState;

public class SpongeObject extends Object{

    public SpongeObject(){
        this.setName("Eponge (Arrête une innondation)");
        Object.objects.add(this);
    }
    
    @Override
    public void useObject(Monster monster){
        FloodedGroundState.killFlood();
    }
}
