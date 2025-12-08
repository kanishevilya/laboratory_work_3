package locations.districts;

import enums.LocationType;
import locations.Location;

import java.util.ArrayList;
import java.util.List;

public class Country extends Location {
    protected final List<City> cities;
    protected final List<Country> allies;

    public Country(String name, String description, List<City> cities, List<Country> allies) {
        super(name, description, LocationType.Country, null, new ArrayList<>(), new ArrayList<>());
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
