package instanciator;

import entity.Attack;

public class AttackInstanciator extends Instanciator {

    public void implement(Attack attack, String[] args) throws Exception {
        if (attack == null) {
            throw new Exception("Value not affected to an attack");
        }

        String attributeName = args[0];
        String attributeValue = args[1];

        // Check and set Type attributes
        if (!hasAttribute(attack, attributeName)) {
            this.setTypeAttribute(attack, attributeName, attributeValue);
            return;
        }

        // Set other Attack attributes
        this.setAttackAttribute(attack, attributeName, attributeValue);
    }

    private void setAttackAttribute(Attack attack, String attributeName, String attributeValue) throws Exception {
        switch (attributeName) {
            case "Name":
                attack.setName(attributeValue);
                break;

            case "Type":
                // Assuming selectType is implemented
                this.selectType(attack, attributeValue);
                break;

            case "Power":
                attack.setPower(Integer.parseInt(attributeValue));
                break;

            case "NbUse":
                attack.setNbuse(Integer.parseInt(attributeValue));
                break;

            case "Fail":
                attack.setFail(Float.parseFloat(attributeValue));
                break;

            default:
                throw new Exception("Error configuration: Unknown attribute '" + attributeName + "' for Attack::class");
        }
    }
}
