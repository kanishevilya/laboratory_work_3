package items.magical.effects;

import characters.Character;
import items.magical.SpellEffect;
import items.magical.Staff;

public class HealSpellEffect implements SpellEffect {
    @Override
    public void apply(Character caster, Character target, int power, Staff staff) {
        target.heal(power);
    }
}
