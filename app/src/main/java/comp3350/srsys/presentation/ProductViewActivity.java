package comp3350.srsys.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.srsys.R;
import comp3350.srsys.business.AccessProducts;
import comp3350.srsys.objects.Product;

public class ProductViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_view_page);
        AccessProducts ap = new AccessProducts();
        ArrayList<Product> prodList = ap.getAllProducts();
        Product p = prodList.get(0);
        String name = p.getName();
        double currBid = p.getCurrentBid();
        TextView tv = findViewById(R.id.productTitle);
        tv.setText(name);
        tv = findViewById(R.id.productDescription);
        String desc = "this is a test description. \nthis is a test description. \nthis is a test description.";
        tv.setText(desc);
        tv.findViewById(R.id.productPrice);
        String currBidStr = "Current Bid: " + currBid;
        tv.setText(currBidStr);
    }

    public void buttonProductReviewsOnClick(View v) {
        Intent productReviewIntent = new Intent(ProductViewActivity.this, ProductReviewActivity.class);
        ProductViewActivity.this.startActivity(productReviewIntent);
    }

    public void buttonSellerProfileOnClick(View view) {
        Intent sellerProfileIntent = new Intent(ProductViewActivity.this, ProfileViewActivity.class);
        ProductViewActivity.this.startActivity(sellerProfileIntent);
    }
}