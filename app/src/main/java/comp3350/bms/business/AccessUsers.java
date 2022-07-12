package comp3350.bms.business;

import java.util.List;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.objects.User;
import comp3350.bms.persistence.DataAccess;

public class AccessUsers
{
	private DataAccess dataAccess;
	private List<User> users;
	private User user;
	private int currentUser;

	public AccessUsers() {
		dataAccess = Services.getDataAccess(Main.dbName);
        users = null;
        user = null;
		currentUser = 0;
	}

    public String getUsers(List<User> users) {
        users.clear();
        return dataAccess.getUserSequential(users);
    }

}
