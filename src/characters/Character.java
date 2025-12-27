package characters;

import buildings.Building;
import exceptions.InventoryFullException;
import items.Item;
import items.economy.CoinPurse;
import locations.Location;

import java.util.ArrayList;
import java.util.List;

import global.Event;

public abstract class Character {
    protected final String name;
    protected int health;
    protected final int maxHealth;
    protected Location currentLocation;
    protected boolean isAlive;

    protected final Inventory inventory;
    protected final DialogueHelper dialogueHelper;

    protected final CharacterState fearState;
    protected final CharacterState rageState;
    protected int fatigue;

    protected final List<Location> inaccessibleLocations;

    public Character(String name, int health, Location currentLocation, boolean isAlive, List<Item> bag, boolean isFear,
            int fatigue, CoinPurse coinPurse) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.currentLocation = currentLocation;
        this.isAlive = isAlive;
        this.fearState = new CharacterState(isFear);
        this.rageState = new CharacterState();

        this.inventory = new Inventory(this, bag, coinPurse);
        this.setEquipment(new ArrayList<>());
        this.dialogueHelper = new DialogueHelper();

        this.fatigue = fatigue;
        this.inaccessibleLocations = new ArrayList<>();
    }

    public void equipItem(Item item) {
        inventory.equipItem(item);
    }

    public void unEquipItem(Item item) {
        inventory.unEquipItem(item);
    }

    public boolean isEquip(Item item) {
        return inventory.isEquip(item);
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

    public void addItemToBag(Item item) throws InventoryFullException {
        inventory.addItemToBag(item);
    }

    public void removeItemFromBag(Item item) {
        inventory.removeItemFromBag(item);
    }

    public boolean isFear() {
        return fearState.getStateStatus();
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
        fearState.setState(true, reason);
    }

    public void removeFear() {
        fearState.setState(false, null);
    }

    public void setRage(Event reason) {
        rageState.setState(true, reason);
    }

    public boolean isRage() {
        return rageState.getStateStatus();
    }

    public void removeRage() {
        rageState.setState(false, null);
    }

    public Event getRageReason() {
        return rageState.getStateReason();
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public void heal(int additionalHealth) {
        if (isFear()) {
            removeFear();
        }
        if (health + additionalHealth > maxHealth) {
            health = maxHealth;
        } else {
            health += additionalHealth;
        }
    }

    public void moveTo(Location location) {
        if (inaccessibleLocations.contains(location) || !isAlive()) {
            return;
        }
        boolean isParent = currentLocation.getParentLocation() == location
                || location.getParentLocation() == currentLocation;
        if (!currentLocation.getNeighboringLocations().contains(location) && !isParent) {
            return;
        }
        currentLocation.removeCharacter(this);
        setCurrentLocation(location);
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

    public void observe(Building building) {
        System.out.println(building.observeBuilding());
    }

    public void observe(Location location) {
        System.out.println(location.observeLocation());
    }
}
