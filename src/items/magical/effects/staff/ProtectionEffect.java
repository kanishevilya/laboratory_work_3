package items.magical.effects.staff;

import characters.wizard.Wizard;
import characters.human.Human;
import characters.Character;

public class ProtectionEffect implements StaffEffect {

    @Override
    public void apply(Character target, int powerLvl) {
        if (target instanceof Human human) {
            human.setPhysicalDefense(human.getPhysicalDefense() + powerLvl);
        } else if (target instanceof Wizard wizard) {
            wizard.setMagicalDefence(wizard.getMagicalDefence() + powerLvl);
        } else {
            target.heal(powerLvl);
        }
    }
}

