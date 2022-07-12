package comp3350.bms.presentation;

// Purpose: ProfileViewActivity handles the profile page for the products.
// Currently does not work.

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import comp3350.bms.R;

public class ProfileViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        TextView tv = findViewById(R.id.username);
        tv.setText(R.string.username);
    }
}