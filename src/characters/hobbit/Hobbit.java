package characters.hobbit;

import characters.Character;
import items.Item;
import items.economy.CoinPurse;
import locations.Location;
import exceptions.InventoryFullException;
import java.util.ArrayList;

public class Hobbit extends Character {
    protected int stealth;
    protected final int theftRate;
    protected int thirstForFood;

    public Hobbit(String name, int health, Location currentLocation, CoinPurse coinPurse, int stealth, int theftRate,
            int thirstForFood) {
        super(name, health, currentLocation, true, new ArrayList<>(), false, 0, coinPurse);
        this.stealth = stealth;
        this.theftRate = theftRate;
        this.thirstForFood = thirstForFood;
    }

    public int getStealth() {
        return stealth;
    }

    public void setStealth(int stealth) {
        this.stealth = stealth;
    }

    public boolean steal(Item item) throws InventoryFullException {
        if (!canAct()) {
            return false;
        }

        if (item.getOwner() != this && (theftRate * Math.random() + getStealth()) > 6) {
            item.transferTo(this);
            return true;
        }
        return false;
    }

    @Override
    public void moveTo(locations.Location location) {
        super.moveTo(location);
        thirstForFood = Math.min(100, thirstForFood + 5);
        checkHunger();
    }

    public boolean canAct() {
        return thirstForFood <= 90;
    }

    private void checkHunger() {
        if (thirstForFood > 80) {
            setFatigue(getFatigue() + 10);
        }
        if (thirstForFood > 95) {
            setHealth(getHealth() - 5);
        }
    }

    public int getThirstForFood() {
        return thirstForFood;
    }

    public void setThirstForFood(int thirstForFood) {
        this.thirstForFood = thirstForFood;
    }
}
