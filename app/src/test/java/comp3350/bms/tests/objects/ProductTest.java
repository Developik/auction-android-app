package comp3350.bms.tests.objects;

import org.junit.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import comp3350.bms.objects.Product;

public class ProductTest {

	Product product;
	Date date;
	LocalDateTime start;
	LocalDateTime end;
	String picture;
	String name;
	String category;
	String description;
	List<String> categories = Arrays.asList("Books", "Watches", "Garden");

	@Before
	public void setUp() {
		name = "Garden Bucket";
		date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		start = LocalDateTime.now().minusDays(1L);
		end = LocalDateTime.now().minusHours(1L);
		picture = "1.png";
		category = categories.get(2);
		description = "A bucket for gardening.";
		try{
			product = new Product(name, start, end, picture, description, category);
		}
		catch (Exception e){
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

		Assert.assertEquals(product.getCategory(), category);
	}

	@Test
	public void testName() {
		name = "";
		try {
			product = new Product(name, start, end, picture, description, category);
			Assert.fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}

		try {
			product = new Product(null, start, end, picture, description, category);
			Assert.fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}
	}

	@Test
	public void testStartDate() {
		try {
			product = new Product(name, null, end, picture, description, category);
			Assert.fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}
	}

	@Test
	public void testEndDate() {
		try {
			product = new Product(name, start, null, picture, description, category);
			Assert.fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}
	}

	@Test
	public void testCategory() {
		try {
			product = new Product(name, start, end, picture, description, null);
			Assert.fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}

		category = "";
		try {
			product = new Product(name, start, end, picture, description, category);
			Assert.fail("Creation of Item succeeded when it shouldn't have been.");
		} catch (Exception ignored) {}
	}

}
