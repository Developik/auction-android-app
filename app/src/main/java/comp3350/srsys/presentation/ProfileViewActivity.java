package comp3350.srsys.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import comp3350.srsys.R;

public class ProfileViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        TextView tv = findViewById(R.id.username);
        tv.setText(R.string.username);
    }
}