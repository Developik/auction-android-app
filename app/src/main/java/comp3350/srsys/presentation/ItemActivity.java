package comp3350.srsys.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.srsys.R;
import comp3350.srsys.business.PingChat;
import comp3350.srsys.objects.ChatMessages;

public class ItemActivity extends Activity{

    private PingChat pingChat;
    private ArrayList<ChatMessages> chatList;
    private TextView chatLog1;
    private TextView chatLog2;
    private TextView chatLog3;
    private TextView chatLog4;
    private EditText chatInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Main.startUp();

        setContentView(R.layout.activity_item);

        pingChat = new PingChat();

        chatList = new ArrayList<>();
        String result = pingChat.getMessages();
        if (result != null)
        {
            Messages.fatalError(this, result);
        }

        chatList = new ArrayList<>();
        chatLog1 = findViewById(R.id.ChatLog1);
        chatLog2 = findViewById(R.id.ChatLog2);
        chatLog3 = findViewById(R.id.ChatLog3);
        chatLog4 = findViewById(R.id.ChatLog4);
        chatInput = findViewById(R.id.chatInput);
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

    public void updateChat(View view) {
        final int MAX_NEW_CHATS = 3;
        int numberOfChats = (int) (Math.random() * MAX_NEW_CHATS);
        for (int i = 0; i < numberOfChats; i++) {
            updateAllChat(pingChat.getRandom());
        }
        updateAllChat("User: " + chatInput.getText());
    }

    public void updateAllChat(String newMessage){
        chatLog4.setText(chatLog3.getText());
        chatLog3.setText(chatLog2.getText());
        chatLog2.setText(chatLog1.getText());
        chatLog1.setText(newMessage);
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
