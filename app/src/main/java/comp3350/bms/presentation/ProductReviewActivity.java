package comp3350.bms.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.bms.R;
import comp3350.bms.business.AccessProducts;
import comp3350.bms.objects.Product;

public class ProductReviewActivity extends Activity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_review_page);
        AccessProducts accessProducts = new AccessProducts();
        ArrayList<Product> prodList = accessProducts.getAllProducts();
        Product thisProduct = prodList.get(0);
        String productName = thisProduct.getName();
        double highestBid = thisProduct.getHighestBid().getBidAmount();
        TextView thisTextView = findViewById(R.id.productTitle);
        thisTextView.setText(productName);
    }

    public void submitReviewOnClick(View view) {
        // TODO: nothing happens...
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductReviewActivity.this);
        builder.setMessage("Review Submitted!");
        AlertDialog dialog = builder.create();
    }
}