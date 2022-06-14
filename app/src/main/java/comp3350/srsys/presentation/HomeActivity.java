package comp3350.srsys.presentation;

import comp3350.srsys.R;
import comp3350.srsys.application.Main;
import comp3350.srsys.business.ItemLogic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Main.startUp();

        // generate bids
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //your method
                ItemLogic.assignBidToRandomItem();
                System.out.println("Random bid was done!");
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

    /*
    public void buttonCoursesOnClick(View v) {
        Intent coursesIntent = new Intent(HomeActivity.this, CoursesActivity.class);
        HomeActivity.this.startActivity(coursesIntent);
    }
    */

}
