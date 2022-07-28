package comp3350.bms.objects;

// Purpose: Product object that contains all details about the product such as the name, date,
// when the auction ends for that product, the current bid, what category it is, and whether
// it has been sold or not.

import java.sql.Timestamp;
import java.util.Date;

import comp3350.bms.business.ProductLogic;

public class Product {
    private Long itemID;
    private String name;
    private Date datePosted;
    private String picture;
    private double startingBid;
    private double currentBid;
    private Date auctionStart;
    private Date auctionEnd;
    private boolean sold;
    private String category;

    // pre-generated Product
    public Product(Long itemID, String name, Date datePosted, String picture, double startingBid,
                   double currentBid, Date auctionStart, Date auctionEnd, boolean sold,
                   String category) throws Exception {
        // separate productLogic later
        this.itemID = itemID;
        this.name = name;
        this.datePosted = datePosted;
        this.picture = picture;
        this.startingBid = startingBid;
        this.currentBid = currentBid;
        this.auctionStart = auctionStart;
        this.auctionEnd = auctionEnd;
        this.sold = sold;
        this.category = category;

        if (!itemObjectValidation()) {
            throw new Exception("Product Item parameters are incorrect!");
        }
    }

    public Long getItemID() {
        return (itemID);
    }

    public String getName() {
        return (name);
    }

    public String getCategory() {
        return (category);
    }

    public Date getDatePosted() {
        return (datePosted);
    }

    public Timestamp getDatePostedTimestamp() {
        return (new Timestamp(datePosted.getTime()));
    }

    public double getCurrentBid() {
        return (currentBid);
    }

    public double getStartingBid() {
        return (startingBid);
    }

    public Date getAuctionStart() {
        return (auctionStart);
    }

    public Timestamp getAuctionStartTimestamp() {
        return (new Timestamp(auctionStart.getTime()));
    }

    public Date getAuctionEnd() {
        return (auctionEnd);
    }

    public Timestamp getAuctionEndTimestamp() {
        return (new Timestamp(auctionEnd.getTime()));
    }

    public String getPicture() {
        return (picture);
    }

    public boolean isSold() {
        return (sold);
    }

    public boolean itemObjectValidation() {
        boolean result = true;

        if (itemID < 1 || name == null || name.length() < 1 || datePosted == null ||
                category == null || category.length() < 1 || startingBid < 0 || (currentBid < 0) ||
                auctionStart == null || auctionEnd == null) {
            result = false;
        }

        return result;
    }

}
