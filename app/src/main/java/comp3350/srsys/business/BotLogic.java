package comp3350.srsys.business;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import comp3350.srsys.objects.Product;

public class BotLogic
{
    public static Long generateID()
    {
        return new Date().getTime();
    }

    public static boolean assignBidToRandomProduct() {
        System.out.println("Attempt to bid rand Started!");
        AccessProducts accessProducts = new AccessProducts();
        ArrayList<Product> ProductList = new ArrayList<Product>();
        accessProducts.getProducts(ProductList);
        int min = 0;
        int max = ProductList.size();
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);

        Product randProduct = ProductList.get(randomNum);

        if (randProduct.isSold()) {
            return false;
        }

        double lastBid = randProduct.getCurrentBid();

        int maxBid = 100;

        if (lastBid >= maxBid){
            return false;
        }

        double rndBid = ThreadLocalRandom.current().nextDouble(lastBid, maxBid);
        DecimalFormat df = new DecimalFormat("#.##");
        rndBid = Double.parseDouble(df.format(rndBid));

        if (!(rndBid > randProduct.getCurrentBid())){
            return false;
        }
        System.out.println("NEW BID : " + rndBid);
        randProduct.setNewBid(rndBid);

        System.out.println("Bid rand Ended!");

        return true;
    }

}
