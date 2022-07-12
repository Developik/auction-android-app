package comp3350.bms.business;

// Purpose: AuctionManager handles the business logic for the auctions; the auctions will be
// sealed so the bidders do not know the highest bid.

import java.util.ArrayList;

import comp3350.bms.objects.Bid;
import comp3350.bms.objects.User;

public class AuctionManager {
    private long startTimeSec;
    private long endTimeSec;
    private ArrayList<Bid> bids;

    public AuctionManager(int durationSeconds) {
        if (durationSeconds <= 0) {
            throw new NumberFormatException("Cannot have Auction of <= 0 minutes!");
        }
        this.startTimeSec = getTimeSec();
        this.endTimeSec = startTimeSec + durationSeconds;
        this.bids = new ArrayList<>();
    }

    public boolean addBid(double value, User user) {
        long curTimeSec = getTimeSec();
        if (value > 0 && curTimeSec < endTimeSec && user != null) {
            // duplicate bids from same user not allowed
            for (int i = 0; i < bids.size(); i++) {
                if (bids.get(i).getUser().equals(user) && bids.get(i).getValue() == value) {
                    return false;
                }
            }
            // add bid
            Bid b = new Bid(value, user);
            bids.add(b);
            user.setBid(b);
            return true;
        }
        return false;
    }

    public int getBidCount() {
        return bids.size();
    }

    public Bid getWinner() {
        long curTimeSec = getTimeSec();
        if (curTimeSec < endTimeSec) return null;
        if (bids == null || bids.size() == 0) return null;

        Bid highestBid = bids.get(0);
        for (int i = 1; i < bids.size(); i++) {
            Bid curBid = bids.get(i);
            if (curBid.getValue() > highestBid.getValue()) {
                highestBid = curBid;
            }
        }
        return highestBid;
    }

    public void endAuction() {
        endTimeSec = 0;
    }

    public int getSecondsRemaining() {
        int secondsRemaining = (int) (endTimeSec - getTimeSec());
        return Math.max(0, secondsRemaining);
    }

    private long getTimeSec() {
        return System.currentTimeMillis() / 1000L;
    }

}
