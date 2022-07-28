package comp3350.bms.presentation;

// Purpose: ProductViewActivity is a presentation object that handles the product page directly.

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.bms.R;
import comp3350.bms.business.AccessProducts;
import comp3350.bms.business.AccessUsers;
import comp3350.bms.business.AccessWallet;
import comp3350.bms.business.AuctionManager;
import comp3350.bms.business.PingChat;
import comp3350.bms.objects.Bid;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;
import comp3350.bms.objects.Wallet;

public class ProductViewActivity extends Activity {

    private PingChat pingChat;
    private TextView chatLog1;
    private TextView chatLog2;
    private TextView chatLog3;
    private TextView chatLog4;
    private TextView bidHistory1;
    private TextView bidHistory2;
    private TextView bidHistory3;
    private TextView highestBid;

    private EditText chatInput;
    private User user = null;
    private Product product = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_view_page);

        bidHistory1 = findViewById(R.id.bidHistory1);
        bidHistory2 = findViewById(R.id.bidHistory2);
        bidHistory3 = findViewById(R.id.bidHistory3);
        highestBid = findViewById(R.id.highestBid);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String user = bundle.getString("user");
            AccessUsers accessUsers = new AccessUsers();
            List<User> users = new ArrayList<User>();
            accessUsers.getUsers(users);
            for (User u : users) {
                if (u.getUsername().equals(user)) {
                    this.user = u;
                }
            }

            Long itemID = bundle.getLong("itemID");
            AccessProducts accessProducts = new AccessProducts();
            List<Product> products = new ArrayList<Product>();
            accessProducts.getProducts(products);
            for (Product p : products) {
                System.out.println("---" + p.getItemID().toString() +
                        "---" + itemID.toString());
                if (p.getItemID().equals(itemID)) {
                    this.product = p;
                    System.out.println("### CONFIRMED");
                }
            }

        }

        //AccessProducts ap = new AccessProducts();
        //ArrayList<Product> prodList = ap.getAllProducts();
        //Product p = prodList.get(0);
        String name = product.getName();
        double currBid = product.getCurrentBid();
        TextView tv = findViewById(R.id.productTitle);
        tv.setText(name);
        tv = findViewById(R.id.productDescription);
        String desc = "this is a test description. \nthis is a test description. \nthis is a test description.";
        tv.setText(desc);
        tv.findViewById(R.id.productPrice);
        String currBidStr = "Current Bid: " + currBid;
        tv.setText(currBidStr);

        if (this.user == null) {
            System.out.println("User is null! Shouldn't happen");
        }

        pingChat = new PingChat();

        String result = pingChat.getMessages();
        if (result != null) {
            Messages.fatalError(this, result);
        }

        chatLog1 = findViewById(R.id.ChatLog1);
        chatLog2 = findViewById(R.id.ChatLog2);
        chatLog3 = findViewById(R.id.ChatLog3);
        chatLog4 = findViewById(R.id.ChatLog4);
        chatInput = findViewById(R.id.chatInput);

        updateAllBidHistory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void buttonProductReviewsOnClick(View v) {
        Intent productReviewIntent = new Intent(ProductViewActivity.this, ProductReviewActivity.class);
        ProductViewActivity.this.startActivity(productReviewIntent);
    }

    public void buttonSellerProfileOnClick(View view) {
        Intent sellerProfileIntent = new Intent(ProductViewActivity.this, ProfileViewActivity.class);
        ProductViewActivity.this.startActivity(sellerProfileIntent);
    }

    public void buttonOnBidClick(View view) {
        TextView withDrawTextBox = findViewById(R.id.withDrawAmount);
        String withDrawAmount = withDrawTextBox.getText().toString();

        if (withDrawAmount.isEmpty()) {
            Messages.warning(this, "Please enter an amount");
        } else if (Double.parseDouble(withDrawAmount) <= 0) {
            Messages.warning(this, "Please enter a positive amount");
        } else {
            AccessWallet accessWallet = new AccessWallet();
            Wallet wallet = accessWallet.getWalletFromUser(this.user.getUsername());

            double amount = Double.parseDouble(withDrawAmount);
            amount = Math.round(amount * 100.0) / 100.0;

            if (wallet.getBalance() < amount) {
                Messages.warning(this, "Can not withdraw that much!");
            } else {
                wallet.withdraw(amount);
                // save withdraw
                accessWallet.updateWallet(wallet);

                AuctionManager auctionManager = new AuctionManager();
                auctionManager.addBid(amount, product, user);

                withDrawTextBox.setText(String.valueOf(0));

                updateAllBidHistory();

                Messages.warning(this, "Transaction was a success!");

            }
        }
    }

    public void updateChat(View view) {
        if (chatInput.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
        } else {
            user.createMessage(pingChat.getRandom(), -1);
            updateAllChat();
            user.createMessage(chatInput.getText().toString(), 0);
            updateAllChat(); //updates all chat boxes
            chatInput.getText().clear();
        }
    }

    public void updateAllChat() {
        int numberOfChats = user.getNumberOfChats();
        if (numberOfChats >= 0) {
            chatLog1.setText(user.getChatMessage(0));
        }
        if (numberOfChats >= 1) {
            chatLog2.setText(user.getChatMessage(1));
        }
        if (numberOfChats >= 2) {
            chatLog3.setText(user.getChatMessage(2));
        }
        if (numberOfChats >= 3) {
            chatLog4.setText(user.getChatMessage(3));
        }
    }


    public void updateAllBidHistory() {
        AuctionManager auctionManager = new AuctionManager();
        ArrayList<Bid> bids = new ArrayList<Bid>();
        auctionManager.getAllBidsForProduct(bids, product);

        double value = 0;
        User currUser;
        Bid currBid;

        if (bids.size() >= 3) {
            currBid = bids.get(bids.size()-3);
            value = currBid.getValue();
            currUser = auctionManager.getOwnerOfBid(currBid);
            bidHistory1.setText(String.format("Username: %s | Amount: %s",
                    currUser.getUsername(), value));
        }
        if (bids.size() >= 2) {
            currBid = bids.get(bids.size()-2);
            value = currBid.getValue();
            currUser = auctionManager.getOwnerOfBid(currBid);
            bidHistory2.setText(String.format("Username: %s | Amount: %s",
                    currUser.getUsername(), value));
        }
        if (bids.size() >= 1) {
            currBid = bids.get(bids.size()-1);
            value = currBid.getValue();
            currUser = auctionManager.getOwnerOfBid(currBid);
            bidHistory3.setText(String.format("Username: %s | Amount: %s",
                    currUser.getUsername(), value));

            Bid bid = auctionManager.getHighestBid(product);
            currUser = auctionManager.getOwnerOfBid(bid);
            highestBid.setText(String.format("Highest Bid: " +
                            "Username: %s | Amount: %s",
                    currUser.getUsername(), bid.getValue()));
        }

    }
}