package locations.fortress;

import characters.wizard.Wizard;
import enums.LocationType;
import locations.Location;

import java.util.ArrayList;
import java.util.List;

public class OrthancTower extends Location {
    protected final List<Wizard> residentWizards;

    public OrthancTower(String name, String description, LocationType locationType, Location parentLocation) {
        super(name, description, locationType, parentLocation, new ArrayList<>(), new ArrayList<>());
        this.residentWizards = new ArrayList<>();
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
