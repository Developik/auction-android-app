package comp3350.srsys.objects;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDateTime;

public class Bid {
    private User bidder;
    private Product product;
    private double bidAmount;
    private LocalDateTime bidDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Bid(User bidder, Product product, double bidAmount) {
        this.bidder = bidder;
        this.product = product;
        this.bidAmount = bidAmount;
        this.bidDate = LocalDateTime.now();
    }

    public User getBidder() {
        return this.bidder;
    }

    public Product getProduct() {
        return this.product;
    }

    public double getBidAmount() {
        return this.bidAmount;
    }

    public LocalDateTime getBidDate() {
        return this.bidDate;
    }
}
