package comp3350.bms.tests.Integration;

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

    public void shutdown() { Services.closeDataAccess(); }

    // tests to make sure that a new AccessUsers object has no users in it (stub db)
    @Test
    public void testEmptyObjectStub() {
        System.out.println("\nStarting AccessUsers Test: empty object (stub db)");

        // test with stub db
        setup();

        String result = accessUsers.getUsers(userResult); // returns null + populates userResult
        Assert.assertEquals(2, userResult.size());

        shutdown();

        System.out.println("\nFinished test.");
    }

    // tests to make sure that a new AccessUsers object has no users in it (real db)
    @Test
    public void testEmptyObjectReal() {
        System.out.println("\nStarting AccessUsers Test: empty object (real db)");

        // test with real db
        setupReal();

        String result = accessUsers.getUsers(userResult);
        Assert.assertEquals(2, userResult.size());

        shutdown();

        System.out.println("\nFinished test.");
    }
}
