package comp3350.srsys.business;

import java.util.ArrayList;
import java.util.Date;

import comp3350.srsys.objects.Bid;
import comp3350.srsys.objects.User;

// Purpose: Manage the auction.
//          Auction style is closed-book, so bidders don't know the highest bid

public class AuctionManager {
    private Date startDate;
    private Date endDate;
    private ArrayList<Bid> bids;

    public AuctionManager(Date endDate){
        this.startDate = new Date();
        this.endDate = endDate;
        this.bids = new ArrayList<>();
    }

    public boolean addBid(int value, User user){
        Date curDate = new Date();
        if(user != null && curDate.before(endDate)){
            Bid b = new Bid(value, user);
            bids.add(b);
            user.setBid(b);
            return true;
        }
        return false;
    }

    public int getBidCount(){
        return bids.size();
    }

    public Bid getWinner(){
        Date curDate = new Date();
        if(curDate.before(endDate)) return null;
        if(bids == null || bids.size() == 0) return null;

        Bid highestBid = bids.get(0);
        for(int i = 1; i < bids.size(); i++){
            Bid curBid = bids.get(i);
            if(curBid.getValue() > highestBid.getValue()){
                highestBid = curBid;
            }
        }
        return highestBid;
    }

    public int getMinutesRemaining(){
        int minutesRemaining;

        long millisecondsRemaining = endDate.getTime() - startDate.getTime();
        if(millisecondsRemaining <= 0){
            minutesRemaining = 0;
        }
        else{
            minutesRemaining = (int)(millisecondsRemaining / 60000) + 1;
        }
        return minutesRemaining;
    }

}
