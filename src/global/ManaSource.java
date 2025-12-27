package global;

import locations.Location;

import java.util.HashMap;
import java.util.Map;

public class ManaSource {
    private static ManaSource instance;
    private static int totalMana;
    private static int manaRegenerationRate;
    private static Map<Location, Integer> bonusOfManaInLocations;

    private ManaSource(){
        totalMana = 1000;
        manaRegenerationRate = 10;
        bonusOfManaInLocations = new HashMap<>();
    }


    public static ManaSource getInstance(){
        if(instance == null){
            instance = new ManaSource();
        }
        return instance;
    }

    public void regenerateMana(){
        totalMana += manaRegenerationRate;
    }

    public int getAvailableManaAtLocation(Location location){
        return bonusOfManaInLocations.getOrDefault(location, 0);
    }

    public int takeMana(Location location, int amount){
        int bonus = getAvailableManaAtLocation(location);
        int available = getTotalMana() + bonus;
        
        if (available >= amount) {
            totalMana -= amount;
            return amount;
        }
        return 0;
    }

    public int getTotalMana(){
        return totalMana;
    }
    
    public void setManaBonus(Location location, int bonus) {
        bonusOfManaInLocations.put(location, bonus);
    }
}
