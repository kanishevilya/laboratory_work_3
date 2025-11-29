package items.magical;

import enums.SpellType;

public class Spell {
    protected String name;
    protected int manaCost;
    protected SpellType spellType;
    protected int power;

    public Spell(String name, int manaCost, SpellType spellType, int power) {
        this.name = name;
        this.manaCost = manaCost;
        this.spellType = spellType;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public int getPower() {
        return power;
    }
}
