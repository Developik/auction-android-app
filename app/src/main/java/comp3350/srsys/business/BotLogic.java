package comp3350.srsys.business;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import comp3350.srsys.objects.AuctionStatus;
import comp3350.srsys.objects.Bid;
import comp3350.srsys.objects.Product;

public class BotLogic
{
    public static Long generateID()
    {
        return new Date().getTime();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean assignBidToRandomProduct() {
        System.out.println("Attempt to bid rand Started!");
        AccessProducts accessProducts = new AccessProducts();
        ArrayList<Product> ProductList = new ArrayList<Product>();
        accessProducts.getProducts(ProductList);
        int min = 0;
        int max = ProductList.size();
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);

        Product randProduct = ProductList.get(randomNum);

        if (randProduct.getAuctionStatus() == AuctionStatus.COMPLETED) {
            return false;
        }

        Bid highest = randProduct.getHighestBid();


        Bid newBid = new Bid(null, randProduct, highest.getBidAmount() + 1);
        randProduct.addBid(newBid);

        System.out.println("NEW BID : " + newBid.getBidAmount());

        System.out.println("Bid rand Ended!");

        return true;
    }

}
