package comp3350.srsys.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import comp3350.srsys.R;
import comp3350.srsys.business.AccessItems;
import comp3350.srsys.objects.Item;
import comp3350.srsys.objects.Student;

public class FeedActivity extends Activity {

    private AccessItems accessItems;
    private ArrayList<Item> itemList;
    private ArrayAdapter<Item> itemArrayAdapter;
    private int selectedItemPosition = -1;


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
