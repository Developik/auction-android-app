package comp3350.srsys.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.srsys.R;
import comp3350.srsys.business.AccessProducts;
import comp3350.srsys.objects.Product;

public class ProfileViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        TextView tv = findViewById(R.id.username);
        tv.setText(R.string.username);
    }
}