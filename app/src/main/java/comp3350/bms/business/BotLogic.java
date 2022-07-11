package comp3350.bms.business;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.objects.Bid;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;
import comp3350.bms.persistence.DataAccess;

public class BotLogic
{
    // Purpose: Generate bids on Products

    private User selectedUser;

    public BotLogic() throws Exception {
        List<User> users = new ArrayList<>();
        DataAccess dataAccess = Services.getDataAccess(Main.dbName);
        dataAccess.open(Main.dbName);
        dataAccess.getUserSequential(users);
        dataAccess.close();

        Random rand = new Random();
        boolean found = false;
        int index = 0;
        User user;
        while (!found){
            if (index >= users.size())
                index = 0;
            user = users.get(index);
            String name = user.getUsername();
            if (name.contains("bot") && rand.nextInt(3) == 0){
                this.selectedUser = user;
                found = true;
            }
            index++;
        }
    }

    public Long generateID() { return new Date().getTime(); }

    public User getSelectedUser() { return selectedUser; }

    public boolean assignBidToRandomProduct() {
        System.out.println("Attempt to bid rand Started!");
        AccessProducts accessProducts = new AccessProducts();
        ArrayList<Product> ProductList = new ArrayList<>();
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

        Bid newBid = new Bid(rndBid, selectedUser);
        selectedUser.setBid(newBid);

        System.out.println("Bid rand Ended!");

        return true;
    }

}
