package items.magical;

import enums.SpellType;

public record Spell (String name, int manaCost, SpellType spellType, int power){}
