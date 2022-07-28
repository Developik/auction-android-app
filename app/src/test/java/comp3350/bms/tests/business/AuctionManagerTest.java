package comp3350.bms.tests.business;

// Purpose: Tests the AuctionManager business object and its functions

import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import comp3350.bms.business.AuctionManager;
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
        //accessProducts = new AccessProducts();
        bidList = new ArrayList<>();
        dataAccess.getBidSequential(bidList);
        bidInitSize = bidList.size();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testAddBid() {
        System.out.println("Running testAddBid");
        Product product = dataAccess.getAllProducts().get(0);

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(1, 15.00,  date);
        dataAccess.insertBid(product, bid ,u1);

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(2, 7.00,  date);
        dataAccess.insertBid(product, bid ,u1);

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(3, 40.00,  date);
        dataAccess.insertBid(product, bid ,u1);
        Assert.assertEquals(3, dataAccess.getBidsNumber());

        System.out.println("Test getting owner stub test");
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(4, 7.00,  date);
        dataAccess.insertBid(product, bid, u2);
        User owner = dataAccess.getOwnerOfBid(bid);
        Assert.assertEquals(owner.getUsername(), u2.getUsername());

        System.out.println("All bids require a user who made the bid");
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(5, 7.00,  date);
        testRes = dataAccess.insertBid(product, bid ,null);
        Assert.assertEquals("Null object Error", testRes);

        testRes = dataAccess.insertBid(product, null ,null);
        Assert.assertEquals("Null object Error", testRes);

        System.out.println("All insert bids operations require a bid");

        System.out.println("Bids with more than 2 decimal values should be added");
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(6, 7.0000,  date);
        testRes = dataAccess.insertBid(product, bid ,u2);

        System.out.println("Bids with more than 2 decimal values should be added");
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(7, 15.6340,  date);
        testRes = dataAccess.insertBid(product, bid ,u2);

        Assert.assertEquals(6, dataAccess.getBidsNumber());
    }

    @Test
    public void testHighestBid() {
        Product product = dataAccess.getAllProducts().get(1);

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(1, 7.00,  date);
        testRes = dataAccess.insertBid(product, bid ,u2);

        Assert.assertEquals(dataAccess.getHighestBid(product).getBidID(),
                bid.getBidID());

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(2, 15.00,  date);
        testRes = dataAccess.insertBid(product, bid ,u2);

        Assert.assertEquals(dataAccess.getHighestBid(product).getBidID(),
                bid.getBidID());

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(3, 60.00,  date);
        testRes = dataAccess.insertBid(product, bid ,u2);

        Assert.assertEquals(dataAccess.getHighestBid(product).getBidID(),
                bid.getBidID());

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        bid = new Bid(4, 40.00,  date);
        testRes = dataAccess.insertBid(product, bid ,u2);

        Assert.assertEquals(dataAccess.getHighestBid(product).getBidID(),
                3);

        // get bids for a product
        ArrayList<Bid> bids = new ArrayList<>();
        dataAccess.getAllBidsForProduct(bids, product);
        Assert.assertEquals(4, bids.size());
    }

}
