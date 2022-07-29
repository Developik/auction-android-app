package comp3350.bms.business;

// Purpose: AuctionManager handles the business logic for the auctions; the auctions will be
// sealed so the bidders do not know the highest bid.

import java.util.ArrayList;
import java.util.Date;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.objects.Bid;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;
import comp3350.bms.persistence.DataAccess;

public class AuctionManager {
    DataAccess dataAccess;

    public AuctionManager() {
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public String getAllBidsForProduct(ArrayList<Bid> bids, Product product) {
        return dataAccess.getAllBidsForProduct(bids, product);
    }

    public void addBid(double value, Product product, User user) {
        // add bid getCurrTimestamp
        int bidID = generateBidID();
        Date date = new Date(System.currentTimeMillis());
        Bid bid = new Bid(bidID, value, date);
        dataAccess.insertBid(product, bid, user);
    }

    public Bid getHighestBid(Product product) {
        return dataAccess.getHighestBid(product);
    }

    public User getOwnerOfBid(Bid bid) {
        return dataAccess.getOwnerOfBid(bid);
    }

    public int generateBidID() {
        //getBidsSequential
        return dataAccess.getBidsNumber() + 1;
    }

}
