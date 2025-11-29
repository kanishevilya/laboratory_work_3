package characters;

import items.Item;
import items.economy.CoinPurse;
import locations.Location;

import java.util.ArrayList;
import java.util.HashMap;
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
    protected List<Item> bag;
    protected List<Item> equipment;

    protected boolean isFear;
    protected Event eventForFear;
    protected boolean isRage;
    protected Event eventForRage;
    protected int fatigue;
    protected CoinPurse coinPurse;
    protected Map<Character, List<String>> receivedMessages;

    protected List<Location> inaccessibleLocations;

    public Character(String name, int health, Location currentLocation, boolean isAlive, List<Item> bag, boolean isFear,
            int fatigue, CoinPurse coinPurse) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.currentLocation = currentLocation;
        this.isAlive = isAlive;
        this.bag = bag;
        this.isFear = isFear;
        this.eventForFear = null;
        this.isRage = false;
        this.eventForRage = null;
        this.fatigue = fatigue;
        this.coinPurse = coinPurse;
        this.equipment = new ArrayList<>();
        this.receivedMessages = new HashMap<>();
        this.inaccessibleLocations = new ArrayList<>();
    }

    public void equipItem(Item item) {
        equipment.add(item);
    }

    public void unEquipItem(Item item) {
        equipment.remove(item);
    }

    public List<Item> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Item> equipment) {
        this.equipment = equipment;
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
        return bag;
    }

    public void addItemToBag(Item item) {
        item.setOwnerWithTransfer(this);
        bag.add(item);
    }

    public void removeItemFromBag(Item item) {
        item.setOwnerWithTransfer(null);
        bag.remove(item);
    }

    public boolean isFear() {
        return isFear;
    }

    public int getFatigue() {
        return fatigue;
    }

    public CoinPurse getCoinPurse() {
        return coinPurse;
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
        return receivedMessages;
    }

    public void viewReceivedMessage() {
        for (Character character : receivedMessages.keySet()) {
            System.out.println(character.name + ": ");
            for (String message : receivedMessages.get(character)) {
                System.out.println("\t" + message);
            }
        }
        receivedMessages = new HashMap<>();
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
        Map<Character, List<String>> messages = recipient.getReceivedMessages();
        if (messages.get(recipient) != null) {
            messages.get(recipient).add(message);
        } else {
            List<String> initialMessages = new ArrayList<>();
            initialMessages.add(message);
            messages.put(recipient, initialMessages);
        }
    }

    public boolean hasAccessTo(Location location) {
        return !inaccessibleLocations.contains(location);
    }

}
