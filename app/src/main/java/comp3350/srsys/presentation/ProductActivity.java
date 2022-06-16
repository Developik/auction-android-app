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

public class ProductActivity extends Activity{

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

}
