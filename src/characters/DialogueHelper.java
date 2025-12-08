package characters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DialogueHelper {
    private Map<Character, List<String>> receivedMessages;

    public DialogueHelper() {
        this.receivedMessages = new HashMap<>();
    }

    public Map<Character, List<String>> getReceivedMessages() {
        return receivedMessages;
    }

    public void speak(Character recipient, String message) {
        Map<Character, List<String>> messages = recipient.getReceivedMessages();
        if (messages.get(recipient) != null) {
            messages.get(recipient).add(message);
        } else {
            List<String> initialMessages = new ArrayList<>();
            initialMessages.add(message);
            messages.put(recipient, initialMessages);
        }
    }

    public void viewReceivedMessages() {
        for (Character character : receivedMessages.keySet()) {
            System.out.println(character.getName() + ": ");
            for (String message : receivedMessages.get(character)) {
                System.out.println("\t" + message);
            }
        }
        receivedMessages.clear();
    }
}
