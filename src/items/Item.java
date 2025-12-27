package items;

import characters.Character;
import exceptions.InventoryFullException;

public abstract class Item {
    protected final String name;
    protected final String description;
    protected Character owner;
    protected int value;

    public Item(String name, String description, Character owner, int value) throws InventoryFullException {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.value = value;
        if (owner != null) {
            owner.addItemToBag(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Character getOwner() {
        return owner;
    }

    public void setOwnerWithTransfer(Character owner) {
        this.owner = owner;
    }

    public void setOwner(Character owner) throws InventoryFullException {
        transferTo(owner);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void transferTo(Character character) throws InventoryFullException {
        if (owner != null) {
            owner.removeItemFromBag(this);
        }
        owner = character;
        character.addItemToBag(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Item))
            return false;
        Item item = (Item) o;
        return value == item.value &&
                name.equals(item.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = result + (owner != null ? owner.getName().hashCode() : 0);
        result = result + value;
        return result;
    }

    @Override
    public String toString() {
        return "Название: "+this.getName()+" Описание: "+this.getDescription();
    }
}
