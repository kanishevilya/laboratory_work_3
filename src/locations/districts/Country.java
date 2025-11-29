package locations.districts;

import characters.Character;
import enums.LocationType;
import locations.Location;

import java.util.List;

public class Country extends Location {
    protected List<City> cities;
    protected List<Country> allies;

    public Country(String name, String description, Location parentLocation, List<Character> characters, List<Location> neighboringLocations, List<City> cities, List<Country> allies) {
        super(name, description, LocationType.Country, parentLocation, characters, neighboringLocations);
        this.cities = cities;
        this.allies = allies;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<Country> getAllies() {
        return allies;
    }

    public void addCity(City city){
        cities.add(city);
    }

    public void addAlly(Country ally){
        allies.add(ally);
    }
    public void removeAlly(Country ally){
        allies.remove(ally);
    }
}
