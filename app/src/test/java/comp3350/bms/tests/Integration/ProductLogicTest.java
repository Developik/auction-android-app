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

    private ArrayList<Product> filteredList;

    private String query;

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
    public void testSearchQueryEmptyStub() {
        setup();

        productList = new ArrayList<>();

        filters = new HashMap<>();
        String query = "";
        filters.put("query", query);

        dataAccess.getProductSequential(productList);

        filteredList = ProductLogic.filterFeed(filters, productList);
        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(productInitSize, filteredList.size());

        shutdown();
    }

    @Test
    public void testNullFiltersStub() {
        setup();//start test with stub Database

        productList = new ArrayList<>();

        filters = null;
        dataAccess.getProductSequential(productList);
        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(productInitSize, filteredList.size());

        shutdown();//close Database
    }

    @Test
    public void testQueryBidFiltersStub() {
        setup();//start test with stubDatabase

        productList = new ArrayList<>();

        filters = new HashMap<>();
        query = "Garden Bucket";
        filters.put("query", query);

        dataAccess.getProductSequential(productList);
        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(1, filteredList.size());

        shutdown();//close Database
    }

    @Test
    public void testNullQueryBidFiltersStub() {
        setup();//start test with stubDatabase

        productList = new ArrayList<>();

        filters = new HashMap<>();
        filters.put("query", null);

        dataAccess.getProductSequential(productList);
        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(3, filteredList.size());

        shutdown();//close Database
    }

    @Test
    public void testSearchQueryEmptyReal() {
        setupReal();

        productList = new ArrayList<>();

        filters = new HashMap<>();
        String query = "";
        filters.put("query", query);

        dataAccess.getProductSequential(productList);

        filteredList = ProductLogic.filterFeed(filters, productList);
        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(productInitSize, filteredList.size());

        shutdown();
    }

    @Test
    public void testNullFiltersReal() {
        setupReal();

        productList = new ArrayList<>();

        filters = null;
        dataAccess.getProductSequential(productList);
        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(productInitSize, filteredList.size());

        shutdown();//close Database
    }

    @Test
    public void testQueryBidFiltersReal() {
        setupReal();

        productList = new ArrayList<>();

        filters = new HashMap<>();
        query = "Garden Bucket";
        filters.put("query", query);

        dataAccess.getProductSequential(productList);
        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(5, filteredList.size());

        shutdown();//close Database
    }

    @Test
    public void testNullQueryBidFiltersReal() {
        setupReal();

        productList = new ArrayList<>();

        filters = new HashMap<>();
        filters.put("query", null);

        dataAccess.getProductSequential(productList);
        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(7, filteredList.size());

        shutdown();//close Database
    }

    @Test
    public void testAddingProductStub(){
        setup();

        Product product;
        Date date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        Date start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        Date end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        String picture = "nothing";

        try {
            long id = 101;
            product = new Product(id, "Car", date, picture, 5.0, 5.0, start, end, false,
                    "TestCategory");
            dataAccess.insertProduct(product);
        } catch (Exception ignored) {
        }

        // Reinitialize the product list
        productList = new ArrayList<>();
        dataAccess.getProductSequential(productList);
        productInitSize = productList.size();

        filters = new HashMap<>();
        filters.put("query", "Car");

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(1, filteredList.size());

        shutdown();//close Database
    }

    @Test
    public void testAddingProductReal(){
        setupReal();

        Product product;
        Date date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        Date start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        Date end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        String picture = "nothing";

        try {
            long id = 101;
            product = new Product(id, "Car", date, picture, 5.0, 5.0, start, end, false,
                    "TestCategory");
            dataAccess.insertProduct(product);
        } catch (Exception ignored) {
        }

        // Reinitialize the product list
        productList = new ArrayList<>();
        dataAccess.getProductSequential(productList);
        productInitSize = productList.size();

        filters = new HashMap<>();
        filters.put("query", "Car");

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(1, filteredList.size());

        shutdown();//close Database
    }

    @Test
    public void testRemovingProductStub(){
        setup();

        Product product;
        Date date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        Date start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        Date end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        String picture = "nothing";

        try {
            long id = 101;
            product = new Product(id, "Car", date, picture, 5.0, 5.0, start, end, false,
                    "TestCategory");
            dataAccess.removeProduct(product);
        } catch (Exception ignored) {
        }

        // Reinitialize the product list
        productList = new ArrayList<>();
        dataAccess.getProductSequential(productList);
        productInitSize = productList.size();

        filters = new HashMap<>();
        filters.put("query", "Car");

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(0, filteredList.size());

        shutdown();//close Database
    }

    @Test
    public void testRemovingProductReal(){
        setupReal();

        Product product;
        Date date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        Date start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        Date end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        String picture = "nothing";

        try {
            long id = 101;
            product = new Product(id, "Car", date, picture, 5.0, 5.0, start, end, false,
                    "TestCategory");
            dataAccess.removeProduct(product);
        } catch (Exception ignored) {
        }

        // Reinitialize the product list
        productList = new ArrayList<>();
        dataAccess.getProductSequential(productList);
        productInitSize = productList.size();

        filters = new HashMap<>();
        filters.put("query", "Car");

        filteredList = ProductLogic.filterFeed(filters, productList);

        Assert.assertEquals(productInitSize, productList.size());
        Assert.assertEquals(0, filteredList.size());

        shutdown();//close Database
    }
}
