package locations.fortress;

import characters.Character;
import characters.human.Knight;
import enums.LocationType;
import locations.Location;

import java.util.ArrayList;
import java.util.List;

public class Gate extends Location {
    protected boolean isLocked;
    protected final List<Knight> guards;

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
            setLocked(false);
        }
    }

    public void close() {
        setLocked(true);
    }

    public boolean checkAccess(Character character) {
        return character.hasAccessTo(this);
    }
}
