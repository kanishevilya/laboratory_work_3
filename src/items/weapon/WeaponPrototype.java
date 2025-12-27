package items.weapon;

import characters.Character;
import exceptions.InventoryFullException;

public interface WeaponPrototype {
    WeaponPrototype clone(Character owner) throws InventoryFullException;
}
