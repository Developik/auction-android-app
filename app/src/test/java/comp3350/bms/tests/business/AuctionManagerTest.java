//package comp3350.bms.tests.business;
//
//// Purpose: Tests the AuctionManager business object and its functions
//
//import org.junit.*;
//
//import comp3350.bms.business.AuctionManager;
//import comp3350.bms.objects.Bid;
//import comp3350.bms.objects.User;
//
//public class AuctionManagerTest {
//
//    AuctionManager am;
//    User u1;
//    User u2;
//
//    // This is to clear the user objects.
//    private void resetUser() {
//        System.out.println("Starting AuctionManager test");
//        am = new AuctionManager(3);
//        try {
//            u1 = new User("username", "john", "smith", "55 road dr.", 44, false);
//            u2 = new User("boblast123", "bob", "last", "10 lol cres.", 2, false);
//        } catch (Exception e) {
//            Assert.fail();
//        }
//    }
//
//    @Test
//    public void testAddBid() {
//        resetUser();
//        System.out.println("Running testAddBid");
//        am.addBid(15.00, u1);
//        am.addBid(7.00, u2);
//        am.addBid(40.00, u1);
//        Assert.assertEquals(3, am.getBidCount());
//
//        System.out.println("Negative bid should not be added");
//        resetUser();
//        am.addBid(15.00, u1);
//        am.addBid(7.00, u2);
//        am.addBid(-10.00, u1);
//        Assert.assertEquals(2, am.getBidCount());
//
//        System.out.println("Bid of value 0 should not be added");
//        resetUser();
//        am.addBid(15, u1);
//        am.addBid(7, u2);
//        am.addBid(0, u1);
//        Assert.assertEquals(2, am.getBidCount());
//
//        System.out.println("Duplicate values from different users should be added");
//        resetUser();
//        am.addBid(15, u1);
//        am.addBid(15, u2);
//        Assert.assertEquals(2, am.getBidCount());
//
//        System.out.println("Duplicate values from same user should not be added");
//        resetUser();
//        am.addBid(15, u1);
//        am.addBid(15, u1);
//        Assert.assertEquals(1, am.getBidCount());
//
//        System.out.println("All bids require a user who made the bid");
//        resetUser();
//        am.addBid(15, u1);
//        am.addBid(25, null);
//        Assert.assertEquals(1, am.getBidCount());
//
//        System.out.println("Bids with more than 2 decimal values should be added");
//        resetUser();
//        am.addBid(15.00000, u1);
//        am.addBid(15.6789, u1);
//        Assert.assertEquals(2, am.getBidCount());
//
//    }
//
//    @Test
//    public void testGetBidCount() {
//        System.out.println("Running testGetBidCount");
//        resetUser();
//        am.addBid(15, u1);
//        am.addBid(20, u2);
//        Assert.assertEquals(2, am.getBidCount());
//
//        System.out.println("With no bids added");
//        resetUser();
//        Assert.assertEquals(0, am.getBidCount());
//
//        System.out.println("Only invalid bids attempted to be added");
//        resetUser();
//        am.addBid(-5, u1);
//        am.addBid(10, null);
//        am.addBid(0, null);
//        Assert.assertEquals(0, am.getBidCount());
//    }
//
//    @Test
//    public void testGetWinner() throws InterruptedException {
//        System.out.println("Running testGetWinner");
//
//        System.out.println("normal test");
//        resetUser();
//        am.addBid(6.50, u1);
//        am.addBid(11.00, u2);
//        am.addBid(5.01, u1);
//        Bid winningBid = am.getWinner();
//        Assert.assertEquals(11.00, winningBid.getValue(), 0.01);
//        Assert.assertEquals(u2, am.getWinner().getUser());
//
//        System.out.println("auction with invalid bid");
//        resetUser();
//        am.addBid(6.01, u1);
//        am.addBid(-5.00, u1);
//        am.addBid(2.00, u2);
//        winningBid = am.getWinner();
//        Assert.assertEquals(6.01, winningBid.getValue(), 0.01);
//        Assert.assertEquals(u1, am.getWinner().getUser());
//
//        System.out.println("duplicate values by same user");
//        resetUser();
//        am.addBid(6.00, u1);
//        am.addBid(4.00, u2);
//        am.addBid(6.00, u1);
//        winningBid = am.getWinner();
//        Assert.assertEquals(6.00, winningBid.getValue(), 0.01);
//        Assert.assertEquals(u1, am.getWinner().getUser());
//
//        System.out.println("no bids have been made, expect no winning bid");
//        resetUser();
//        Assert.assertNull(am.getWinner());
//
//    }
//}
