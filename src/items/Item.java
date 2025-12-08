package items;

import characters.Character;

public abstract class Item {
    protected final String name;
    protected final String description;
    protected Character owner;
    protected int value;

    public Item(String name, String description, Character owner, int value) {
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

    public void setOwner(Character owner) {
        transferTo(owner);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void transferTo(Character character) {
        if (owner != null) {
            owner.removeItemFromBag(this);
        }
        owner = character;
        character.addItemToBag(this);
    }
}
