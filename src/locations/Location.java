package locations;

import enums.LocationType;
import characters.Character;

import java.util.ArrayList;
import java.util.List;

public abstract class Location {
    protected String name;
    protected String description;
    protected LocationType locationType;
    protected Location parentLocation;
    protected List<Character> characters;
    protected List<Location> neighboringLocations;

    public Location(String name, String description, LocationType locationType, Location parentLocation,
            List<Character> characters, List<Location> neighboringLocations) {
        this.name = name;
        this.description = description;
        this.locationType = locationType;
        this.parentLocation = parentLocation;
        List<Character> initialCharacters = new ArrayList<>(characters);
        this.characters = new ArrayList<>(characters);

        for (Character character : initialCharacters) {
            character.moveTo(this);
        }
        this.neighboringLocations = neighboringLocations;
        for (Location neighboringLocation : neighboringLocations) {
            neighboringLocation.addNeighboringLocation(this);
        }
    }

    public String getName() {
        return name;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public String getDescription() {
        return description;
    }

    public Location getParentLocation() {
        return parentLocation;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public List<Location> getNeighboringLocations() {
        return neighboringLocations;
    }

    public void setParentLocation(Location parentLocation) {
        this.parentLocation = parentLocation;
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    public void addNeighboringLocation(Location location) {
        if (!neighboringLocations.contains(location)) {
            neighboringLocations.add(location);
            location.addNeighboringLocation(this);
        }
    }
}
