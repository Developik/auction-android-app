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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

            TextView userTextView = findViewById(R.id.selected_username);
            if (user != null) {
                userTextView.setText(user);
            } else {
                userTextView.setText("");
            }
        }


        accessItems = new AccessItems();

        itemList = new ArrayList<Item>();
        String result = accessItems.getItems(itemList);
        if (result != null)
        {
        	Messages.fatalError(this, result);
        }
        else
        {
            itemArrayAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, itemList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text2 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text1 = (TextView) view.findViewById(android.R.id.text2);
                    //TextView text3 = (TextView) view.findViewById(android.R.id.text3);

                    text2.setText("Category: " + itemList.get(position).getCategory());
                    text1.setText("Title: " + itemList.get(position).getName());
                    //text3.setText("Date Posted: " + itemList.get(position).getDatePosted());

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.listItems);

            // go to Item Activity
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object listItem = listView.getItemAtPosition(position);
                    Intent feedIntent = new Intent(FeedActivity.this, ItemActivity.class);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_students, menu);

        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_students, menu);

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

    private HashMap<String, String> getFilters(){
        HashMap<String, String> map = new HashMap<String, String>();
        String query = ((SearchView) findViewById(R.id.search_query)).getQuery().toString();
        String minBid = ((TextView) findViewById(R.id.min_bid)).getText().toString();
        String maxBid = ((TextView) findViewById(R.id.max_bid)).getText().toString();
        map.put("query", query);
        map.put("minMaxBid", minBid + ";" + maxBid);
        return map;
    }

    private void update_feed(Map<String, String> filters){
        accessProducts = new AccessProducts();

        productList = new ArrayList<Product>();
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

                    TextView text1 = (TextView) view.findViewById(R.id.text1);
                    TextView text2 = (TextView) view.findViewById(R.id.text2);
                    TextView text3 = (TextView) view.findViewById(R.id.text3);

                    text1.setText("Category: " + productList.get(position).getCategory());
                    text2.setText("Title: " + productList.get(position).getName());
                    String curr_bid_str = ""+productList.get(position).getCurrentBid();
                    text3.setText("Last Bid: " + curr_bid_str);

                    return view;
                }
            };

            final ListView listView = (ListView) findViewById(R.id.listItems);

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
