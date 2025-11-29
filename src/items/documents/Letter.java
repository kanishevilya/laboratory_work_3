package items.documents;

import characters.Character;
import items.Item;

public class Letter extends Item {
    protected String title;
    protected String content;
    protected Character sender;
    protected Character recipient;
    protected boolean isWasOpen;
    protected Character entrustTo;

    public Letter(String name, String description, int value, String title, String content, Character sender, Character recipient) {
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
        isWasOpen=true;
        return content;
    }

    public void write(String content){
        this.content += content;
    }

    public Character getSender() {
        return sender;
    }

    public Character getRecipient() {
        return recipient;
    }

    public void entrustTo(Character character){
        super.transferTo(character);
        entrustTo=character;
    }

}
