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
import java.util.Iterator;
import java.util.Map;

import comp3350.srsys.R;
import comp3350.srsys.business.AccessItems;
import comp3350.srsys.objects.Item;

public class FeedActivity extends Activity {

    private AccessItems accessItems;
    private ArrayList<Item> itemList;
    private ArrayAdapter<Item> itemArrayAdapter;
    private int selectedItemPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Map filters = get_filters();
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
        Map filters = get_filters();
        update_feed(filters);
    }

    private Map get_filters(){
        Map<String, String> map = new HashMap<String, String>();
        String query = ((SearchView) findViewById(R.id.search_query)).getQuery().toString();
        String min_bid = ((TextView) findViewById(R.id.min_bid)).getText().toString();
        String max_bid = ((TextView) findViewById(R.id.max_bid)).getText().toString();
        System.out.println("Query:" + query);
        System.out.println("min_bid:" + min_bid);
        System.out.println("max_bid:" + max_bid);

        map.put("query", query);
        map.put("min_max_bid", min_bid + ";" + max_bid);
        return map;
    }

    private ArrayList<Item> filter_feed(Map<String, String> filters, ArrayList<Item> itemList){
        ArrayList<Item> new_itemList = new ArrayList<Item>(itemList);
        Iterator it = filters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String key = (String) pair.getKey();
            String value = (String) pair.getValue();
            //System.out.println(key + " = " + value);
            if (value != null && !(value.trim().equals("")))
                new_itemList = filter_feed_by(key, value, new_itemList);
                System.out.println("ItemList0:" + itemList.toString());
            it.remove(); // avoids a ConcurrentModificationException
        }
        System.out.println("ItemList1:" + itemList.toString());
        return new_itemList;
    }

    private ArrayList<Item> filter_feed_by(String key, String value, ArrayList<Item> itemList){
        ArrayList<Item> filteredList = new ArrayList<Item>();

        switch(key) {
            case "query":
                filteredList = filter_by_query(itemList, value);
                break;
            case "min_max_bid":
                filteredList = filter_by_bid(itemList, value);
                break;
        }
        return filteredList;
    }

    private ArrayList<Item> filter_by_query(ArrayList<Item> itemList, String value) {
        ArrayList<Item> filteredList = new ArrayList<Item>();
        for (Item item : itemList) {
            if ((item.getName().contains(value))) {
                System.out.println("1:" + item.getName());
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    private ArrayList<Item>  filter_by_bid(ArrayList<Item> itemList, String value){
        ArrayList<Item> filteredList = new ArrayList<Item>();
        System.out.println("Value:" + value);
        String [] input = value.split(";");
        // return empty list if number of parameters doesn't equal 2
        // if (input.length != 2)
        //    return filteredList;
        String min_val;
        String max_val;
        double min_val_num = 0;
        double max_val_num = (double) Integer.MAX_VALUE;
        try {
            min_val = input[0];
            min_val_num = Double.parseDouble(min_val);
        } catch(Exception e){
            //return filteredList;
            System.out.println("min_val_num NOT PRESENT");
        }

        try {
            max_val = input[1];
            max_val_num = Double.parseDouble(max_val);
        } catch(Exception e){
            //return filteredList;
            System.out.println("max_val_num NOT PRESENT");
        }

        for(Item item : itemList) {
            if ((max_val_num >= item.getCurrentBid()) && (item.getCurrentBid() >= min_val_num)) {
                System.out.println("2:" + item.getCurrentBid());
                System.out.println("3:" + min_val_num + " " + max_val_num);
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    private void update_feed(Map<String, String> filters){
        accessItems = new AccessItems();

        itemList = new ArrayList<Item>();
        String result = accessItems.getItems(itemList);
        System.out.println("ItemList3:" + itemList.toString());
        itemList = filter_feed(filters, itemList);
        System.out.println("ItemList4:" + itemList.toString());

        if (result != null)
        {
            Messages.fatalError(this, result);
        }
        else {
            itemArrayAdapter = new ArrayAdapter<Item>(this, R.layout.feed_list_item, android.R.id.text1, itemList) {
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

            final ListView listView = (ListView) findViewById(R.id.listItems);

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
    }
    /*
    private String validateStudentData(Student student, boolean isNewStudent) {
        if (student.getStudentID().length() == 0) {
            return "Student ID required";
        }

        if (student.getStudentName().length() == 0) {
            return "Student name required";
        }

        if (isNewStudent && accessStudents.getRandom(student.getStudentID()) != null) {
            return "Student ID " + student.getStudentID() + " already exists.";
        }

        return null;
    }

     */
}
