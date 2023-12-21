package instanciator;

import entity.objects.HealObject;
import entity.objects.HpPotionObject;
import entity.objects.PcPotionObject;
import entity.objects.SpongeObject;

public class ObjectInstanciator {
    public static void instanciate() {
        new SpongeObject();
        new PcPotionObject();
        new HpPotionObject();
        new HealObject();
    }
}
