package buildings.training;

import buildings.Building;
import characters.human.Knight;
import enums.BuildingType;
import locations.Location;

public class TrainingCamp extends Building {
    private int trainingCost;

    public TrainingCamp(String name, BuildingType buildingType, Location location, boolean isOpen, int trainingCost) {
        super(name, buildingType, location, isOpen);
        this.trainingCost = trainingCost;
    }

    public void changeTrainingCost(int newCost){
        trainingCost=newCost;
    }

    public void train(Knight knight) {
        if (!isOpen) {
            return;
        }

        if (knight.getCoinPurse().spendCopper(trainingCost)) {
            knight.increaseFencingSkill();
        }
    }
}
