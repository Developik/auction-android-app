package comp3350.srsys.objects;

public class ChatMessages {
    private String message;
    private String user;

    public ChatMessages() {//sets default states
        message = "";
        user = "Temp";
    }

    public ChatMessages(String newMessage, String newUser) {
        this.message = newMessage;
        this.user = newUser;
        if(newUser == null) {
            this.user = "Temp";
        }
    }

    public String getMessage() {
        return this.user + ": " + this.message;
    }
}
