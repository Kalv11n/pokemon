package entity.objects;

import entity.Monster;

public class HpPotionObject extends Object{
    
    public HpPotionObject(){
        this.setName("Hp Potion");
        Object.objects.add(this);
    }

    @Override
    public void useObject(Monster monster){
        monster.setHp(monster.getHp() + 30 );
    }
}
