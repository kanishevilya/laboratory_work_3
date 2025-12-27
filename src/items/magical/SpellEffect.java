package items.magical;

import characters.Character;

public interface SpellEffect {
    void apply(Character caster, Character target, int power, Staff staff);
}
