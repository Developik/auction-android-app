package comp3350.srsys.presentation;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import comp3350.srsys.R;

public class WalletActivity extends AppCompatActivity {

    public TextView walletBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        walletBalance = findViewById(R.id.balance);
    }

    public void buttonTopupOnClick(View v) {
        // 1. Ask for the credit card number
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Top Up");
        builder.setMessage("Please fill in your credit card number");
        final EditText cardNumber = new EditText(this);
        cardNumber.setHint("Card Number");
        cardNumber.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        builder.setView(cardNumber);

        builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                String cardNumberString = cardNumber.getText().toString();
                if (cardNumberString.length() == 16) {
                    // 2. Ask for the credit card expiry date
                    System.out.println("Valid credit card number");
                    builder.setMessage("Please fill in your credit card expiry date");
                    final EditText expiryDate = new EditText(WalletActivity.this);
                    expiryDate.setHint("MM/YY");
                    builder.setView(expiryDate);

                    builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(android.content.DialogInterface dialog, int which) {
                            String expiryDateString = expiryDate.getText().toString();
                            if (expiryDateString.length() == 5) {
                                // 3. Ask for the credit card CVV
                                System.out.println("Valid credit card expiry date");
                                builder.setMessage("Please fill in how much you want to top up");
                                final EditText topupAmount = new EditText(WalletActivity.this);
                                topupAmount.setHint("Amount");
                                topupAmount.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
                                builder.setView(topupAmount);

                                builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(android.content.DialogInterface dialog, int which) {
                                        // 4. Top up the user's wallet
                                        String topupAmountString = topupAmount.getText().toString();
                                        if (topupAmountString.length() > 0) {
                                            System.out.println("Valid top up amount");

                                            // Top up balance in the balance text view
                                            walletBalance.setText(String.valueOf(Double.parseDouble(walletBalance.getText().toString()) + Double.parseDouble(topupAmountString)));

                                            Toast.makeText(WalletActivity.this, "Top up successful", Toast.LENGTH_SHORT).show();
                                        } else {
                                            System.out.println("Invalid top up amount");
                                            Toast.makeText(WalletActivity.this, "Please enter a valid top up amount", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                builder.setNegativeButton("Cancel", null);
                                builder.show();

                            } else {
                                System.out.println("Invalid credit card expiry date");
                                Toast.makeText(WalletActivity.this, "Invalid credit card expiry date", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    builder.setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(android.content.DialogInterface dialog, int which) {
                            System.out.println("Cancelled top up");
                            Toast.makeText(WalletActivity.this, "Cancelled top up", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                } else {
                    System.out.println("Invalid credit card number");
                    // Show toast message
                    Toast.makeText(WalletActivity.this, "Invalid credit card number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();

    }
}