package comp3350.bms.tests.business;

import org.junit.*;

import java.time.LocalDateTime;

import comp3350.bms.objects.Bid;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;

public class AuctionManagerTest {
    User u1;
    User u2;

    Product p;

    @Before
    public void setUp() {
        try{
            p = new Product("testProduct", LocalDateTime.now().minusDays(1L), LocalDateTime.now().minusHours(1L), "testPic", "testDesc", "testCategory");
            u1 = new User("username", "john", "smith", "55 road dr.", 44, false);
            u2 = new User("boblast123", "bob", "last", "10 lol cres.", 2, false);
        }
		catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void testAddBid() {
        System.out.println("Running testAddBid");
        System.out.println("normal bids");
        setUp();
        p.addBid(new Bid(u1, 15.00));
        p.addBid(new Bid(u2, 7.00));
        p.addBid(new Bid(u1, 40.00));
        Assert.assertEquals(40.00, p.getHighestBid().getBidAmount());

        System.out.println("negative bid should not be added");
        setUp();
        p.addBid(new Bid(u1, 15.00));
        p.addBid(new Bid(u2, 7.00));
        p.addBid(new Bid(u1, -10.00));
        Assert.assertEquals(7.00, p.getLowestBid().getBidAmount());

        System.out.println("bid of value 0 should not be added");
        setUp();
        p.addBid(new Bid(u1, 15.00));
        p.addBid(new Bid(u2, 7.00));
        p.addBid(new Bid(u1, 0));
        Assert.assertEquals(7.00, p.getLowestBid().getBidAmount());

        System.out.println("duplicate values from different users should be added");
        setUp();
        p.addBid(new Bid(u1, 15));
        p.addBid(new Bid(u2, 15));
        Assert.assertEquals(2, p.getBidCount());

        System.out.println("duplicate values from same user should not be added");
        setUp();
        p.addBid(new Bid(u1, 15));
        p.addBid(new Bid(u1, 15));
        Assert.assertEquals(1, p.getBidCount());

        System.out.println("all bids require a user who made the bid");
        setUp();
        p.addBid(new Bid(u1, 15));
        p.addBid(new Bid(null, 15));
        Assert.assertEquals(1, p.getBidCount());

        System.out.println("bids with more than 2 decimal values should be added");
        setUp();
        p.addBid(new Bid(u1, 15.00000));
        p.addBid(new Bid(u2, 15.6789));
        Assert.assertEquals(2, p.getBidCount());

    }

    @Test
    public void testGetBidCount() {
        System.out.println("Running testGetBidCount");
        System.out.println("normal test");
        setUp();
        p.addBid(new Bid(u1, 15));
        p.addBid(new Bid(u2, 20));
        Assert.assertEquals(2, p.getBidCount());

        System.out.println("with no bids added");
        setUp();
        Assert.assertEquals(0, p.getBidCount());

        System.out.println("only invalid bids attempted to be added");
        setUp();
        p.addBid(new Bid(u1, -10));
        p.addBid(new Bid(u2, -1));
        p.addBid(new Bid(u1, 0));
        Assert.assertEquals(0, p.getBidCount());
    }

    @Test
    public void testGetWinner() throws InterruptedException {
        System.out.println("Running testGetWinner");

        System.out.println("normal test");
        setUp();
        p.addBid(new Bid(u1, 15.00));
        p.addBid(new Bid(u2, 7.00));
        p.addBid(new Bid(u1, 40.00));
        // wait for auction to end
        Assert.assertEquals(40.00, p.getHighestBid().getBidAmount());
        Assert.assertEquals(u1, p.getHighestBid().getBidder());

        System.out.println("auction with two identical bids (earlier bid should win)");
        setUp();
        p.addBid(new Bid(u1, 15.00));
        p.addBid(new Bid(u2, 15.00));
        Assert.assertEquals(15.00, p.getHighestBid().getBidAmount());
        Assert.assertEquals(u1, p.getHighestBid().getBidder());
    }
}
