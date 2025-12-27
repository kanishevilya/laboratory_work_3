package items.magical;

import characters.Character;
import enums.Material;
import items.Item;
import exceptions.InventoryFullException;

public class MagicalRing extends Item {
    protected final int powerLvl;
    protected final Material material;
    protected final int influenceOnOwner;
    protected boolean isEquipped;

    public MagicalRing(String name, String description, Character owner, int value, int powerLvl, Material material,
            int influenceOnOwner, boolean isEquipped) throws InventoryFullException {
        super(name, description, owner, value);
        this.powerLvl = powerLvl;
        this.material = material;
        this.influenceOnOwner = influenceOnOwner;
        this.isEquipped = isEquipped;
    }

    public int getPowerLvl() {
        return powerLvl;
    }

    public Material getMaterial() {
        return material;
    }

    public int getInfluenceOnOwner() {
        return influenceOnOwner;
    }

    public boolean isEquipped() {
        return isEquipped;
    }

    public void equip() {
        if (isEquipped()) {
            return;
        }
        isEquipped = true;
        owner.equipItem(this);
        setEffects(10);
    }

    protected void setEffects(int additionalValue) {
        int addValue = additionalValue * getInfluenceOnOwner() * getPowerLvl();
        addValue += getMaterial() == Material.MagicMetal ? 10 : 0;
        owner.setHealth(owner.getHealth() - addValue);
        owner.setFatigue(owner.getFatigue() + addValue);
    }

    public void unEquip() {
        isEquipped = false;
        owner.unEquipItem(this);
        setEffects(-10);
    }
}
