package characters;

import items.Item;
import items.economy.CoinPurse;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Character owner;
    private List<Item> bag;
    private List<Item> equipment;
    private CoinPurse coinPurse;

    public Inventory(Character owner, List<Item> bag, CoinPurse coinPurse) {
        this.owner = owner;
        this.bag = bag != null ? bag : new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.coinPurse = coinPurse;
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

    public List<Item> getBag() {
        return bag;
    }

    public void addItemToBag(Item item) {
        item.setOwnerWithTransfer(owner);
        bag.add(item);
    }

    public void removeItemFromBag(Item item) {
        item.setOwnerWithTransfer(null);
        bag.remove(item);
    }

    public CoinPurse getCoinPurse() {
        return coinPurse;
    }
}
