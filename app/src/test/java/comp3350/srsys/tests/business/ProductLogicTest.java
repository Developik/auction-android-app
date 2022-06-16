package comp3350.srsys.tests.business;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import comp3350.srsys.application.Main;
import comp3350.srsys.business.AccessProducts;
import comp3350.srsys.business.ProductLogic;
import comp3350.srsys.objects.Product;

public class ProductLogicTest extends TestCase {

	private AccessProducts accessProducts;
	private ArrayList<Product> productList;
	private HashMap<String, String> filters;

	private Product product;
	private ArrayList<Product> filteredList;

	private String query;
	private String minBid;
	private String maxBid;

	private Date date;
	private Date start;
	private Date end;
	private ArrayList<String> pictures;

	private int productInitSize;


	@Before
	public void setUp() throws Exception {
		Main.startUp();
		accessProducts = new AccessProducts();
		productList = new ArrayList<>();
		accessProducts.getProducts(productList);

		productInitSize = productList.size();
	}

	@After
	public void tearDown() {
		Main.shutDown();
	}

	@Test
	public void testBasicFilters(){
		accessProducts = new AccessProducts();
		productList = new ArrayList<>();

		filters = new HashMap<>();
		String query = "";
		String minBid = "5";
		String maxBid = "25";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		accessProducts.getProducts(productList);

		filteredList = ProductLogic.filterFeed(filters, productList);
		assertEquals(productList.size(), productInitSize);
		assertEquals(filteredList.size(), productInitSize);
	}

	@Test
	public void testNullFilters(){
		accessProducts = new AccessProducts();
		productList = new ArrayList<>();

		date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		pictures = new ArrayList<>(Arrays.asList("3.png"));
		try{
			product = new Product("Garden Bucket 2", date, pictures, 5.0, 5.0, start, end, false,
					"TestCategory");
			accessProducts.insertProduct(product);
		}
		catch (Exception ignored){}

		filters = null;
		accessProducts.getProducts(productList);
		filteredList = ProductLogic.filterFeed(filters, productList);

		assertEquals(productList.size(), productInitSize+1);
		assertEquals(filteredList.size(), productInitSize+1);
	}

	@Test
	public void testQueryBidFilters(){
		accessProducts = new AccessProducts();
		productList = new ArrayList<>();

		date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		pictures = new ArrayList<>(Arrays.asList("3.png"));
		try{
			product = new Product("Garden Bucket 1234125252423223253",
					date, pictures, 5.0, 14241, start, end, false,
					"TestCategory");
			accessProducts.insertProduct(product);
		}
		catch (Exception ignored){}

		filters = new HashMap<>();
		String query = "Garden Bucket 1234125252423223253";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		accessProducts.getProducts(productList);
		filteredList = ProductLogic.filterFeed(filters, productList);

		assertEquals(productList.size(), productInitSize+1);
		assertEquals(filteredList.size(), 1);

		query = "";
		String minBid = "14241";
		String maxBid = "14242";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		assertEquals(productList.size(), productInitSize+1);
		assertEquals(filteredList.size(), 1);

		query = "";
		minBid = "0";
		maxBid = "1";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		filteredList = ProductLogic.filterFeed(filters, productList);

		assertEquals(productList.size(), productInitSize+1);
		assertEquals(filteredList.size(), 0);

		query = "";
		minBid = "0";
		maxBid = "0";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		filteredList = ProductLogic.filterFeed(filters, productList);

		assertEquals(productList.size(), productInitSize+1);
		assertEquals(filteredList.size(), 0);

		query = null;
		minBid = "14241";
		maxBid = "14241";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		filteredList = ProductLogic.filterFeed(filters, productList);

		assertEquals(productList.size(), productInitSize+1);
		assertEquals(filteredList.size(), 1);

		minBid = "14241";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		filteredList = ProductLogic.filterFeed(filters, productList);

		assertEquals(productList.size(), productInitSize+1);
		assertEquals(filteredList.size(), 1);

		query = null;
		maxBid = null;
		minBid = "14242";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		filteredList = ProductLogic.filterFeed(filters, productList);

		assertEquals(productList.size(), productInitSize+1);
		assertEquals(filteredList.size(), 0);
	}
}
