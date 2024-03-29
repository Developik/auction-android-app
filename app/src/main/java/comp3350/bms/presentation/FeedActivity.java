package comp3350.bms.presentation;

// Purpose: Presentation object that handles the Feed (page after login/onboarding) activity

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comp3350.bms.R;
import comp3350.bms.business.AccessProducts;
import comp3350.bms.business.AccessUsers;
import comp3350.bms.business.ProductLogic;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;

public class FeedActivity extends Activity {

    private AccessProducts accessProducts;
    private ArrayList<Product> productList;
    private ArrayAdapter<Product> itemArrayAdapter;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_feed);
        if (bundle != null) {
            String user = bundle.getString("user");
            AccessUsers accessUsers = new AccessUsers();
            List<User> users = new ArrayList<>();
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


        AccessProducts accessItems = new AccessProducts();

        ArrayList<Product> itemList = new ArrayList<>();
        String result = accessItems.getProducts(itemList);
        if (result != null) {
            Messages.fatalError(this, result);
        } else {
            itemArrayAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_activated_2, itemList) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text2 = view.findViewById(android.R.id.text1);
                    TextView text1 = view.findViewById(android.R.id.text2);

                    text2.setText("Category: " + productList.get(position).getCategory());
                    text1.setText("Title: " + productList.get(position).getName());

                    return view;
                }
            };

            final ListView listView = findViewById(R.id.listItems);

            // go to Item Activity
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Intent productViewIntent = new Intent(FeedActivity.this, ProductViewActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("user", user.getUsername());
                productViewIntent.putExtras(bundle1);
                FeedActivity.this.startActivity(productViewIntent);
            });
            listView.setAdapter(itemArrayAdapter);

        }
        HashMap<String, String> filters = getFilters();
        update_feed(filters);
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
        return super.onOptionsItemSelected(item);
    }

    public void searchFeedOnClick(View v) {
        HashMap<String, String> filters = getFilters();
        update_feed(filters);
    }

    public void buttonWalletOnClick(View v) {
        Intent walletIntent = new Intent(FeedActivity.this, WalletActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("user", this.user.getUsername());
        walletIntent.putExtras(bundle);
        FeedActivity.this.startActivity(walletIntent);
    }

    private HashMap<String, String> getFilters() {
        HashMap<String, String> map = new HashMap<>();
        String query = ((TextView) findViewById(R.id.search_query)).getText().toString();
        map.put("query", query);
        return map;
    }

    private void update_feed(Map<String, String> filters) {
        accessProducts = new AccessProducts();

        productList = new ArrayList<>();
        String result = accessProducts.getProducts(productList);
        productList = ProductLogic.filterFeed(filters, productList);

        if (result != null) {
            Messages.fatalError(this, result);
        } else {
            itemArrayAdapter = new ArrayAdapter<Product>(this, R.layout.feed_list_item, R.id.text1, productList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = view.findViewById(R.id.text1);
                    TextView text2 = view.findViewById(R.id.text2);
                    TextView text3 = view.findViewById(R.id.text3);
                    ImageView image = view.findViewById(R.id.picture_view);

                    text1.setText("Category: " + productList.get(position).getCategory());
                    text2.setText("Title: " + productList.get(position).getName());
                    String curr_bid_str = "" + productList.get(position).getCurrentBid();
                    text3.setText("Last Bid: " + curr_bid_str);

                    if (productList.get(position).getPicture().equals("garden_bucket")) {
                        image.setImageResource(R.drawable.garden_bucket);
                    } else if (productList.get(position).getPicture().equals("rolex_watch")) {
                        image.setImageResource(R.drawable.rolex_watch);
                    } else {
                        image.setImageResource(R.drawable.mortarboard);
                    }

                    return view;
                }
            };

            final ListView listView = findViewById(R.id.listItems);

            // go to Item Activity
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Intent productViewIntent = new Intent(FeedActivity.this, ProductViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", user.getUsername());
                bundle.putLong("itemID", productList.get(position).getItemID());
                productViewIntent.putExtras(bundle);
                FeedActivity.this.startActivity(productViewIntent);
            });
            listView.setAdapter(itemArrayAdapter);
        }
    }
}
