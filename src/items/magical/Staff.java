package items.magical;

import characters.Character;
import characters.human.Human;
import characters.wizard.Wizard;
import enums.Effect;
import enums.Material;
import items.Item;
import exceptions.InventoryFullException;

public class Staff extends Item {
    protected final int powerLvl;
    protected final Effect effect;
    protected final Material material;

    public Staff(String name, String description, Character owner, int value, int powerLvl, Effect effect,
            Material material) throws InventoryFullException {
        super(name, description, owner, value);
        this.powerLvl = powerLvl;
        this.effect = effect;
        this.material = material;
    }

    public int amplifySpell(Spell spell) {
        return (material == Material.MagicWood ? 5 : 0) + spell.power() + powerLvl;
    }

    public void invokeSpecialEffect(Character target) {
        switch (effect) {
            case Protection:
                if (target instanceof Human human) {
                    human.setPhysicalDefense(human.getPhysicalDefense() + powerLvl);
                } else if (target instanceof Wizard wizard) {
                    wizard.setMagicalDefence(wizard.getMagicalDefence() + powerLvl);
                } else {
                    target.heal(powerLvl);
                }
                break;
            case Strength:
                if (target instanceof Human human) {
                    human.setStrength(human.getStrength() + powerLvl);
                } else if (target instanceof Wizard wizard) {
                    wizard.setMagicalRank(wizard.getMagicalRank() + powerLvl);
                } else {
                    target.heal(powerLvl);
                }
                break;
            case Corruption:
                target.setHealth(Math.max(0, target.getHealth() - powerLvl));
                if (target instanceof Human human) {
                    human.setPhysicalDefense(human.getPhysicalDefense() - powerLvl);
                    human.setStrength(human.getStrength() - powerLvl);
                }
                if (target instanceof Wizard wizard) {
                    wizard.setMagicalDefence(wizard.getMagicalDefence() - powerLvl);
                    wizard.setMagicalRank(wizard.getMagicalRank() - powerLvl);
                }
                break;
            default:
                break;
        }
    }
}
