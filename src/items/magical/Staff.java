package items.magical;

import characters.Character;

import enums.Material;
import items.Item;
import exceptions.InventoryFullException;
import items.magical.effects.staff.StaffEffect;

public class Staff extends Item {
    protected final int powerLvl;
    protected final Material material;
    protected final StaffEffect effect;

    public Staff(String name, String description, Character owner, int value,
                 int powerLvl, Material material, StaffEffect effect)
            throws InventoryFullException {
        super(name, description, owner, value);
        this.powerLvl = powerLvl;
        this.material = material;
        this.effect = effect;
    }

    public int amplifySpell(Spell spell) { return (material == Material.MagicWood ? 5 : 0) + spell.power() + powerLvl; }

    public void invokeSpecialEffect(Character target) {
        effect.apply(target, powerLvl);
    }
}
