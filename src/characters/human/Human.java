package characters.human;

import characters.Character;
import items.Item;
import items.economy.CoinPurse;
import locations.Location;

import java.util.ArrayList;
import java.util.List;

public class Human extends Character {
    protected int strength;
    protected int greed;
    protected int physicalDefense;

    public Human(String name, int health, Location currentLocation, boolean isAlive, List<Item> bag, boolean isFear,
            int fatigue, CoinPurse coinPurse, int strength, int greed, int physicalDefense) {
        super(name, health, currentLocation, isAlive, bag, isFear, fatigue, coinPurse);
        this.strength = strength;
        this.greed = greed;
        this.physicalDefense = physicalDefense;
    }

    public Human(String name, int health, Location currentLocation, CoinPurse coinPurse, int strength, int greed,
            int physicalDefense) {
        super(name, health, currentLocation, true, new ArrayList<>(), false, 0, coinPurse);
        this.strength = strength;
        this.greed = greed;
        this.physicalDefense = physicalDefense;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getGreed() {
        return greed;
    }

    public void increaseGreed(int additionalGreed) {
        this.greed += additionalGreed;
    }

    public int getPhysicalDefense() {
        return physicalDefense;
    }

    public void setPhysicalDefense(int physicalDefense) {
        this.physicalDefense = physicalDefense;
    }

    public void attack(Character opponent) {
        int damage = strength;

        if (isRage()) {
            damage = (int) (damage * 1.5);
            physicalDefense = (int) (physicalDefense * 0.7);
        }

        if (isFear()) {
            return;
        }

        opponent.setHealth(Math.max(0, opponent.getHealth() - damage));
        if (opponent.getHealth() == 0) {
            opponent.setAlive(false);
        }
    }

}
