package comp3350.srsys.business;

// Purpose: Manages the databases ChatMessages

import java.util.ArrayList;

import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.objects.ChatMessages;
import comp3350.srsys.persistence.DataAccessStub;

public class PingChat {
	private final DataAccessStub dataAccess;
	private ArrayList<ChatMessages> allMessages;

	public PingChat(){
		dataAccess = Services.getDataAccess(Main.dbName);
		allMessages = new ArrayList<>();
	}

    public String getMessages(){
        allMessages.clear();
        return dataAccess.getChatMessagesSequential(allMessages);
    }

	//function used at run time to call getRandomS(-1) for random message
	public String getRandom(){
		String message = "";
		if (allMessages != null && allMessages.size() > 0){
			message = getRandomS(-1);
		}
		return message;
	}

	//getRandomS is created to allow PingChatTest to test certain messages
	//index is -1 during run time and is tested for other valid and invalid values
	public String getRandomS(int index){//Index is used to test random function
		String message = "Range {" + allMessages.size() + "}, Invalid Index";
		if (index == -1){
			message = allMessages.get((int) (Math.random() * allMessages.size())).getMessageString();
		}
		else if (index < allMessages.size() && index > -1){
			message = allMessages.get(index).getMessage();
		}
		return message;
	}
}