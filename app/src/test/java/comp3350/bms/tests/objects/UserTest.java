package comp3350.bms.tests.objects;

// Purpose: Tests the User object and its functions

import org.junit.*;

import java.util.Arrays;
import java.util.List;

import comp3350.bms.objects.User;

public class UserTest {

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
        try {
            user = new User(username, firstName, lastName, address, age, false);
        } catch (Exception e) {
            Assert.fail("Creation of User failed.");
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testProductCreatedCorrectly() {
        Assert.assertEquals(user.getUsername(), username);
        Assert.assertEquals(user.getFirstName(), firstName);
        Assert.assertEquals(user.getLastName(), lastName);
        Assert.assertEquals(user.getFullName(), "Joe Doe");

        Assert.assertEquals(user.getAddress(), address);
        Assert.assertEquals((int) user.getAge(), age);
    }

    @Test
    public void testUserName() {
        username = "";
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            Assert.fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        try {
            user = new User(null, firstName, lastName, address, age, isBot);
            Assert.fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testFirstAndLastName() {
        firstName = "";
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            Assert.fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        try {
            user = new User(username, null, lastName, address, age, isBot);
            Assert.fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        lastName = "";
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            Assert.fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        lastName = null;
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            Assert.fail("Creation of User succeeded when it shouldn't have been.");
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
            Assert.fail();
        }

        Assert.assertEquals(user.getFullName(), "Abc Def");

        firstName = "123   ";
        lastName = "   345 ";
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
        } catch (Exception ignored) {
            Assert.fail();
        }

        Assert.assertEquals(user.getFullName(), "123       345 ");
    }

    @Test
    public void testAge() {
        age = -1;
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            Assert.fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        age = Integer.MIN_VALUE;
        try {
            user = new User(username, firstName, lastName, address, age, isBot);
            Assert.fail("Creation of User succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testGetMessageInitializedUser() {
        try {
            User user = new User("RyanL", "Ryan", "L", "82 Place", 0, false);
            Assert.assertEquals(null, user.getChatMessage(0));
            Assert.assertEquals(null, user.getChatMessage(1));
            Assert.assertEquals(null, user.getChatMessage(-1));
            //passed null tests with empty history
            user.createMessage("", 0);
            //Tests a valid message
            Assert.assertEquals("RyanL: ", user.getChatMessage(0));
            //Tests expected null values
            Assert.assertEquals(null, user.getChatMessage(-1));
            Assert.assertEquals(null, user.getChatMessage(1));
            //passed null tests with some history
            user.createMessage("", -1);
            //Tests a valid message that uses the Temp user instead
            Assert.assertEquals("Ryan: ", user.getChatMessage(0));
            //Tests a valid message in the second position
            Assert.assertEquals("RyanL: ", user.getChatMessage(1));
            //Tests expected null values
            Assert.assertEquals(null, user.getChatMessage(-1));
            Assert.assertEquals(null, user.getChatMessage(2));
            //passed null tests with some history
        } catch (Exception ignored) {
            System.out.println("Creation of User failed.");
            Assert.assertEquals(0, 1);
        }
        System.out.println("Finished testGetMessageInitializedUser");
    }
}
