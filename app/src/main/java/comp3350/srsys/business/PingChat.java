package comp3350.srsys.business;

import java.util.ArrayList;

import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.objects.ChatMessages;
import comp3350.srsys.persistence.DataAccessStub;

public class PingChat
{
	private final DataAccessStub dataAccess;
	private ArrayList<ChatMessages> allMessages;

	public PingChat() {
		dataAccess = Services.getDataAccess(Main.dbName);
		allMessages = new ArrayList<>();
	}

    public String getMessages() {
        allMessages.clear();
        return dataAccess.getChatMessagesSequential(allMessages);
    }

	public String getRandom() {
		String message = "";
		if (allMessages != null && allMessages.size() > 0) {
			message = getRandomS(-1);
		}
		return message;
	}

	public String getRandomS(int index) {//Index is used to test random function
		String message = "Range {" + allMessages.size() + "}, Invalid Index";
		if (index == -1) {
			message = allMessages.get((int) (Math.random() * allMessages.size())).getMessage();
		}
		else if (index < allMessages.size() && index > -1){
			message = allMessages.get(index).getMessage();
		}
		return message;
	}
}