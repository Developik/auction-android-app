package comp3350.srsys.tests.business;

import junit.framework.TestCase;

import comp3350.srsys.business.AuctionManager;
import comp3350.srsys.objects.User;

public class AuctionManagerTest extends TestCase {

    AuctionManager am;
    User u1;
    User u2;

    public void setUp() {
        am = new AuctionManager(3);
        u1 = new User("username", "john", "smith", "55 road dr.", 44, false);
        u2 = new User("boblast123", "bob", "last", "10 lol cres.", 2, false);
    }

    public void testAddBid() {
        System.out.println("Running testAddBid");
        System.out.println("normal bids");
        setUp();
        am.addBid(15.00, u1);
        am.addBid(7.00, u2);
        am.addBid(40.00, u1);
        assertEquals(3, am.getBidCount());

        System.out.println("negative bid should not be added");
        setUp();
        am.addBid(15.00, u1);
        am.addBid(7.00, u2);
        am.addBid(-10.00, u1);
        assertEquals(2, am.getBidCount());

        System.out.println("bid of value 0 should not be added");
        setUp();
        am.addBid(15, u1);
        am.addBid(7, u2);
        am.addBid(0, u1);
        assertEquals(2, am.getBidCount());

        System.out.println("duplicate values from different users should be added");
        setUp();
        am.addBid(15, u1);
        am.addBid(15, u2);
        assertEquals(2, am.getBidCount());

        System.out.println("duplicate values from same user should not be added");
        setUp();
        am.addBid(15, u1);
        am.addBid(15, u1);
        assertEquals(1, am.getBidCount());

        System.out.println("all bids require a user who made the bid");
        setUp();
        am.addBid(15, u1);
        am.addBid(25, null);
        assertEquals(1, am.getBidCount());

        System.out.println("bids with more than 2 decimal values should be added");
        setUp();
        am.addBid(15.00000, u1);
        am.addBid(15.6789, u1);
        assertEquals(2, am.getBidCount());

    }

    public void testGetBidCount() {
        System.out.println("Running testGetBidCount");
        System.out.println("normal test");
        setUp();
        am.addBid(15, u1);
        am.addBid(20, u2);
        assertEquals(2, am.getBidCount());

        System.out.println("with no bids added");
        setUp();
        assertEquals(0, am.getBidCount());

        System.out.println("only invalid bids attempted to be added");
        setUp();
        am.addBid(-5, u1);
        am.addBid(10, null);
        am.addBid(0, null);
        assertEquals(0, am.getBidCount());
    }

    public void testGetWinner() throws InterruptedException {
        System.out.println("Running testGetWinner");

        System.out.println("normal test");
        setUp();
        am.addBid(6.50, u1);
        am.addBid(11.00, u2);
        am.addBid(5.01, u1);
        // wait for auction to end
        Thread.sleep(4000);
        assertEquals(11.00, am.getWinner().getValue());
        assertEquals(u2, am.getWinner().getUser());

        System.out.println("auction with invalid bid");
        setUp();
        am.addBid(6.01, u1);
        am.addBid(-5.00, u1);
        am.addBid(2.00, u2);
        Thread.sleep(4000);
        assertEquals(6.01, am.getWinner().getValue());
        assertEquals(u1, am.getWinner().getUser());

        System.out.println("calling before auction completion");
        setUp();
        am.addBid(6.00, u1);
        am.addBid(11.00, u2);
        am.addBid(5.00, u1);
        assertNull(am.getWinner());

        System.out.println("duplicate values by same user");
        setUp();
        am.addBid(6.00, u1);
        am.addBid(4.00, u2);
        am.addBid(6.00, u1);
        Thread.sleep(4000);
        assertEquals(6.00, am.getWinner().getValue());
        assertEquals(u1, am.getWinner().getUser());

        System.out.println("duplicate values from different users should be won by earlier voter");
        setUp();
        am.addBid(6.00, u1);
        am.addBid(6.00, u2);
        Thread.sleep(4000);
        assertEquals(6.00, am.getWinner().getValue());
        assertEquals(u1, am.getWinner().getUser());

        System.out.println("duplicate values from different users, but one user tops after");
        setUp();
        am.addBid(6.00, u1);
        am.addBid(6.00, u2);
        am.addBid(7.00, u2);
        Thread.sleep(4000);
        assertEquals(7.00, am.getWinner().getValue());
        assertEquals(u2, am.getWinner().getUser());

        System.out.println("adding highest bid after auction has closed (therefore this bid should not be added)");
        setUp();
        am.addBid(10.00, u1);
        am.addBid(20.00, u2);
        Thread.sleep(4000);
        am.addBid(40.00, u1);
        assertEquals(20.00, am.getWinner().getValue());
        assertEquals(u2, am.getWinner().getUser());

        System.out.println("no bids have been made, expect no winning bid");
        setUp();
        Thread.sleep(4000);
        assertNull(am.getWinner());

        System.out.println("checking 1 cent different noticed");
        setUp();
        am.addBid(10.00, u1);
        am.addBid(10.01, u2);
        Thread.sleep(4000);
        assertEquals(10.01, am.getWinner().getValue());
        assertEquals(u2, am.getWinner().getUser());

        System.out.println("numbers past 100's decimal pos will be dropped, not rounded");
        setUp();
        am.addBid(9.9999999, u1);
        am.addBid(10.00, u2);
        Thread.sleep(4000);
        assertEquals(10.00, am.getWinner().getValue());
        assertEquals(u2, am.getWinner().getUser());

    }

    public void testGetSecondsRemaining() throws InterruptedException {
        System.out.println("Running testGetSecondsRemaining");

        System.out.println("before auction ended");
        setUp();
        assertTrue(am.getSecondsRemaining() > 0);

        System.out.println("well after auction ended");
        setUp();
        Thread.sleep(5000);
        assertEquals(0, am.getSecondsRemaining());
    }
}
