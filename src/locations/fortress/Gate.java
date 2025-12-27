package locations.fortress;

import characters.Character;
import characters.human.Knight;
import enums.LocationType;
import exceptions.AccessDeniedException;
import locations.Location;
import locations.districts.Country;

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
        if (!character.hasAccessTo(this)) {
            throw new AccessDeniedException(character, this, "Нет разрешения на вход");
        }

        if (character instanceof Knight knight) {
            Location parent = getParentLocation();
            Country gateCountry = null;

            if (parent instanceof Country) {
                gateCountry = (Country) parent;
            } else if (parent instanceof Isengard isengard) {
                gateCountry = isengard;
            }

            if (gateCountry != null && knight.getCountryOfSubordinate() != null) {
                boolean hasAccess = gateCountry.getAllies() != null
                        && gateCountry.getAllies().contains(knight.getCountryOfSubordinate())
                        || gateCountry == knight.getCountryOfSubordinate();

                if (!hasAccess) {
                    throw new AccessDeniedException(character, this,
                            "Рыцарь из враждебной страны: " + knight.getCountryOfSubordinate().getName());
                }

                return true;
            }
        }

        return true;
    }
}
