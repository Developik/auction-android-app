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
        AccessProducts productAccess = new AccessProducts();
        ArrayList<Product> productList = productAccess.getAllProducts();
        Product thisProduct = productList.get(0);
        String productName = thisProduct.getName();
        double currBidPrice = thisProduct.getCurrentBid();
        TextView thisTextView = findViewById(R.id.productTitle);
        thisTextView.setText(productName);
        thisTextView = findViewById(R.id.productDescription);
        String productDescription = "this is a test description. \nthis is a test description. \nthis is a test description.";
        thisTextView.setText(productDescription);
        thisTextView.findViewById(R.id.productPrice);
        String currBidString = "Current Bid: " + currBidPrice;
        thisTextView.setText(currBidString);
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
