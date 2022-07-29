package comp3350.bms.tests.Integration;

// Purpose: Tests the AuctionManager business object and its functions

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import comp3350.bms.objects.Bid;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;
import comp3350.bms.persistence.DataAccess;
import comp3350.bms.tests.persistence.DataAccessStub;

public class AuctionManagerTest {

    User u1;
    User u2;
    DataAccess dataAccess;
    ArrayList<Bid> bidList;
    int bidInitSize;
    Date date;
    Bid bid;
    String testRes;

    @Before
    public void setUp() throws Exception {
        System.out.println("Starting AuctionManager test");
        dataAccess = new DataAccessStub();
        dataAccess.open("Stub");
        try {
            ArrayList<User> users = new ArrayList<>();
            dataAccess.getUserSequential(users);
            u1 = users.get(0);
            u2 = users.get(1);

        } catch (Exception e) {
            Assert.fail();
        }
        bidList = new ArrayList<>();
        dataAccess.getBidSequential(bidList);
        bidInitSize = bidList.size();
    }

    @Test
    public void testInsertBid() {
        Product product = dataAccess.getAllProducts().get(0);

        // Test adding valid vids with valid users
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(1, 15.00, date);
        dataAccess.insertBid(product, bid, u1);

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(2, 7.00, date);
        dataAccess.insertBid(product, bid, u1);

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(3, 40.00, date);
        dataAccess.insertBid(product, bid, u1);
        Assert.assertEquals(3, dataAccess.getBidsNumber());

        // Test getting owner (user) who made the bid
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(4, 7.00, date);
        dataAccess.insertBid(product, bid, u2);
        User owner = dataAccess.getOwnerOfBid(bid);
        Assert.assertEquals(owner.getUsername(), u2.getUsername());

        // Test using null user for bid
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(5, 7.00, date);
        testRes = dataAccess.insertBid(product, bid, null);
        Assert.assertEquals("Null object Error", testRes);

        // Test using null product for bid and null user for bid
        testRes = dataAccess.insertBid(product, null, null);
        Assert.assertEquals("Null object Error", testRes);

        // Test using a bid amount with decimal places
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(6, 7.1458, date);
        testRes = dataAccess.insertBid(product, bid, u2);

        // Test using a bid amount of 0
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(6, 0, date);
        testRes = dataAccess.insertBid(product, bid, u2);
        Assert.assertEquals("Bid value too low", testRes);

        // Test using a negative bid amount
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(6, -85, date);
        testRes = dataAccess.insertBid(product, bid, u2);
        Assert.assertEquals("Bid value too low", testRes);


        Assert.assertEquals(5, dataAccess.getBidsNumber());
    }

    @Test
    public void testHighestBid() {
        Product product = dataAccess.getAllProducts().get(1);

        // Test value 7.00
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(1, 7.00, date);
        testRes = dataAccess.insertBid(product, bid, u2);

        Assert.assertEquals(dataAccess.getHighestBid(product).getBidID(),
                bid.getBidID());

        // Test value 15.00
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(2, 15.00, date);
        testRes = dataAccess.insertBid(product, bid, u2);

        Assert.assertEquals(dataAccess.getHighestBid(product).getBidID(),
                bid.getBidID());

        // Test value 60.00
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(3, 60.00, date);
        testRes = dataAccess.insertBid(product, bid, u2);

        Assert.assertEquals(dataAccess.getHighestBid(product).getBidID(),
                bid.getBidID());

        // Test value 40.00
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(4, 40.00, date);
        testRes = dataAccess.insertBid(product, bid, u2);

        // Bid 3 is the highest bid
        Assert.assertEquals(dataAccess.getHighestBid(product).getBidID(),
                3);

        // Verify all bids are in the database
        ArrayList<Bid> bids = new ArrayList<>();
        dataAccess.getAllBidsForProduct(bids, product);
        Assert.assertEquals(4, bids.size());
    }

}
