package items.magical.effects;

import characters.Character;
import enums.Reason;
import global.Event;
import items.magical.SpellEffect;
import items.magical.Staff;

import java.util.List;

public class FearSpellEffect implements SpellEffect {
    @Override
    public void apply(Character caster, Character target, int power, Staff staff) {
        target.setFear(new Event(List.of(caster, target), target.getCurrentLocation(),
                Reason.Magic, "Fear spell cast by " + caster.getName()));
    }
}
