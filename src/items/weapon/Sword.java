package items.weapon;

import characters.Character;
import enums.Material;
import items.Item;

public class Sword extends Item implements WeaponPrototype {
    protected int sharpness;
    protected int weight;
    protected Material material;

    public Sword(String name, String description, Character owner, int value, int sharpness, int weight,
            Material material) {
        super(name, description, owner, value);
        this.sharpness = sharpness;
        this.weight = weight;
        this.material = material;
    }

    private Sword(Sword sword, Character owner) {
        super(sword.getName(), sword.getDescription(), owner, sword.getValue());
        this.sharpness = sword.sharpness;
        this.weight = sword.weight;
        this.material = sword.material;
    }

    public Material getMaterial() {
        return material;
    }

    public int getSharpness() {
        return sharpness;
    }

    public void setSharpness(int sharpness) {
        this.sharpness = sharpness;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Sword clone(Character owner) {
        return new Sword(this, owner);
    }
}
