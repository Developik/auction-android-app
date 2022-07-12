package comp3350.srsys.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import comp3350.srsys.R;
import comp3350.srsys.application.Main;
import comp3350.srsys.business.AccessUsers;
import comp3350.srsys.business.WalletLogic;
import comp3350.srsys.objects.User;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        copyDatabaseToDevice();

        try {
            Main.startUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // generate bids

        setContentView(R.layout.activity_onboarding);

        // Update the spinner with the list of users from DataAccessStub
        Spinner spinner = findViewById(R.id.userSpinner);
        try {
            updateSpinnerWithData(spinner);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void updateSpinnerWithData(Spinner spinner) throws Exception {
        List<User> users = new ArrayList<>();
        AccessUsers accessUsers = new AccessUsers();
        String result = accessUsers.getUsers(users);

        ArrayList<String> names = new ArrayList<>();

        for (User user : users) {
            String name = user.getUsername();
            if (!name.contains("bot"))
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

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        } catch (IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}