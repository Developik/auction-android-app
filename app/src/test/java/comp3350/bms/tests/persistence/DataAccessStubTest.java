package comp3350.bms.tests.persistence;

// Purpose: a unit test class to test the DataAccessStub class

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;

public class DataAccessStubTest {
    private DataAccessStub dataAccessStub;
    private ArrayList<User> users;
    private ArrayList<Product> products;

    public DataAccessStubTest() {
    }

    @Before
    public void setUp() throws Exception {
        dataAccessStub = new DataAccessStub();
        dataAccessStub.open("stub");
    }

    @After
    public void tearDown() {
        System.out.println("Finished Persistence test DataAccess (using stub)");
    }

    @Test
    public void testStubGetUsers() throws Exception {
        User u;

        users = dataAccessStub.getUsers();
        assert (users.size() == 2);
        u = users.get(0);
        assert ("joedoe".equals(u.getUsername()));
        u = users.get(1);
        assert ("easyUser".equals(u.getUsername()));
        u = new User("testUser", "first", "last", "123 street", 1, false);
        users.add(u);
        u = users.get(2);
        assert ("testUser".equals(u.getUsername()));
    }

    @Test
    public void testStubGetProducts() throws Exception {
        Product p;

        products = dataAccessStub.getAllProducts();
        assert (products.size() == 3);
        p = products.get(0);
        assert ("test product".equals(p.getName()));
        p = products.get(1);
        assert ("Rolex Watch".equals(p.getName()));
        p = products.get(2);
        assert ("Garden Bucket".equals(p.getName()));
        p = new Product("new product", new Date(), "pic", 45.00, 0.00, new Date(), new Date(), false, "random");
        dataAccessStub.insertProduct(p);
        p = products.get(3);
        assert ("new product".equals(p.getName()));
    }
}