package items.magical.effects.staff;

import characters.Character;
import characters.human.Human;
import characters.wizard.Wizard;

public class CorruptionEffect implements StaffEffect {
    @Override
    public void apply(Character target, int powerLvl) {
        target.setHealth(Math.max(0, target.getHealth() - powerLvl));

        if (target instanceof Human human) {
            human.setPhysicalDefense(human.getPhysicalDefense() - powerLvl);
            human.setStrength(human.getStrength() - powerLvl);
        }
        if (target instanceof Wizard wizard) {
            wizard.setMagicalDefence(wizard.getMagicalDefence() - powerLvl);
            wizard.setMagicalRank(wizard.getMagicalRank() - powerLvl);
        }
    }
}

