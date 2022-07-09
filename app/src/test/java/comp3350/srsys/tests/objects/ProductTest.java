package comp3350.srsys.tests.objects;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import comp3350.srsys.objects.Product;

public class ProductTest extends TestCase {

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
		try{
			product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
		}
		catch (Exception e){
			fail("Creation of Item failed.");
		}
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testProductCreatedCorrectly() {
		assertEquals(product.getName(), "Garden Bucket");
		assertEquals(product.getDatePosted(), date);
		assertEquals(product.getAuctionStart(), start);
		assertEquals(product.getAuctionEnd(), end);
		assertEquals(product.getPicture(), picture);

		assertEquals(product.getStartingBid(), startingBid, 0.00001);
		assertEquals(product.getCurrentBid(), currentBid, 0.00001);
		assertEquals(product.isSold(), sold);
		assertEquals(product.getCategory(), category);
	}

	@Test
	public void testName() {
		name = "";
		try {
			product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
			fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}

		name = null;
		try {
			product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
			fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}
	}

	@Test
	public void testStartDate() {
		start = null;
		try {
			product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
			fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}
	}

	@Test
	public void testEndDate() {
		end = null;
		try {
			product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
			fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}
	}

	@Test
	public void testCreationDate() {
		date = null;
		try {
			product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
			fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}
	}

	@Test
	public void testCurrentBid() {
		currentBid = -1;
		try {
			product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
			fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}

		currentBid = Integer.MIN_VALUE;
		try {
			product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
			fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}
	}

	@Test
	public void testStartingBid() {
		startingBid = -1;
		try {
			product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
			fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}

		startingBid = Integer.MIN_VALUE;
		try {
			product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
			fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}
	}

	@Test
	public void testCategory() {
		category = null;
		try {
			product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
			fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}

		category = "";
		try {
			product = new Product(name, date, picture, startingBid, currentBid, start, end, sold, category);
			fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}
	}

}
