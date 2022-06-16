package comp3350.srsys.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.srsys.R;
import comp3350.srsys.business.AccessProducts;
import comp3350.srsys.objects.Product;

public class ProductReviewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_review_page);
        AccessProducts productListAccess = new AccessProducts();
        ArrayList<Product> productList = productListAccess.getAllProducts();
        Product thisProduct = productList.get(0);
        String thisProductName = thisProduct.getName();
        double currBid = p.getCurrentBid();
        TextView thisTextView = findViewById(R.id.productTitle);
        tv.setText(thisProductName);
    }

    public void submitReviewOnClick(View view) {
        // TODO: nothing happens...
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductReviewActivity.this);
        builder.setMessage("Review Submitted!");
        AlertDialog dialog = builder.create();
    }
}
