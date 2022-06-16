package comp3350.srsys.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.objects.Product;
import comp3350.srsys.persistence.DataAccessStub;

public class AccessProducts
{
	private DataAccessStub dataAccess;
	private List<Product> products;
	private Product product;
	private int currentItem;

	public AccessProducts() {
		dataAccess = (DataAccessStub) Services.getDataAccess(Main.dbName);
        products = null;
        product = null;
		currentItem = 0;
	}

    public ArrayList<Product> getAllProducts() {
        return dataAccess.getAllProducts();
    }

    public String getProducts(List<Product> products) {
        products.clear();
        return dataAccess.getProductSequential(products);
    }

    public Product getSequential() {
        String result = null;
        if (products == null) {
            products = new ArrayList<Product>();
            result = dataAccess.getProductSequential(products);
            currentItem = 0;
        }
        if (currentItem < products.size()) {
            product = (Product) products.get(currentItem);
            currentItem++;
        }
        else {
            products = null;
            product = null;
            currentItem = 0;
        }
        return product;
    }

	public String insertProduct(Product currentProduct) {
		return dataAccess.insertProduct(currentProduct);
	}

	public String updateProduct(Product currentProduct) {
		return dataAccess.updateProduct(currentProduct);
	}

}
