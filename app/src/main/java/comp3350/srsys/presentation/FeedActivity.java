package comp3350.srsys.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import comp3350.srsys.R;
import comp3350.srsys.business.AccessProducts;
import comp3350.srsys.business.ProductLogic;
import comp3350.srsys.objects.Product;

public class FeedActivity extends Activity {

    private AccessProducts accessProducts;
    private ArrayList<Product> productList;
    private ArrayAdapter<Product> itemArrayAdapter;
    private int selectedProductPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_feed);
        if(bundle != null) {
            String user = bundle.getString("user");
        }


        AccessProducts accessItems = new AccessProducts();

        ArrayList<Product> itemList = new ArrayList<>();
        String result = accessItems.getProducts(itemList);
        if (result != null)
        {
        	Messages.fatalError(this, result);
        }
        else
        {
            itemArrayAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, itemList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text2 = view.findViewById(android.R.id.text1);
                    TextView text1 = view.findViewById(android.R.id.text2);
                    //TextView text3 = (TextView) view.findViewById(android.R.id.text3);

                    text2.setText("Category: " + productList.get(position).getCategory());
                    text1.setText("Title: " + productList.get(position).getName());
                    //text3.setText("Date Posted: " + itemList.get(position).getDatePosted());

                    return view;
                }
            };

            final ListView listView = findViewById(R.id.listItems);

            // go to Item Activity
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object listItem = listView.getItemAtPosition(position);
                    Intent feedIntent = new Intent(FeedActivity.this, ProductActivity.class);
                    FeedActivity.this.startActivity(feedIntent);
                }
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
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void searchFeedOnClick(View v) {
        HashMap<String, String> filters = getFilters();
        update_feed(filters);
    }

    public void buttonWalletOnClick(View v) {
        Intent walletIntent = new Intent(FeedActivity.this, WalletActivity.class);
        FeedActivity.this.startActivity(walletIntent);
    }

    private HashMap<String, String> getFilters(){
        HashMap<String, String> map = new HashMap<>();
        String query = ((SearchView) findViewById(R.id.search_query)).getQuery().toString();
        String minBid = ((TextView) findViewById(R.id.min_bid)).getText().toString();
        String maxBid = ((TextView) findViewById(R.id.max_bid)).getText().toString();
        map.put("query", query);
        map.put("minMaxBid", minBid + ";" + maxBid);
        return map;
    }

    private void update_feed(Map<String, String> filters){
        accessProducts = new AccessProducts();

        productList = new ArrayList<>();
        String result = accessProducts.getProducts(productList);
        productList = ProductLogic.filterFeed(filters, productList);

        if (result != null)
        {
            Messages.fatalError(this, result);
        }
        else {
            itemArrayAdapter = new ArrayAdapter<Product>(this, R.layout.feed_list_item, R.id.text1, productList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = view.findViewById(R.id.text1);
                    TextView text2 = view.findViewById(R.id.text2);
                    TextView text3 = view.findViewById(R.id.text3);

                    text1.setText("Category: " + productList.get(position).getCategory());
                    text2.setText("Title: " + productList.get(position).getName());
                    String curr_bid_str = ""+productList.get(position).getCurrentBid();
                    text3.setText("Last Bid: " + curr_bid_str);

                    return view;
                }
            };

            final ListView listView = findViewById(R.id.listItems);

            // go to Item Activity
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object listItem = listView.getItemAtPosition(position);
                    Intent feedIntent = new Intent(FeedActivity.this, ProductActivity.class);
                    FeedActivity.this.startActivity(feedIntent);
                }
            });
            listView.setAdapter(itemArrayAdapter);
        }
    }
}
