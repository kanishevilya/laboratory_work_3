package global;

import java.util.ArrayList;
import java.util.List;

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
            System.out.println("Event: " + event.getDescription());
            System.out.println("Reason: " + event.getEventReason());
            System.out.println();
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
        ManaSourse.getInstance().regenerateMana();
    }
}
