package comp3350.bms.objects;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDateTime;

public class Bid {
    private User bidder;
    private double bidAmount;
    private LocalDateTime bidDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Bid(User bidder, double bidAmount) {
        this.bidder = bidder;
        this.bidAmount = bidAmount;
        this.bidDate = LocalDateTime.now();
    }

    public User getBidder() {
        return this.bidder;
    }

    public double getBidAmount() {
        return this.bidAmount;
    }

    public LocalDateTime getBidDate() {
        return this.bidDate;
    }
}

