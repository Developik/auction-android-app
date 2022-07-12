package comp3350.bms.tests.objects;

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
}
