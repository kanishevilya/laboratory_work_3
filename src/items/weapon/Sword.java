package items.weapon;

import characters.Character;
import enums.Material;
import items.Item;
import exceptions.InventoryFullException;

public class Sword extends Item implements WeaponPrototype {
    protected int sharpness;
    protected int weight;
    protected final Material material;

    public Sword(String name, String description, Character owner, int value, int sharpness, int weight,
            Material material) throws InventoryFullException {
        super(name, description, owner, value);
        this.sharpness = sharpness;
        this.weight = weight;
        this.material = material;
    }

    private Sword(Sword sword, Character owner) throws InventoryFullException {
        super(sword.getName(), sword.getDescription(), owner, sword.getValue());
        this.setSharpness(sword.getSharpness());
        this.setWeight(sword.getWeight());
        this.material = sword.getMaterial();
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

    @Override
    public Sword clone(Character owner) throws InventoryFullException {
        return new Sword(this, owner);
    }
}
