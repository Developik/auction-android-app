package comp3350.srsys.objects;

// Purpose: Simple object that contains a message, user pair.

import java.util.Date;

public class ChatMessages {

    private String message;
    private String user;
    private long time;

    public ChatMessages() {
        message = "";
        user = "Temp";
        time = System.currentTimeMillis();
    }

    public ChatMessages(String newMessage, String newUser) {
        this.message = newMessage;
        this.user = newUser;
        if(newUser == null) {
            this.user = "Temp";
        }
        time = System.currentTimeMillis();
    }

    public String getMessage() {
        return this.user + ": " + this.message;
    }

    public long getTimeOfMesage() {
        return time;
    }
}
