package comp3350.srsys.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import comp3350.srsys.R;
import comp3350.srsys.business.PingChat;
import comp3350.srsys.objects.User;

public class ProductActivity extends Activity {

    private PingChat pingChat;
    private TextView chatLog1;
    private TextView chatLog2;
    private TextView chatLog3;
    private TextView chatLog4;
    private EditText chatInput;
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currUser = this.getIntent().getExtras().getParcelable("user");

        //Main.startUp();

        setContentView(R.layout.activity_item);

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

/*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("currUser", currUser);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currUser.setChatHistory(savedInstanceState.getParcelable("currUser"));
    }*/

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
        if (currUser != null) {
            final int MAX_NEW_CHATS = 3;
            int numberOfChats = (int) (Math.random() * MAX_NEW_CHATS);
            for (int i = 0; i < numberOfChats; i++) {
                currUser.createMessage(pingChat.getRandom(), -1);
            }
            currUser.createMessage(chatInput.getText().toString(), 0);
            updateAllChat(); //updates all chat boxes
        } else {
            System.out.println("CurrUser is NULL...");
        }
    }

    public void updateAllChat() {
        int numberOfChats = currUser.getNumberOfChats();
        if (numberOfChats >= 0) {
            chatLog1.setText(currUser.getChatMessage(0));
        }
        if (numberOfChats >= 1) {
            chatLog2.setText(currUser.getChatMessage(1));
        }
        if (numberOfChats >= 2) {
            chatLog3.setText(currUser.getChatMessage(2));
        }
        if (numberOfChats >= 3) {
            chatLog4.setText(currUser.getChatMessage(3));
        }
    }

}
