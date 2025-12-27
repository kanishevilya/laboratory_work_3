package exceptions;

import characters.Character;
import locations.Location;

public class AccessDeniedException extends RuntimeException {
    private final Character character;
    private final Location location;
    private final String reason;

    public AccessDeniedException(Character character, Location location, String reason) {
        super(String.format("%s не может войти в %s: %s",
                character.getName(), location.getName(), reason));
        this.character = character;
        this.location = location;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return String.format("Доступ запрещен: %s пытается войти в '%s' - %s",
                character.getName(), location.getName(), reason);
    }

    public Character getCharacter() {
        return character;
    }

    public Location getLocation() {
        return location;
    }

    public String getReason() {
        return reason;
    }
}
