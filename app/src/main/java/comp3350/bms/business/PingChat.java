package comp3350.bms.business;

// Purpose: Manages the database's ChatMessages

import java.util.ArrayList;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.objects.ChatMessages;
import comp3350.bms.persistence.DataAccess;

public class PingChat {
    private final DataAccess dataAccess;
    private ArrayList<ChatMessages> allMessages;

    public PingChat() {
        dataAccess = Services.getDataAccess(Main.dbName);
        allMessages = new ArrayList<>();
    }//creates a PingChat with access to the database

    public String getMessages() {
        allMessages.clear();
        return dataAccess.getChatMessagesSequential(allMessages);
    }//initializes allMessages from the database

    public String getRandom() {
        String message = "";//default
        if (allMessages != null && allMessages.size() > 0) {
            message = getRandomS(-1);
        }//if a message exist, getRandomS(-1)
        return message;
    }

    public String getRandomS(int index) {//Index is used to test random function
        String message = "Range {" + allMessages.size() + "}, Invalid Index";
        if (index == -1) {//called at runtime
            message = allMessages.get((int) (Math.random() * allMessages.size())).getMessageString();
        } else if (index < allMessages.size() && index > -1) {//called by tests
            message = allMessages.get(index).getMessage();
        }
        return message;
    }
}