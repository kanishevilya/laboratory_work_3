package items.documents;

import characters.Character;
import items.Item;
import exceptions.InventoryFullException;

public class Letter extends Item {
    protected final String title;
    protected String content;
    protected final Character sender;
    protected final Character recipient;
    protected boolean isWasOpen;
    protected Character entrustTo;

    public Letter(String name, String description, int value, String title, String content, Character sender,
            Character recipient) throws InventoryFullException {
        super(name, description, sender, value);
        this.title = title;
        this.content = content;
        this.isWasOpen = false;
        this.sender = sender;
        this.recipient = recipient;
    }

    public String getTitle() {
        return title;
    }

    public String read() {
        isWasOpen = true;
        return content;
    }

    public void write(String content) {
        this.content = content;
    }

    public Character getSender() {
        return sender;
    }

    public Character getRecipient() {
        return recipient;
    }

    public void entrustTo(Character character) throws InventoryFullException {
        this.entrustTo = character;
        super.transferTo(character);
    }

}
