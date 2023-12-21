package entity.objects;

import entity.Monster;

public class PcPotionObject extends Object{
    
    public PcPotionObject(){
        this.setName("Pc Potion");
        Object.objects.add(this);
    }

    @Override
    public void useObject(Monster monster){
        monster.setAttack(monster.getAttack()+20);
    }
}
