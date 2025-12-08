package buildings;

import enums.BuildingType;
import locations.Location;

public abstract class Building {
    protected final String name;
    protected final BuildingType buildingType;
    protected final Location location;
    protected boolean isOpen;

    public Building(String name, BuildingType buildingType, Location location, boolean isOpen) {
        this.name = name;
        this.buildingType = buildingType;
        this.location = location;
        this.isOpen = isOpen;
    }

    public String getName() {
        return name;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open){
        isOpen=open;
    }

    public String observeBuilding(){
        return getName()+" in location "+getLocation();
    }
}
