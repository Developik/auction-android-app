package comp3350.bms.tests.objects;

// Purpose: Tests the Product object and its functions

import org.junit.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import comp3350.bms.objects.Product;

public class ProductTest {

    Product product;
    Date date;
    Date start;
    Date end;
    String picture;
    double startingBid;
    double currentBid;
    boolean sold;
    String name;
    String category;
    List<String> categories = Arrays.asList("Books", "Watches", "Garden");

    @Before
    public void setUp() {
        name = "Garden Bucket";
        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        picture = "1.png";
        startingBid = 5.0;
        currentBid = 5.0;
        sold = false;
        category = categories.get(2);
        try {
            product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
        } catch (Exception e) {
            Assert.fail("Creation of Item failed.");
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testProductCreatedCorrectly() {
        Assert.assertEquals(product.getName(), "Garden Bucket");
        Assert.assertEquals(product.getDatePosted(), date);
        Assert.assertEquals(product.getAuctionStart(), start);
        Assert.assertEquals(product.getAuctionEnd(), end);
        Assert.assertEquals(product.getPicture(), picture);
        Assert.assertEquals(product.getStartingBid(), startingBid, 0.00001);
        Assert.assertEquals(product.getCurrentBid(), currentBid, 0.00001);
        Assert.assertEquals(product.isSold(), sold);
        Assert.assertEquals(product.getCategory(), category);
    }

    @Test
    public void testName() {
        name = "";
        try {
            product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
            Assert.fail("Creation of Item succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        name = null;
        try {
            product = new Product(null, date, picture, startingBid, currentBid, start, end, sold, category);
            Assert.fail("Creation of Item succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testStartDate() {
        start = null;
        try {
            product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
            Assert.fail("Creation of Item succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testEndDate() {
        end = null;
        try {
            product = new Product(name, date, picture, startingBid, currentBid, start, null, sold, category);
            Assert.fail("Creation of Item succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testCreationDate() {
        date = null;
        try {
            product = new Product(name, null, picture, startingBid, currentBid, start, end, sold, category);
            Assert.fail("Creation of Item succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testCurrentBid() {
        currentBid = -1;
        try {
            product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
            Assert.fail("Creation of Item succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        currentBid = Integer.MIN_VALUE;
        try {
            product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
            Assert.fail("Creation of Item succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testStartingBid() {
        startingBid = -1;
        try {
            product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
            Assert.fail("Creation of Item succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        startingBid = Integer.MIN_VALUE;
        try {
            product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
            Assert.fail("Creation of Item succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testCategory() {
        category = null;
        try {
            product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, null);
            Assert.fail("Creation of Item succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }

        category = "";
        try {
            product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
            Assert.fail("Creation of Item succeeded when it shouldn't have been.");
        } catch (Exception ignored) {
        }
    }

}
