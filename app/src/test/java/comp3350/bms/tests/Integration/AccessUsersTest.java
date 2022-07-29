package comp3350.bms.tests.Integration;

// Purpose: Tests the AccessUsers business object and its functions

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.business.AccessUsers;
import comp3350.bms.objects.User;
import comp3350.bms.persistence.DataAccess;
import comp3350.bms.tests.persistence.DataAccessStub;

public class AccessUsersTest {
    ArrayList<User> userResult;
    AccessUsers accessUsers;
    DataAccess testAccess;

    public void setup() {
        testAccess = new DataAccessStub();
        try {
            Services.createDataAccess(testAccess);
        } catch (Exception e) {
            Assert.fail();
        }
        accessUsers = new AccessUsers();
        userResult = new ArrayList<>();
    }

    public void setupReal() {
        Main.startUp();
        accessUsers = new AccessUsers();
        userResult = new ArrayList<>();
    }

    public void shutdown() {
        Services.closeDataAccess();
    }

    // Test for existing users in stub database
    @Test
    public void testGetUsersStub() {

        // test with stub db
        setup();

        accessUsers.getUsers(userResult);
        // Two users in stub db
        Assert.assertEquals(2, userResult.size());

        shutdown();

    }

    // Test for existing users in real database
    @Test
    public void testGetUsersReal() {
        // test with real db
        setupReal();

        accessUsers.getUsers(userResult);
        // One user in real db
        Assert.assertEquals(1, userResult.size());

        shutdown();
    }
}