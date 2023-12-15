package instanciator;

import java.lang.reflect.Field;
import java.util.Random;

import entity.Card;
import entity.types.Type;
import entity.types.TypeEarth;
import entity.types.TypeElectric;
import entity.types.TypeFire;
import entity.types.TypeInsect;
import entity.types.TypeNature;
import entity.types.TypeNormal;
import entity.types.TypePlant;
import entity.types.TypeWater;

public abstract class Instanciator {
    
    public static boolean hasAttribute(Object obj, String attributeName) {
        Class<?> objClass = obj.getClass();
        
        while (objClass != null) {
            Field[] fields = objClass.getDeclaredFields();

            for (Field field : fields) {
                if (field.getName().equals(attributeName.toLowerCase())) {
                    return true;
                }
            }

            // Remontez d'une classe dans la hiérarchie
            objClass = objClass.getSuperclass();
        }

        return false;
    }

    public int getRandNumberInInterval(int intervalFirst, int intervalLast) {
        if (intervalFirst > intervalLast) {
            throw new IllegalArgumentException("Invalid interval. First value must be less than or equal to the last value.");
        }

        Random random = new Random();

        return random.nextInt((intervalLast - intervalFirst) + 1) + intervalFirst;
    }

    public void selectType(Card card, String arg) throws Exception {
        switch (arg) {
            case "Electric":
                card.setType(new TypeElectric());
                break;

            case "Water":
                card.setType(new TypeWater());
                break;
            
            case "Fire":
                card.setType(new TypeFire());
                break;
            
            case "Earth":
                card.setType(new TypeEarth());
                break;
            
            case "Nature":
                card.setType(new TypeNature());
                break;
            
            case "Insect":
                card.setType(new TypeInsect());
                break;
            
            case "Plant":
                card.setType(new TypePlant());
                break;
            
            case "Normal":
                card.setType(new TypeNormal());
                break;
            
            default:
                throw new Exception("Undefined type '" + arg + "'");
        }
    }

    public void setTypeAttribute(Card card, String attributeName, String attributeValue) throws Exception {
        Type type = card.getType();

        if (type == null) {
            throw new Exception("Error configuration: Card type not initialized.");
        }

        switch (attributeName) {
            case "Paralysis": // TypeElectric
                if (!(card.getType() instanceof TypeElectric)) {
                    throw new Exception("Error configuration : Undefined '" + attributeName + "' attribute for TypeElectric::class");
                }

                TypeElectric elec = (TypeElectric) card.getType();
                elec.setParalysis(Float.parseFloat(attributeValue));

                break;
            
            case "Hide": // TypeEarth
                if (!(card.getType() instanceof TypeEarth)) {
                    throw new Exception("Error configuration : Undefined '" + attributeName + "' attribute for TypeEarth::class");
                }

                TypeEarth earth = (TypeEarth) card.getType();
                earth.setHide(Float.parseFloat(attributeValue));

                break;

            case "Burn": // TypeFire
                if (!(card.getType() instanceof TypeFire)) {
                    throw new Exception("Error configuration : Undefined '" + attributeName + "' attribute for TypeFire::class");
                }

                TypeFire fire = (TypeFire) card.getType();
                fire.setBurn(Float.parseFloat(attributeValue));

                break;
            
            case "Poison": // TypeInsect
                if (!(card.getType() instanceof TypeInsect)) {
                    throw new Exception("Error configuration : Undefined '" + attributeName + "' attribute for TypeInsect::class");
                }

                TypeInsect insect = (TypeInsect) card.getType();
                insect.setPoison(Float.parseFloat(attributeValue));

                break;

            case "Cure": // TypePlant
                if (!(card.getType() instanceof TypePlant)) {
                    throw new Exception("Error configuration : Undefined '" + attributeName + "' attribute for TypePlant::class");
                }

                TypePlant plant = (TypePlant) card.getType();
                plant.setCure(Float.parseFloat(attributeValue));

                break;
            
            case "Flood": // TypeWater
                if (!(card.getType() instanceof TypeWater)) {
                    throw new Exception("Error configuration : Undefined '" + attributeName + "' attribute for TypeWater::class");
                }

                TypeWater water = (TypeWater) card.getType();
                water.setFlood(Float.parseFloat(attributeValue));

                break;
            
            case "Fall": // TypeWater
                if (!(card.getType() instanceof TypeWater)) {
                    throw new Exception("Error configuration : Undefined '" + attributeName + "' attribute for TypeWater::class");
                }

                TypeWater water2 = (TypeWater) card.getType();
                water2.setFall(Float.parseFloat(attributeValue));

                break;

            default:
                throw new Exception("Error configuration: Unknown attribute '" + attributeName + "' for Type::class");
        }
    }
}
