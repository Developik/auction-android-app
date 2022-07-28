package comp3350.bms.objects;

// Purpose: a connection object between Product and Bid objects

public class ProductBid {
    private int productID;
    private int bidID;

    public ProductBid(int productID, int bidID) {
        if (productID < 0)
            throw new NullPointerException("productID cannot be less than 0");
        if (bidID < 0)
            throw new NullPointerException("bidID cannot be less than 0");

        this.productID = productID;
        this.bidID = bidID;
    }

    public int getProductID() {
        return productID;
    }

    public int getBidID() {
        return bidID;
    }

}
