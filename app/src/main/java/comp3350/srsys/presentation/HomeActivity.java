package comp3350.srsys.presentation;

import comp3350.srsys.R;
import comp3350.srsys.application.Main;
import comp3350.srsys.business.BotLogic;
import comp3350.srsys.business.ProductLogic;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Main.startUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // generate bids
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                //your method
//                BotLogic.assignBidToRandomProduct();
//                System.out.println("Random bid was done!");
            }
        }, 0, 5000);//put here time 1000 milliseconds=1 second

        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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


    public void buttonFeedOnClick(View v) {
        Intent feedIntent = new Intent(HomeActivity.this, FeedActivity.class);
        HomeActivity.this.startActivity(feedIntent);
    }

    public void buttonProductViewOnClick(View v) {
        Intent productViewIntent = new Intent(HomeActivity.this, ProductViewActivity.class);
        HomeActivity.this.startActivity(productViewIntent);
    }

    /*
    public void buttonCoursesOnClick(View v) {
        Intent coursesIntent = new Intent(HomeActivity.this, CoursesActivity.class);
        HomeActivity.this.startActivity(coursesIntent);
    }
    */

}
