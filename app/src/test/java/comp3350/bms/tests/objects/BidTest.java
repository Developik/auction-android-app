package comp3350.bms.tests.objects;

import junit.framework.TestCase;

import comp3350.bms.objects.Bid;
import comp3350.bms.objects.User;

public class BidTest extends TestCase {

    public void testConstructor(){
        System.out.println("Testing Bid constructor");

        System.out.println("normal test");
        User u = null;
        try {
            u = new User("username", "john", "smith", "55 road dr.", 44, false);
        }
        catch (Exception e){
            fail();
        }

        Bid bid = new Bid(50, u);

        System.out.println("with null user should throw exception");
        try{
            Bid badBid = new Bid(50, null);
            assertEquals(1, 2);
        }
        catch(NullPointerException e){
            // should get here
            assertEquals(1, 1);
        }

    }
}