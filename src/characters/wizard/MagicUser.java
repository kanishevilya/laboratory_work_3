package characters.wizard;

import characters.Character;
import items.magical.Spell;

public interface MagicUser {
    int getCurrentMana();
    boolean castSpell(Spell spell, Character character);
    void rechangeMana();
}
