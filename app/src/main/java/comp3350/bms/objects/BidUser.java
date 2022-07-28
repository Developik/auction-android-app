package comp3350.bms.objects;

// Purpose: a connection object between User and Bid objects

public class BidUser {
    private String username;
    private int bidID;

    public BidUser(int bidID, String username) {
        if (bidID < 0)
            throw new NullPointerException("bidID cannot be less than 0");

        this.username = username;
        this.bidID = bidID;
    }

    public int getBidID() {
        return bidID;
    }

    public String getUsername() {
        return username;
    }

}