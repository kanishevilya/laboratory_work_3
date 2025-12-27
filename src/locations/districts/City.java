package locations.districts;

import buildings.Building;
import enums.BuildingType;
import enums.LocationType;
import locations.Location;
import locations.fortress.Gate;

import java.util.ArrayList;
import java.util.List;

public class City extends Location {
    protected final List<Gate> gates;
    protected final Country country;
    protected final List<Building> buildings;

    public City(String name, String description, Location parentLocation, Country country) {
        super(name, description, LocationType.City, parentLocation, new ArrayList<>(), new ArrayList<>());
        this.gates = new ArrayList<>();
        this.country = country;
        this.buildings = new ArrayList<>();
    }

    public List<Gate> getGates() {
        return gates;
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
        for (Gate gate : getGates()) {
            openGate(gate);
        }
    }

    public Country getCountry(){
        return country;
    }

    public void openGate(Gate gate) {
        gate.open(gate.getGuards().get(0));
    }

    @Override
    public String observeLocation(){
        return super.observeLocation()+ " Country: "+getCountry();
    }
}
