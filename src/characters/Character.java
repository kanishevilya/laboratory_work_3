package characters;

import items.Item;
import items.economy.CoinPurse;
import locations.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import global.Event;
import global.EventsManager;

public abstract class Character {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected Location currentLocation;
    protected boolean isAlive;

    protected Inventory inventory;
    protected DialogueHelper dialogueHelper;

    protected boolean isFear;
    protected Event eventForFear;
    protected boolean isRage;
    protected Event eventForRage;
    protected int fatigue;

    protected List<Location> inaccessibleLocations;

    public Character(String name, int health, Location currentLocation, boolean isAlive, List<Item> bag, boolean isFear,
            int fatigue, CoinPurse coinPurse) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.currentLocation = currentLocation;
        this.isAlive = isAlive;
        this.isFear = isFear;

        this.inventory = new Inventory(this, bag, coinPurse);
        this.dialogueHelper = new DialogueHelper();

        this.eventForFear = null;
        this.isRage = false;
        this.eventForRage = null;
        this.fatigue = fatigue;
        this.inaccessibleLocations = new ArrayList<>();
    }

    public void equipItem(Item item) {
        inventory.equipItem(item);
    }

    public void unEquipItem(Item item) {
        inventory.unEquipItem(item);
    }

    public List<Item> getEquipment() {
        return inventory.getEquipment();
    }

    public void setEquipment(List<Item> equipment) {
        this.inventory.setEquipment(equipment);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public List<Item> getBag() {
        return inventory.getBag();
    }

    public void addItemToBag(Item item) {
        inventory.addItemToBag(item);
    }

    public void removeItemFromBag(Item item) {
        inventory.removeItemFromBag(item);
    }

    public boolean isFear() {
        return isFear;
    }

    public int getFatigue() {
        return fatigue;
    }

    public CoinPurse getCoinPurse() {
        return inventory.getCoinPurse();
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setFear(Event reason) {
        eventForFear = reason;
        isFear = true;
        EventsManager.getInstance().registerEvent(reason);
    }

    public void setRage(Event reason) {
        eventForRage = reason;
        isRage = true;
        EventsManager.getInstance().registerEvent(reason);
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public Map<Character, List<String>> getReceivedMessages() {
        return dialogueHelper.getReceivedMessages();
    }


    public void heal(int additionalHealth) {
        if (health + additionalHealth > maxHealth) {
            health = maxHealth;
        } else {
            health += additionalHealth;
        }
    }

    public void moveTo(Location location) {
        if (!hasAccessTo(location)) {
            return;
        }
        boolean isNeighbor = currentLocation.getNeighboringLocations().contains(location);
        boolean isParent = currentLocation.getParentLocation() == location
                || location.getParentLocation() == currentLocation;
        if (!isNeighbor && !isParent) {
            return;
        }
        currentLocation.removeCharacter(this);
        currentLocation = location;
        currentLocation.addCharacter(this);
    }

    public void speak(Character recipient, String message) {
        dialogueHelper.speak(recipient, message);
    }

    public void viewReceivedMessages() {
        dialogueHelper.viewReceivedMessages();
    }

    public boolean hasAccessTo(Location location) {
        return !inaccessibleLocations.contains(location);
    }

}
