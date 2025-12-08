package buildings.shop;

import buildings.Building;
import characters.Character;
import enums.BuildingType;
import items.Item;
import locations.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop extends Building {
    protected List<Item> availableItems;
    protected Map<Item, Integer> prices;

    public Shop(String name, Location location) {
        super(name, BuildingType.Shop, location, true);
        this.availableItems = new ArrayList<>();
        this.prices = new HashMap<>();
    }

    public List<Item> getAvailableItems() {
        return availableItems;
    }

    public void addItem(Item item, int price) {
        availableItems.add(item);
        prices.put(item, price);
    }

    public void removeItem(Item item) {
        availableItems.remove(item);
        prices.remove(item);
    }

    public int getPrice(Item item) {
        return item.getValue();
    }

    public void setPrice(Item item, int price) {
        if (!availableItems.contains(item)) {
            availableItems.add(item);
        }
        prices.put(item, price);
    }

    public boolean purchaseItem(Character character, Item item) {
        if(availableItems.contains(item) && character.getCoinPurse().spendCopper(item.getValue())){
            item.transferTo(character);
            removeItem(item);
            return true;
        }
        return false;
    }

    public void sellItem(Character character, Item item) {
        character.removeItemFromBag(item);
        addItem(item, item.getValue());
        character.getCoinPurse().addCopper(item.getValue());
    }

}