package items.weapon;

import characters.Character;

public interface WeaponPrototype {
    public WeaponPrototype clone(Character owner);
}
