package comp3350.srsys.tests.business;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
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
import comp3350.srsys.persistence.DataAccess;
import comp3350.srsys.tests.persistence.DataAccessStub;

public class ProductLogicTest {

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
	private String picture;

	private int productInitSize;
	private DataAccess dataAccess;

	@Before
	public void setUp() throws Exception {
		dataAccess = new DataAccessStub();
		dataAccess.open("Stub");
		//accessProducts = new AccessProducts();
		productList = new ArrayList<>();
		dataAccess.getProductSequential(productList);

		productInitSize = productList.size();
	}

	@After
	public void tearDown() {

	}

	@Test
	public void testBasicFilters(){
		productList = new ArrayList<>();

		filters = new HashMap<>();
		String query = "";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		dataAccess.getProductSequential(productList);

		filteredList = ProductLogic.filterFeed(filters, productList);
		Assert.assertEquals(productList.size(), productInitSize);
		Assert.assertEquals(filteredList.size(), productInitSize);
	}

	@Test
	public void testNullFilters(){
		productList = new ArrayList<>();

		date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		picture = "3.png";
		try{
			product = new Product("Garden Bucket 2", date, picture, 5.0, 5.0, start, end, false,
					"TestCategory");
			dataAccess.insertProduct(product);
		}
		catch (Exception ignored){}

		filters = null;
		dataAccess.getProductSequential(productList);
		filteredList = ProductLogic.filterFeed(filters, productList);

		Assert.assertEquals(productList.size(), productInitSize + 1);
		Assert.assertEquals(filteredList.size(), productInitSize + 1);
	}

	@Test
	public void testQueryBidFilters(){
		productList = new ArrayList<>();

		date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		picture = "3.png";
		try{
			product = new Product("Garden Bucket 1234125252423223253",
					date, picture, 5.0, 14241, start, end, false,
					"TestCategory");
			dataAccess.insertProduct(product);
		}
		catch (Exception ignored){}

		filters = new HashMap<>();
		String query = "Garden Bucket 1234125252423223253";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		dataAccess.getProductSequential(productList);
		filteredList = ProductLogic.filterFeed(filters, productList);

		Assert.assertEquals(productList.size(), productInitSize + 1);
		Assert.assertEquals(filteredList.size(), 1);

		query = "";
		String minBid = "14241";
		String maxBid = "14242";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		Assert.assertEquals(productList.size(), productInitSize + 1);
		Assert.assertEquals(filteredList.size(), 1);

		query = "";
		minBid = "0";
		maxBid = "1";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		filteredList = ProductLogic.filterFeed(filters, productList);

		Assert.assertEquals(productList.size(), productInitSize + 1);
		Assert.assertEquals(filteredList.size(), 0);

		query = "";
		minBid = "0";
		maxBid = "0";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		filteredList = ProductLogic.filterFeed(filters, productList);

		Assert.assertEquals(productList.size(), productInitSize + 1);
		Assert.assertEquals(filteredList.size(), 0);

		query = null;
		minBid = "14241";
		maxBid = "14241";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		filteredList = ProductLogic.filterFeed(filters, productList);

		Assert.assertEquals(productList.size(), productInitSize + 1);
		Assert.assertEquals(filteredList.size(), 1);

		minBid = "14241";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		filteredList = ProductLogic.filterFeed(filters, productList);

		Assert.assertEquals(productList.size(), productInitSize + 1);
		Assert.assertEquals(filteredList.size(), 1);

		query = null;
		maxBid = null;
		minBid = "14242";
		filters.put("query", query);
		filters.put("minMaxBid", minBid + ";" + maxBid);

		filteredList = ProductLogic.filterFeed(filters, productList);

		Assert.assertEquals(productList.size(), productInitSize + 1);
		Assert.assertEquals(filteredList.size(), 0);
	}
}
