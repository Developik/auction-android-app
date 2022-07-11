package comp3350.srsys.objects;

// Purpose: Simple object that contains a message, user pair.

public class ChatMessages {

    // add unique ID for the message

    private String message;
    private String user;

    public ChatMessages() {
        message = "";
        user = "Temp";
    }

    public ChatMessages(String newMessage, String newUser) {
        this.message = newMessage;
        this.user = newUser;
        if (newUser == null) {
            this.user = "Temp";
        }
    }

    public String getMessage() {
        return this.user + ": " + this.message;
    }

    public String getMessageString() {
        return this.message;
    }
}
