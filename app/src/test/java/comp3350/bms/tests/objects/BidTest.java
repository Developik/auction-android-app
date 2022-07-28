package comp3350.bms.tests.objects;

// Purpose: Tests the Bid object and its functions

import org.junit.*;

import java.util.Date;

import comp3350.bms.objects.Bid;

public class BidTest {

    @Test
    public void testBid() {
        System.out.println("Testing Bid class");

        try {
            Date newDate = new Date();
            Bid bid = new Bid(50, 50, newDate);
        } catch (Exception e) {
            Assert.fail("Bid failed to create with valid inputs");
        }
    }
}