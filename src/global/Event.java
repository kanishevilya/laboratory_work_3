package global;

import characters.Character;
import enums.Reason;
import locations.Location;

import java.util.List;

public record Event (List<Character> participants, Location location, Reason reasonOfEvent, String description){
}
