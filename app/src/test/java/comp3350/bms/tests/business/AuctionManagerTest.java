package comp3350.bms.tests.business;

import org.junit.*;

import comp3350.bms.business.AuctionManager;
import comp3350.bms.objects.User;

public class AuctionManagerTest {

    AuctionManager am;
    User u1;
    User u2;

    public void recreateAuction() {
        try{
            am = new AuctionManager(3);
            u1 = new User("username", "john", "smith", "55 road dr.", 44, false);
            u2 = new User("boblast123", "bob", "last", "10 lol cres.", 2, false);
        }
		catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void testNormalVals() {
        recreateAuction();
        System.out.println("normal bids");
        am.addBid(15.00, u1);
        am.addBid(7.00, u2);
        am.addBid(40.00, u1);
        Assert.assertEquals(3, am.getBidCount());

        recreateAuction();
        System.out.println("checking bid count");
        am.addBid(15, u1);
        am.addBid(20, u2);
        Assert.assertEquals(2, am.getBidCount());

        recreateAuction();
        System.out.println("check winner");
        am.addBid(6.50, u1);
        am.addBid(11.00, u2);
        am.addBid(5.01, u1);
        // wait for auction to end
        Assert.assertEquals(11.00, am.getWinner().getValue());
        Assert.assertEquals(u2, am.getWinner().getUser());

        recreateAuction();

    }

    @Test
    public void testCreatingInvalidAuction() {

        System.out.println("negative duration");
        try{
            AuctionManager a = new AuctionManager(-1);
            Assert.fail();
        }
        catch(NumberFormatException e){}

        System.out.println("zero duration");
        try{
            AuctionManager a = new AuctionManager(0);
            Assert.fail();
        }
        catch(NumberFormatException e){}
    }

    @Test
    public void testAddingInvalidBids() {
        recreateAuction();
        System.out.println("negative bid should not be added");
        am.addBid(15.00, u1);
        am.addBid(7.00, u2);
        am.addBid(-10.00, u1);
        Assert.assertEquals(2, am.getBidCount());

        recreateAuction();
        System.out.println("bid of value 0 should not be added");
        am.addBid(15, u1);
        am.addBid(7, u2);
        am.addBid(0, u1);
        Assert.assertEquals(2, am.getBidCount());

        recreateAuction();
        System.out.println("duplicate values from different users should be added");
        am.addBid(15, u1);
        am.addBid(15, u2);
        Assert.assertEquals(2, am.getBidCount());

        recreateAuction();
        System.out.println("duplicate values from same user should not be added");
        am.addBid(15, u1);
        am.addBid(15, u1);
        Assert.assertEquals(1, am.getBidCount());

        recreateAuction();
        System.out.println("all bids require a user who made the bid");
        am.addBid(15, u1);
        am.addBid(25, null);
        Assert.assertEquals(1, am.getBidCount());

        recreateAuction();
        System.out.println("bids with more than 2 decimal values should be added");
        am.addBid(15.00000, u1);
        am.addBid(15.6789, u1);
        Assert.assertEquals(2, am.getBidCount());
    }

    @Test
    public void testGettingBidCountEdgeCases() {
        recreateAuction();
        System.out.println("with no bids added");
        Assert.assertEquals(0, am.getBidCount());

        recreateAuction();
        System.out.println("only invalid bids attempted to be added");
        am.addBid(-5, u1);
        am.addBid(10, null);
        am.addBid(0, null);
        Assert.assertEquals(0, am.getBidCount());
    }

    @Test
    public void testGetWinnerEdgeCases() {

        recreateAuction();
        System.out.println("auction with invalid bid");
        am.addBid(6.01, u1);
        am.addBid(-5.00, u1);
        am.addBid(2.00, u2);
        Assert.assertEquals(6.01, am.getWinner().getValue());
        Assert.assertEquals(u1, am.getWinner().getUser());

        recreateAuction();
        System.out.println("calling before auction completion");
        am.addBid(6.00, u1);
        am.addBid(11.00, u2);
        am.addBid(5.00, u1);
        Assert.assertNull(am.getWinner());

        recreateAuction();
        System.out.println("duplicate values by same user");
        am.addBid(6.00, u1);
        am.addBid(4.00, u2);
        am.addBid(6.00, u1);
        Assert.assertEquals(6.00, am.getWinner().getValue());
        Assert.assertEquals(u1, am.getWinner().getUser());

        recreateAuction();
        System.out.println("duplicate values from different users should be won by earlier voter");
        am.addBid(6.00, u1);
        am.addBid(6.00, u2);
        Assert.assertEquals(6.00, am.getWinner().getValue());
        Assert.assertEquals(u1, am.getWinner().getUser());

        recreateAuction();
        System.out.println("duplicate values from different users, but one user tops after");
        am.addBid(6.00, u1);
        am.addBid(6.00, u2);
        am.addBid(7.00, u2);
        Assert.assertEquals(7.00, am.getWinner().getValue());
        Assert.assertEquals(u2, am.getWinner().getUser());

        recreateAuction();
        System.out.println("adding highest bid after auction has closed (therefore this bid should not be added)");
        am.addBid(10.00, u1);
        am.addBid(20.00, u2);
        am.endAuction();
        am.addBid(40.00, u1);
        Assert.assertEquals(2, am.getBidCount());
        Assert.assertEquals(20.00, am.getWinner().getValue());
        Assert.assertEquals(u2, am.getWinner().getUser());

        recreateAuction();
        System.out.println("no bids have been made, expect no winning bid");
        Assert.assertNull(am.getWinner());

        recreateAuction();
        System.out.println("checking 1 cent different noticed");
        am.addBid(10.00, u1);
        am.addBid(10.01, u2);
        Assert.assertEquals(10.01, am.getWinner().getValue());
        Assert.assertEquals(u2, am.getWinner().getUser());

        recreateAuction();
        System.out.println("numbers past 100's decimal pos will be dropped, not rounded");
        am.addBid(9.9999999, u1);
        am.addBid(10.00, u2);
        Assert.assertEquals(10.00, am.getWinner().getValue());
        Assert.assertEquals(u2, am.getWinner().getUser());

    }

    @Test
    public void testGetSecondsRemainingEdgeCases() {

        recreateAuction();
        System.out.println("before auction ended");
        Assert.assertTrue(am.getSecondsRemaining() > 0);

        recreateAuction();
        System.out.println("well after auction ended");
        Assert.assertEquals(0, am.getSecondsRemaining());
    }
}
