package locations.districts;

import buildings.Building;
import characters.Character;
import enums.BuildingType;
import enums.LocationType;
import locations.Location;
import locations.fortress.Gate;

import java.util.ArrayList;
import java.util.List;

public class City extends Location {
    protected List<Gate> gates;
    protected Country country;
    protected List<Building> buildings;

    public City(String name, String description, Location parentLocation, List<Character> characters,
            List<Location> neighboringLocations, List<Gate> gates, Country country, List<Building> buildings) {
        super(name, description, LocationType.City, parentLocation, characters, neighboringLocations);
        this.gates = gates;
        this.country = country;
        this.buildings = buildings;
    }

    public City(String name, String description, Location parentLocation) {
        super(name, description, LocationType.City, parentLocation, new ArrayList<>(), new ArrayList<>());
        this.gates = new ArrayList<>();
        this.country = null;
        this.buildings = new ArrayList<>();
    }

    public List<Gate> getGates() {
        return gates;
    }

    public Country getCountry() {
        return country;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public Building findBuildingByType(BuildingType type) {
        for (Building building : buildings) {
            if (building.getBuildingType() == type) {
                return building;
            }
        }
        return null;
    }

    public void openGates() {
        for (Gate gate : gates) {
            gate.open(gate.getGuards().get(0));
        }
    }

    public void openGate(Gate gate) {
        gate.open(gate.getGuards().get(0));
    }
}
