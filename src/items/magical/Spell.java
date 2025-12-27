package items.magical;

import enums.SpellType;
import items.magical.effects.AttackSpellEffect;
import items.magical.effects.BuffSpellEffect;
import items.magical.effects.DebuffSpellEffect;
import items.magical.effects.FearSpellEffect;
import items.magical.effects.HealSpellEffect;

public record Spell(String name, int manaCost, SpellEffect effect, int power) {
    public Spell(String name, int manaCost, SpellType spellType, int power) {
        this(name, manaCost, createEffectFromType(spellType), power);
    }

    private static SpellEffect createEffectFromType(SpellType type) {
        return switch (type) {
            case Attack -> new AttackSpellEffect();
            case Heal -> new HealSpellEffect();
            case Fear -> new FearSpellEffect();
            case Buff -> new BuffSpellEffect();
            case Debuff -> new DebuffSpellEffect();
        };
    }
}
