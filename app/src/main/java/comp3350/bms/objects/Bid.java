package comp3350.bms.objects;

// Purpose: Object to handle a "Bid" that contains the value (double) and the User (User) for
// that bid.

import java.util.Date;

public class Bid {

    private double value;
    private Date date;
    private int bidID;

    public Bid(int bidID, double value, Date date) {
        if (value < 0) {
            this.value = 0;
        } else {
            this.value = value;
        }
        this.date = date;
        this.bidID = bidID;

    }

    public double getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public int getBidID() {
        return bidID;
    }

}
