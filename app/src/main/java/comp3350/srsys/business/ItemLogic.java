package comp3350.srsys.business;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import comp3350.srsys.objects.Item;
import comp3350.srsys.objects.SC;

public class ItemLogic
{
    public static Long generateID()
    {
        return new Date().getTime();
    }

    public static boolean assignBidToRandomItem() {
        System.out.println("Attempt to bid rand Started!");
        AccessItems accessItems = new AccessItems();
        ArrayList<Item> itemList = new ArrayList<Item>();
        accessItems.getItems(itemList);
        int min = 0;
        int max = itemList.size();
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);

        Item rand_item = itemList.get(randomNum);

        if (rand_item.isSold()) {
            return false;
        }

        double last_bid = rand_item.getCurrentBid();

        int max_bid = 100;

        if (last_bid >= max_bid){
            return false;
        }

        double rnd_bid = ThreadLocalRandom.current().nextDouble(last_bid, max_bid);
        DecimalFormat df = new DecimalFormat("#.##");
        rnd_bid = Double.parseDouble(df.format(rnd_bid));

        if (!(rnd_bid > rand_item.getCurrentBid())){
            return false;
        }
        System.out.println("NEW BID : " + rnd_bid);
        rand_item.setNewBid(rnd_bid);

        System.out.println("Bid rand Ended!");

        return true;
    }

}
