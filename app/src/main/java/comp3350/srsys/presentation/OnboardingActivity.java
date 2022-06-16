package comp3350.srsys.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import comp3350.srsys.R;
import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.business.BotLogic;
import comp3350.srsys.objects.User;
import comp3350.srsys.persistence.DataAccessStub;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Main.startUp();

        // generate bids
        final BotLogic newBot = new BotLogic();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                newBot.assignBidToRandomProduct();
            }
        }, 0, 5000);//put here time 1000 milliseconds=1 second

        setContentView(R.layout.activity_onboarding);

        // Update the spinner with the list of users from DataAccessStub
        Spinner spinner = findViewById(R.id.userSpinner);
        updateSpinnerWithData(spinner);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }

    public void buttonFeedOnClick(View v) {
        Intent feedIntent = new Intent(OnboardingActivity.this, FeedActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("user", ((Spinner) findViewById(R.id.userSpinner)).getSelectedItem().toString());
        feedIntent.putExtras(bundle);
        OnboardingActivity.this.startActivity(feedIntent);
    }

    private void updateSpinnerWithData(Spinner spinner) {
        List<User> users = new ArrayList<>();
        DataAccessStub dataAccess = Services.getDataAccess(Main.dbName);

        dataAccess.open(Main.dbName);

        dataAccess.getUserSequential(users);

        dataAccess.close();

        ArrayList<String> names = new ArrayList<>();

        for (User user : users) {
            String name = user.getUsername();
            if (!name.contains("bot") && !user.checkIsBot())
                names.add(user.getUsername());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

    }

    public void buttonProductViewOnClick(View v) {
        Intent productViewIntent = new Intent(OnboardingActivity.this, ProductViewActivity.class);
        OnboardingActivity.this.startActivity(productViewIntent);
    }
}