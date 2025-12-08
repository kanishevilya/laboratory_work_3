package items.weapon;

import characters.Character;

public interface WeaponPrototype {
    WeaponPrototype clone(Character owner);
}
