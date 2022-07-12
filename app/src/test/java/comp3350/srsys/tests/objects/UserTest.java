package comp3350.srsys.tests.objects;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import comp3350.bms.objects.User;

public class UserTest extends TestCase {

    User user;
    String username;
    String firstName;
    String lastName;
    String address;
    int age;
    boolean isBot;
    List<String> categories = Arrays.asList("Books", "Watches", "Garden");

    @Before
    public void setUp() {
        username = "joedoe";
        firstName = "Joe";
        lastName = "Doe";
        address = "66 Chancellor Dr, Winnipeg, MB";
        age = 25;
        isBot = false;
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
        } catch (Exception e) {
            fail("Creation of Item failed.");
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testProductCreatedCorrectly() {
        assertEquals(user.getUsername(), username);
        assertEquals(user.getFirstName(), firstName);
        assertEquals(user.getLastName(), lastName);
        assertEquals(user.getFullName(), "Joe Doe");

        assertEquals(user.getAddress(), address);
        assertEquals((int) user.getAge(), age);
    }

    @Test
    public void testUserName() {
        username = "";
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        username = null;
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testFirstAndLastName() {
        firstName = "";
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        firstName = null;
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        lastName = "";
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        lastName = null;
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testFullName() {
        firstName = "Abc";
        lastName = "Def";
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
        } catch (Exception ignored) {
            fail();
        }

        assertEquals(user.getFullName(), "Abc Def");

        firstName = "123   ";
        lastName = "   345 ";
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
        } catch (Exception ignored) {
            fail();
        }

        assertEquals(user.getFullName(), "123       345 ");
    }

    @Test
    public void testAge() {
        age = -1;
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        age = Integer.MIN_VALUE;
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testIsBot() {
        isBot = false;
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
        } catch (Exception ignored) {
            fail("Creation of User failed.");
        }

        isBot = true;
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
        } catch (Exception ignored) {
            fail("Creation of User failed.");
        }
    }

    @Test
    public void testGetMessageInitializedUser() {
        try {
            User user = new User("RyanL", "Ryan", "L", "82 Place", 0, false);
            assertEquals(null, user.getChatMessage(0));
            assertEquals(null, user.getChatMessage(1));
            assertEquals(null, user.getChatMessage(-1));
            //passed null tests with empty history
            user.createMessage("", 0);
            //Tests a valid message
            assertEquals("RyanL: ", user.getChatMessage(0));
            //Tests expected null values
            assertEquals(null, user.getChatMessage(-1));
            assertEquals(null, user.getChatMessage(1));
            //passed null tests with some history
            user.createMessage("", -1);
            //Tests a valid message that uses the Temp user instead
            assertEquals("Ryan: ", user.getChatMessage(0));
            //Tests a valid message in the second position
            assertEquals("RyanL: ", user.getChatMessage(1));
            //Tests expected null values
            assertEquals(null, user.getChatMessage(-1));
            assertEquals(null, user.getChatMessage(2));
            //passed null tests with some history
        } catch (
                Exception ignored) {
            fail("Creation of User failed.");
        }
        System.out.println("Finished testGetMessageInitializedUser");
    }
}
