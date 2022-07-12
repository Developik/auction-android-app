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

import java.util.ArrayList;
import java.util.List;

import comp3350.bms.R;
import comp3350.bms.business.AccessProducts;
import comp3350.bms.business.AccessUsers;
import comp3350.bms.business.PingChat;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;

public class ProductViewActivity extends Activity {

    private PingChat pingChat;
    private TextView chatLog1;
    private TextView chatLog2;
    private TextView chatLog3;
    private TextView chatLog4;
    private EditText chatInput;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_view_page);
        AccessProducts ap = new AccessProducts();
        ArrayList<Product> prodList = ap.getAllProducts();
        Product p = prodList.get(0);
        String name = p.getName();
        double currBid = p.getCurrentBid();
        TextView tv = findViewById(R.id.productTitle);
        tv.setText(name);
        tv = findViewById(R.id.productDescription);
        String desc = "this is a test description. \nthis is a test description. \nthis is a test description.";
        tv.setText(desc);
        tv.findViewById(R.id.productPrice);
        String currBidStr = "Current Bid: " + currBid;
        tv.setText(currBidStr);

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
        }

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

    public void updateChat(View view) {
        final int MAX_NEW_CHATS = 3;
        int numberOfChats = (int) (Math.random() * MAX_NEW_CHATS);
        for (int i = 0; i < numberOfChats; i++) {
            user.createMessage(pingChat.getRandom(), -1);
        }
        user.createMessage(chatInput.getText().toString(), 0);
        updateAllChat(); //updates all chat boxes
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
}