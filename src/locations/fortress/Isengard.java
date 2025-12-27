package locations.fortress;

import enums.LocationType;
import locations.Location;
import locations.districts.Country;

import java.util.ArrayList;

public class Isengard extends Country {
    protected final OrthancTower orthancTower;
    protected final Gate mainGate;

    public Isengard(String name, String description, Location parentLocation, OrthancTower orthancTower,
            Gate mainGate) {
        super(name, description, new ArrayList<>(), new ArrayList<>());
        this.locationType = LocationType.Fortress;
        this.orthancTower = orthancTower;
        this.mainGate = mainGate;
    }

    public OrthancTower getOrthancTower() {
        return orthancTower;
    }

    public void openGate() {
        mainGate.open(mainGate.getGuards().get(0));
    }
}
