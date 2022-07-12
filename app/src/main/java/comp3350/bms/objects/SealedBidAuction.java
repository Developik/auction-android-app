package comp3350.bms.objects;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class SealedBidAuction {
    private ArrayList<Bid> bids;
    private LocalDateTime auctionStart;
    private LocalDateTime auctionEnd;
    private AuctionStatus status;

    public SealedBidAuction(LocalDateTime start, LocalDateTime end) {
        this.bids = new ArrayList<>();
        this.auctionStart = start;
        this.auctionEnd = end;
    }

    public LocalDateTime getAuctionStart() {
        return this.auctionStart;
    }

    public LocalDateTime getAuctionEnd() {
        return this.auctionEnd;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public AuctionStatus getAuctionStatus() {
        AuctionStatus returnStatus = AuctionStatus.NOT_STARTED;
        LocalDateTime currTime = LocalDateTime.now();

        if (this.auctionStart.compareTo(currTime) < 0) {
            returnStatus = AuctionStatus.IN_PROGRESS;
            if (this.auctionEnd.compareTo(currTime) < 0) {
                returnStatus = AuctionStatus.COMPLETED;
            }
        }

        return returnStatus;
    }

    public void addBid(Bid bid) {
        this.bids.add(bid);
    }

    public int getBidCount() {
        return bids.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Bid getHighestBid() {
        Bid highest = null;

        if (!bids.isEmpty()) {
            highest = bids.get(0);
            Bid curr;

            for (int i = 0; i < bids.size(); i++) {
                curr = bids.get(i);
                if (curr.getBidAmount() > highest.getBidAmount()) {
                    // if the bid is higher we set it to highest
                    highest = curr;
                } else if (curr.getBidAmount() == highest.getBidAmount()) {
                    // if we have a tie
                    if (curr.getBidDate().compareTo(highest.getBidDate()) < 0) {
                        // the earlier bid wins
                        highest = curr;
                    }
                }
            }
        }

        return highest;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Bid getLowestBid() {
        Bid lowest = null;

        if (!bids.isEmpty()) {
            lowest = bids.get(0);
            Bid curr;

            for (int i = 0; i < bids.size(); i++) {
                curr = bids.get(i);
                if (curr.getBidAmount() < lowest.getBidAmount()) {
                    // if the bid is higher we set it to highest
                    lowest = curr;
                } else if (curr.getBidAmount() == lowest.getBidAmount()) {
                    // if we have a tie
                    if (curr.getBidDate().compareTo(lowest.getBidDate()) > 0) {
                        // the earlier bid wins
                        lowest = curr;
                    }
                }
            }
        }

        return lowest;
    }
}