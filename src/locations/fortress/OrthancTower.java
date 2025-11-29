package locations.fortress;

import characters.wizard.Wizard;
import enums.LocationType;
import locations.Location;

import java.util.ArrayList;
import java.util.List;

public class OrthancTower extends Location {
    protected int height;
    protected List<Wizard> residentWizards;

    public OrthancTower(String name, String description, LocationType locationType, Location parentLocation,
            int height) {
        super(name, description, locationType, parentLocation, new ArrayList<>(), new ArrayList<>());
        this.height = height;
        this.residentWizards = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }

    public List<Wizard> getResidentWizards() {
        return residentWizards;
    }

    public void addWizard(Wizard wizard) {
        residentWizards.add(wizard);
    }

    public void removeWizard(Wizard wizard) {
        residentWizards.remove(wizard);
    }
}
