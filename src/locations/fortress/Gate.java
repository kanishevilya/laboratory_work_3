package locations.fortress;

import characters.Character;
import characters.human.Knight;
import enums.LocationType;
import locations.Location;

import java.util.ArrayList;
import java.util.List;

public class Gate extends Location {
    protected boolean isLocked;
    protected List<Knight> guards;

    public Gate(String name, String description, Location parentLocation, List<Character> characters,
            List<Location> neighboringLocations, boolean isLocked, List<Knight> guards) {
        super(name, description, LocationType.Gate, parentLocation, characters, neighboringLocations);
        this.isLocked = isLocked;
        this.guards = guards;
    }

    public Gate(String name, String description, Location parentLocation) {
        super(name, description, LocationType.Gate, parentLocation, new ArrayList<>(), new ArrayList<>());
        this.isLocked = true;
        this.guards = new ArrayList<>();
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public List<Knight> getGuards() {
        return guards;
    }

    public void addGuard(Knight guard) {
        guards.add(guard);
    }

    public void open(Character character) {
        if (checkAccess(character)) {
            isLocked = false;
        }
    }

    public void close() {
        isLocked = true;
    }

    public boolean checkAccess(Character character) {
        return character.hasAccessTo(this);
    }
}
