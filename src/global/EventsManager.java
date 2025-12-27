package global;

import java.util.ArrayList;
import java.util.List;

import characters.Character;

public class EventsManager {
    private static EventsManager instance;
    private final List<Event> listOfEvents;
    private int eventsCount;

    private EventsManager() {
        listOfEvents = new ArrayList<>();
        eventsCount = 0;
    }

    public static EventsManager getInstance() {
        if (instance == null) {
            instance = new EventsManager();
        }
        return instance;
    }

    public void executeEvents() {
        for (Event event : listOfEvents) {
            if (event.reasonOfEvent() == enums.Reason.Magic) {
                ManaSource.getInstance().setManaBonus(event.location(), 100);
            }

            for (Character participant : event.participants()) {
                if (event.reasonOfEvent() == enums.Reason.SawSomething) {
                    for (Character character : event.location().getCharacters()) {
                        if (character != participant && Math.random() > 0.5 && !character.isFear()) {
                            character.setFear(event);
                        }
                    }
                }
            }
        }
        triggerManaSurge();
        listOfEvents.clear();
    }

    public int getEventsCounter() {
        return eventsCount;
    }

    public void registerEvent(Event event) {
        listOfEvents.add(event);
        eventsCount++;
    }

    public void triggerManaSurge() {
        ManaSource.getInstance().regenerateMana();
    }
}
