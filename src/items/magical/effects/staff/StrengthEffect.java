package items.magical.effects.staff;

import characters.Character;
import characters.human.Human;
import characters.wizard.Wizard;

public class StrengthEffect implements StaffEffect {
    @Override
    public void apply(Character target, int powerLvl) {
        if (target instanceof Human human) {
            human.setStrength(human.getStrength() + powerLvl);
        } else if (target instanceof Wizard wizard) {
            wizard.setMagicalRank(wizard.getMagicalRank() + powerLvl);
        } else {
            target.heal(powerLvl);
        }
    }
}


