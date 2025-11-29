package items.weapon;

import characters.Character;
import enums.Material;
import items.Item;

public class Armor extends Item implements WeaponPrototype {
    protected int protection;
    protected int weight;
    protected Material material;

    public Armor(String name, String description, Character owner, int value, int protection, int weight,
            Material material) {
        super(name, description, owner, value);
        this.protection = protection;
        this.weight = weight;
        this.material = material;
    }

    private Armor(Armor armor, Character owner) {
        super(armor.getName(), armor.getDescription(), owner, armor.getValue());
        this.protection = armor.protection;
        this.weight = armor.weight;
        this.material = armor.material;
    }

    public Material getMaterial() {
        return material;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Armor clone(Character owner) {
        return new Armor(this, owner);
    }
}
