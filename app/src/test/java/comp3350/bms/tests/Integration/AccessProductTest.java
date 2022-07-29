package comp3350.bms.tests.Integration;

// Purpose: Tests the AccessProductTest business object and its functions

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.business.AccessProducts;
import comp3350.bms.objects.Product;
import comp3350.bms.persistence.DataAccess;
import comp3350.bms.tests.persistence.DataAccessStub;

public class AccessProductTest {

    AccessProducts accessProductTest;
    ArrayList<Product> itemList;
    String accessResult;
    DataAccess stubAccess;

    public void setup() {
        stubAccess = new DataAccessStub();
        try {
            Services.createDataAccess(stubAccess);

            itemList = new ArrayList<>();
            accessProductTest = new AccessProducts();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    public void setupReal() {
        Main.startUp();

        itemList = new ArrayList<>();
        accessProductTest = new AccessProducts();
    }

    public void shutdown() {
        Services.closeDataAccess();
    }

    //tests to make sure that a new AccessProduct object successfully
    //gets a valid list of Products from database
    @Test
    public void testAccessProduct() {

        setup();

        accessResult = accessProductTest.getProducts(itemList);
        Assert.assertNull(accessResult);//ensures no errors were thrown
        Assert.assertTrue(itemList.size() > 0);//ensures a populated list was returned
        for (int i = 0; i < itemList.size() - 1; i++) {
            //ensures valid products were returned
            Assert.assertTrue(itemList.get(i).itemObjectValidation());
        }

        shutdown();

    }

    //tests to make sure that a new AccessProduct object successfully
    //gets a valid list of Products from database using the real DB
    @Test
    public void testAccessProductWithRealDB() {

        setupReal();

        accessResult = accessProductTest.getProducts(itemList);
        Assert.assertNull(accessResult);//ensures no errors were thrown
        Assert.assertTrue(itemList.size() > 0);//ensures a populated list was returned
        for (int i = 0; i < itemList.size() - 1; i++) {
            //ensures valid products were returned
            Assert.assertTrue(itemList.get(i).itemObjectValidation());
        }

        shutdown();

    }
}
