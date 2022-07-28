package comp3350.bms.tests.Integration;

// Purpose: Tests the ProductLogic business object and its functions

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.business.ProductLogic;
import comp3350.bms.objects.Product;
import comp3350.bms.persistence.DataAccess;
import comp3350.bms.tests.persistence.DataAccessStub;

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
    DataAccess dataAccess;

    public void setup() {
        dataAccess = new DataAccessStub();
        try {
            Services.createDataAccess(dataAccess);
            productList = new ArrayList<>();
            dataAccess.getProductSequential(productList);

            productInitSize = productList.size();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    public void setupReal() {
        Main.startUp();
        dataAccess = Services.getDataAccess(Main.dbName);

        productList = new ArrayList<>();
        dataAccess.getProductSequential(productList);

        productInitSize = productList.size();
    }

    public void shutdown() {
        Services.closeDataAccess();
    }

    @Test
    public void testBasicFilters() {
        setup();

        productList = new ArrayList<>();

        filters = new HashMap<>();
        String query = "";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        dataAccess.getProductSequential(productList);

        filteredList = ProductLogic.filterFeed(filters, productList);
        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(productInitSize, filteredList.size());

        shutdown();
    }

    @Test
    public void testNullFilters() {
        setup();//start test with stub Database

        productList = new ArrayList<>();

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        picture = "3.png";
        try {
            product = new Product("Garden Bucket 2", date, picture, 5.0, 5.0, start, end, false,
                    "TestCategory");
            dataAccess.insertProduct(product);
        } catch (Exception ignored) {
        }

        filters = null;
        dataAccess.getProductSequential(productList);
        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(productInitSize + 1, filteredList.size());

        shutdown();//close Database
    }

    @Test
    public void testQueryBidFilters() {
        setup();//start test with stubDatabase

        productList = new ArrayList<>();

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        picture = "3.png";
        try {
            product = new Product("Garden Bucket 1234125252423223253",
                    date, picture, 5.0, 14241, start, end, false,
                    "TestCategory");
            dataAccess.insertProduct(product);
        } catch (Exception ignored) {
        }

        filters = new HashMap<>();
        query = "Garden Bucket 1234125252423223253";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        dataAccess.getProductSequential(productList);
        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(1, filteredList.size());

        query = "";
        minBid = "14241";
        maxBid = "14242";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(1, filteredList.size());

        query = "";
        minBid = "0";
        maxBid = "1";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(0, filteredList.size());

        query = "";
        minBid = "0";
        maxBid = "0";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(0, filteredList.size());

        query = null;
        minBid = "14241";
        maxBid = "14241";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(1, filteredList.size());

        minBid = "14241";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(1, filteredList.size());

        query = null;
        maxBid = null;
        minBid = "14242";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(0, filteredList.size());

        shutdown();//close Database
    }

    @Test
    public void testNullFiltersRealDB() {
        setupReal();//start test with real Database

        productList = new ArrayList<>();

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        picture = "3.png";
        try {
            product = new Product("Garden Bucket 2", date, picture, 5.0, 5.0, start, end, false,
                    "TestCategory");
            dataAccess.insertProduct(product);
        } catch (Exception ignored) {
        }

        filters = null;
        dataAccess.getProductSequential(productList);
        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(productInitSize + 1, filteredList.size());

        shutdown();//close Database
    }

    @Test
    public void testQueryBidFiltersRealDB() {
        setupReal();//start test with real Database

        productList = new ArrayList<>();

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        picture = "3.png";
        try {
            product = new Product("Garden Bucket 1234125252423223253",
                    date, picture, 5.0, 14241, start, end, false,
                    "TestCategory");
            dataAccess.insertProduct(product);
        } catch (Exception ignored) {
        }

        filters = new HashMap<>();
        query = "Garden Bucket 1234125252423223253";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        dataAccess.getProductSequential(productList);
        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(3, filteredList.size());

        query = "";
        minBid = "14241";
        maxBid = "14242";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(3, filteredList.size());

        query = "";
        minBid = "0";
        maxBid = "1";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(0, filteredList.size());

        query = "";
        minBid = "0";
        maxBid = "0";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(0, filteredList.size());

        query = null;
        minBid = "14241";
        maxBid = "14241";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(3, filteredList.size());

        minBid = "14241";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(3, filteredList.size());

        query = null;
        maxBid = null;
        minBid = "14242";
        filters.put("query", query);
        filters.put("minMaxBid", minBid + ";" + maxBid);

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize + 1, productList.size());
        Assert.assertEquals(0, filteredList.size());

        shutdown();//close Database
    }
}
