package buildings.tavern;

import buildings.Building;
import characters.Character;
import characters.hobbit.Hobbit;
import enums.BuildingType;
import locations.Location;

public class Tavern extends Building {
    protected int availableRooms;
    protected final int roomPrice;
    protected final int mealPrice;
    protected Character owner;

    public Tavern(String name, Location location, int availableRooms,
            int roomPrice, int mealPrice, Character owner) {
        super(name, BuildingType.Tavern, location, true);
        this.availableRooms = availableRooms;
        this.roomPrice = roomPrice;
        this.mealPrice = mealPrice;
        this.owner = owner;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public Character getOwner() {
        return owner;
    }

    public void changeOwner(Character owner) {
        this.owner = owner;
    }

    public void rentRoom(Character character, int nights) {
        if (getAvailableRooms() <= 0) {
            return;
        }

        int totalCost = getRoomPrice() * nights;
        if (character == getOwner() || character.getCoinPurse().spendCopper(totalCost)) {
            availableRooms--;
            character.setFatigue(0);
            character.heal(10 * nights);
        }
    }

    public void serveMeal(Character character) {
        if (character.getCoinPurse().spendCopper(mealPrice)) {
            character.setFatigue(Math.max(0, character.getFatigue() - 20));
            if (character instanceof Hobbit hobbit) {
                hobbit.setThirstForFood(Math.max(0, hobbit.getThirstForFood() - 30));
            }
        }
    }

}
