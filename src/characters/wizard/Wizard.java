package characters.wizard;

import characters.Character;
import exceptions.InventoryFullException;
import global.ManaSource;
import items.economy.CoinPurse;
import items.magical.Spell;
import items.magical.Staff;
import locations.Location;

import java.util.ArrayList;

public class Wizard extends Character implements MagicUser {
    protected final String title;
    protected Staff staff;
    protected int magicalRank;
    protected final int maxOneTimeManaForUse;
    protected int magicalDefence;
    protected int currentMana;
    protected final int maxMana;

    public Wizard(String name, int health, Location currentLocation, CoinPurse coinPurse, String title, Staff staff,
            int magicalRank, int maxOneTimeManaForUse,
            int magicalDefence, int currentMana, int maxMana) {
        super(name, health, currentLocation, true, new ArrayList<>(), false, 0, coinPurse);
        this.title = title;
        this.staff = staff;
        this.magicalRank = magicalRank;
        this.maxOneTimeManaForUse = maxOneTimeManaForUse;
        this.magicalDefence = magicalDefence;
        this.currentMana = currentMana;
        this.maxMana = maxMana;
    }

    public String getTitle() {
        return title;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) throws InventoryFullException {
        this.staff = staff;
        staff.setOwner(this);
    }

    public int getMagicalRank() {
        return magicalRank;
    }

    public void setMagicalRank(int magicalRank) {
        this.magicalRank = magicalRank;
    }

    public int getMaxOneTimeManaForUse() {
        return maxOneTimeManaForUse;
    }

    @Override
    public int getCurrentMana() {
        return currentMana;
    }

    public int getMagicalDefence() {
        return magicalDefence;
    }

    public void setMagicalDefence(int magicalDefence) {
        this.magicalDefence = magicalDefence;
    }

    @Override
    public boolean castSpell(Spell spell, Character character) {
        rechangeMana();

        if (spell.manaCost() > getMaxOneTimeManaForUse() || spell.manaCost() > getCurrentMana()) {
            return false;
        }

        if (currentMana >= spell.manaCost()) {
            currentMana -= spell.manaCost();

            int spellPower = spell.power();

            if (getStaff() != null) {
                spellPower += getStaff().amplifySpell(spell);
                getStaff().invokeSpecialEffect(character);
            }

            spell.effect().apply(this, character, spellPower, getStaff());

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void rechangeMana() {
        int amountToTake = maxMana - currentMana;
        int taken = ManaSource.getInstance().takeMana(currentLocation, amountToTake);
        currentMana += taken;
    }

}
