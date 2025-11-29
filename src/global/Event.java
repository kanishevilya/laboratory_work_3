package global;

import characters.Character;
import enums.Reason;
import locations.Location;

import java.util.List;

public class Event {
    private List<Character> participants;
    private Location location;
    private Reason reasonOfEvent;
    private String description;

    public Event(String description, Location location, Reason reasonOfEvent, List<Character> participants) {
        this.description = description;
        this.location = location;
        this.reasonOfEvent = reasonOfEvent;
        this.participants = participants;
    }

    public List<Character> getParticipants() {
        return participants;
    }

    public Location getCurrentLocation() {
        return location;
    }

    public Reason getEventReason() {
        return reasonOfEvent;
    }

    public String getDescription() {
        return description;
    }

    public void addParticipant(Character character) {
        participants.add(character);
    }
}
