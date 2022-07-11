package comp3350.srsys.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.srsys.R;
import comp3350.srsys.business.AccessProducts;
import comp3350.srsys.objects.Product;

public class ProductReviewActivity extends Activity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_review_page);
        AccessProducts ap = new AccessProducts();
        ArrayList<Product> prodList = ap.getAllProducts();
        Product p = prodList.get(0);
        String name = p.getName();
        double highestBid = p.getHighestBid().getBidAmount();
        TextView tv = findViewById(R.id.productTitle);
        tv.setText(name);
    }

    public void submitReviewOnClick(View view) {
        // TODO: nothing happens...
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductReviewActivity.this);
        builder.setMessage("Review Submitted!");
        AlertDialog dialog = builder.create();
    }
}
