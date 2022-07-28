package comp3350.bms.objects;

// Purpose: a connection object between Product and User objects

public class ProductUser {
    private int productID;
    private String username;

    public ProductUser(int productID, String username) {
        if (productID < 0)
            throw new NullPointerException("productID cannot be less than 0");

        this.productID = productID;
        this.username = username;
    }

    public int getProductID() {
        return productID;
    }

    public String getUsername() {
        return username;
    }

}
