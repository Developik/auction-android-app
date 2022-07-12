package comp3350.bms.tests.objects;


import org.junit.*;

import comp3350.bms.objects.Bid;
import comp3350.bms.objects.User;

public class BidTest {

    @Test
    public void testBid() {
        System.out.println("Testing Bid class");

        try {
            User u = null;
            u = new User("username", "john", "smith", "55 road dr.", 44, false);
            Bid bid = new Bid(50, u);
        } catch (Exception e) {
            Assert.fail("Bid failed to create with valid User object");
        }


        try {
            System.out.println("Bid with null user should not be created.");
            Bid badBid = new Bid(50, null);
            Assert.fail("Bid was made despite having null user object"); // should not get here
        } catch (NullPointerException e) {
            // should get here
            System.out.println("Bid class works as expected.");
        }

    }
}