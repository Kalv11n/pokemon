package instanciator;

import entity.Monster;
import exception.InstanciatorErrorException;

public class MonsterInstanciator extends Instanciator {

    public void implement(Monster monster, String[] args) throws InstanciatorErrorException {
        if (monster == null) {
            throw new InstanciatorErrorException("Value not affected to a monster");
        }

        String attributeName = args[0];
        String attributeValue = args[1];

        // Check and set Type attributes
        if (!hasAttribute(monster, attributeName)) {
            this.setTypeAttribute(monster, attributeName, attributeValue);
            return;
        }

        // Set other Monster attributes
        this.setMonsterAttribute(monster, attributeName, args);
    }

    private void setMonsterAttribute(Monster monster, String attributeName, String[] attributeValue) throws InstanciatorErrorException {
        switch (attributeName) {
            case "Name":
                monster.setName(attributeValue[1]);
                break;

            case "Type":
                // Assuming selectType is implemented
                this.selectType(monster, attributeValue[1]);
                break;

            case "HP":
                monster.setHp(getRandNumberInInterval(Integer.parseInt(attributeValue[1]), Integer.parseInt(attributeValue[2])));
                break;

            case "Speed":
                monster.setSpeed(getRandNumberInInterval(Integer.parseInt(attributeValue[1]), Integer.parseInt(attributeValue[2])));
                break;

            case "Attack":
                monster.setAttack(getRandNumberInInterval(Integer.parseInt(attributeValue[1]), Integer.parseInt(attributeValue[2])));
                break;

            case "Defense":
                monster.setDefense(getRandNumberInInterval(Integer.parseInt(attributeValue[1]), Integer.parseInt(attributeValue[2])));
                break;

            default:
                throw new InstanciatorErrorException("Error configuration: Unknown attribute '" + attributeName + "' for Monster::class");
        }
    }
}
