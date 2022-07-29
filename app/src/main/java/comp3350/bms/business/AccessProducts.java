package comp3350.bms.business;

// Purpose: AccessProducts handles the business logic for the products, which accesses them from the database.

import java.util.ArrayList;
import java.util.List;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.objects.Product;
import comp3350.bms.persistence.DataAccess;

public class AccessProducts {
    private DataAccess dataAccess;

    public AccessProducts() {
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public ArrayList<Product> getAllProducts() {
        return dataAccess.getAllProducts();
    }

    public String getProducts(List<Product> products) {
        products.clear();
        return dataAccess.getProductSequential(products);
    }
}
