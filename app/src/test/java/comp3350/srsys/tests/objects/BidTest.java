package comp3350.srsys.tests.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.srsys.objects.Bid;
import comp3350.srsys.objects.User;

public class BidTest {

    @Test(expected = NullPointerException.class)
    public void testConstructor(){
        System.out.println("Testing Bid constructor");

        System.out.println("normal test");
        User u = new User("username", "john","smith", "55 road dr.", 44);
        Bid bid = new Bid(50, u);
        assertNotNull(bid);

        System.out.println("with null user should throw exception");
        Bid badBid = new Bid(50, null);
    }
}