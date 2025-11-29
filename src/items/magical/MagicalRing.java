package items.magical;

import characters.Character;
import enums.Effect;
import enums.Material;
import items.Item;

public class MagicalRing extends Item {
    protected int powerLvl;
    protected Effect specialEffect;
    protected Material material;
    protected int influenceOnOwner;
    protected boolean isEquipped;

    public MagicalRing(String name, String description, Character owner, int value, int powerLvl, Effect specialEffect, Material material, int influenceOnOwner, boolean isEquipped) {
        super(name, description, owner, value);
        this.powerLvl = powerLvl;
        this.specialEffect = specialEffect;
        this.material = material;
        this.influenceOnOwner = influenceOnOwner;
        this.isEquipped = isEquipped;
    }

    public int getPowerLvl() {
        return powerLvl;
    }

    public Effect getSpecialEffect() {
        return specialEffect;
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

    public void setPowerLvl(int powerLvl) {
        this.powerLvl = powerLvl;
    }

    public void equip(){
        isEquipped=true;
        owner.equipItem(this);
    }

    public void unEquip(){
        isEquipped=false;
        owner.unEquipItem(this);
    }
}
