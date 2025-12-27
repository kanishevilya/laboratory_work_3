package characters.human;

import characters.Character;
import enums.BuildingType;
import items.Item;
import items.economy.CoinPurse;
import items.weapon.Armor;
import items.weapon.Sword;
import locations.Location;
import locations.districts.City;
import locations.districts.Country;

import java.util.ArrayList;
import java.util.List;

import buildings.Building;
import buildings.training.TrainingCamp;
import exceptions.InventoryFullException;

public class Knight extends Human {
    protected int fencingSkillsLvl;
    protected final int maxFencingSkillsLvl;
    protected Armor armor;
    protected Sword sword;
    protected Country countryOfSubordinate;

    public Knight(String name, int health, Location currentLocation, boolean isAlive, List<Item> bag, boolean isFear,
            int fatigue, CoinPurse coinPurse, int strength, int greed, int physicalDefense, int fencingSkillsLvl,
            int maxFencingSkillsLvl, Armor armor, Sword sword, Country countryOfSubordinate)
            throws InventoryFullException {
        super(name, health, currentLocation, isAlive, bag, isFear, fatigue, coinPurse, strength, greed,
                physicalDefense);
        this.fencingSkillsLvl = fencingSkillsLvl;
        this.maxFencingSkillsLvl = maxFencingSkillsLvl;
        this.armor = armor == null ? null : armor.clone(this);
        this.sword = sword == null ? null : sword.clone(this);
        this.countryOfSubordinate = countryOfSubordinate;
    }

    public Knight(String name, int health, Location currentLocation, CoinPurse coinPurse, int strength, int greed,
            int physicalDefense, int fencingSkillsLvl, int maxFencingSkillsLvl, Armor armor, Sword sword,
            Country countryOfSubordinate) throws InventoryFullException {
        this(name, health, currentLocation, true, new ArrayList<>(), false, 0, coinPurse, strength, greed,
                physicalDefense, fencingSkillsLvl, maxFencingSkillsLvl, armor, sword, countryOfSubordinate);
    }

    public Knight(String name, int health, Location currentLocation, CoinPurse coinPurse, int strength, int greed,
            int physicalDefense, int fencingSkillsLvl, int maxFencingSkillsLvl,
            Country countryOfSubordinate) throws InventoryFullException {
        this(name, health, currentLocation, true, new ArrayList<>(), false, 0, coinPurse, strength, greed,
                physicalDefense, fencingSkillsLvl, maxFencingSkillsLvl, null, null, countryOfSubordinate);
    }

    public int getFencingSkillsLvl() {
        return fencingSkillsLvl;
    }

    public Country getCountryOfSubordinate() {
        return countryOfSubordinate;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Sword getSword() {
        return sword;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    public void learnFencing() {
        if (fencingSkillsLvl >= maxFencingSkillsLvl) {
            return;
        }

        if (currentLocation instanceof City && ((City) currentLocation).getBuildings() != null) {
            Building building = ((City) currentLocation).findBuildingByType(BuildingType.TrainingCamp);
            if (building != null) {
                TrainingCamp camp = (TrainingCamp) building;
                if (camp.isOpen()) {
                    camp.train(this);
                }
            }
        }
    }

    public void increaseFencingSkill() {
        if (getFencingSkillsLvl() < maxFencingSkillsLvl) {
            fencingSkillsLvl++;
        }
    }

    @Override
    public void attack(Character opponent) {
        if (isFear()) {
            return;
        }

        int weaponDamage = (sword != null) ? sword.getSharpness() : 0;
        int totalDamage = strength + weaponDamage + getFencingSkillsLvl();

        if (isRage()) {
            totalDamage = (int) (totalDamage * 1.5);
        }

        int opponentDefense = opponent instanceof Human ? ((Human) opponent).getPhysicalDefense() : 0;
        if (opponent instanceof Knight) {
            Armor oppArmor = ((Knight) opponent).getArmor();
            if (oppArmor != null) {
                opponentDefense += oppArmor.getProtection();
            }
        }

        int actualDamage = Math.max(0, totalDamage - opponentDefense);
        opponent.setHealth(opponent.getHealth() - actualDamage);
    }

    public void changeCountryOfSubordinate(Country country) {
        countryOfSubordinate = country;
    }
}
