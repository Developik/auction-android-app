package comp3350.bms.tests.objects;

// Purpose: Tests the Bid object and its functions

import org.junit.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import comp3350.bms.objects.Bid;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;
import comp3350.bms.persistence.DataAccess;
import comp3350.bms.tests.persistence.DataAccessStub;

public class BidTest {

    DataAccess dataAccess;

    @Test
    public void testBid() {
        System.out.println("Testing Bid class");

        try {
            Date date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
            Bid bid = new Bid(1, 50, date);
        } catch (Exception e) {
            Assert.fail("Bid failed to create!");
        }


        try {
            System.out.println("Bid with negative value should become 0.");
            Date date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
            Bid badBid = new Bid(2, -50, date);
            Assert.assertEquals(badBid.getValue(), 0, 0.001);
        } catch (NullPointerException e) {
            // should get here
            Assert.fail("Bid failed to create!");
        }

    }
}