package comp3350.srsys.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import comp3350.srsys.R;
import comp3350.srsys.application.Main;
import comp3350.srsys.business.AccessItems;
import comp3350.srsys.objects.Item;

public class ItemActivity extends Activity {

    private AccessItems accessItems;
    private ArrayList<Item> itemList;
    private ArrayAdapter<Item> itemArrayAdapter;
    private int selectedItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Main.startUp();

        setContentView(R.layout.activity_item);
    }
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

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

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(itemList.get(position).getItemID() + ": " + itemList.get(position).getItemName());
                    text2.setText(itemList.get(position).getItemAddress());

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.listItems);
            listView.setAdapter(itemArrayAdapter);

        }
    }
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_students, menu);
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
