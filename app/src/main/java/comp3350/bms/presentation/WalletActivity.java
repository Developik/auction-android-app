package comp3350.bms.presentation;

// Purpose: WalletActivity handles the wallet page for the user, which allows them to top up and
// view past transactions.

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.bms.R;
import comp3350.bms.business.AccessPaymentcards;
import comp3350.bms.business.AccessUsers;
import comp3350.bms.business.AccessWallet;
import comp3350.bms.objects.Paymentcard;
import comp3350.bms.objects.User;
import comp3350.bms.objects.Wallet;


public class WalletActivity extends AppCompatActivity {

    public TextView walletBalance;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_wallet);
        walletBalance = findViewById(R.id.balance);
        if (bundle != null) {
            String user = bundle.getString("user");
            AccessUsers accessUsers = new AccessUsers();
            List<User> users = new ArrayList<User>();
            accessUsers.getUsers(users);
            for (User u : users) {
                if (u.getUsername().equals(user)) {
                    this.user = u;
                }
            }
        }
        if (this.user == null) {
            System.out.println("User is null! Shouldn't happen");
        }
        else{
            AccessWallet accessWallet = new AccessWallet();
            System.out.println("------"+ this.user.getUsername());
            Wallet wallet = accessWallet.getWalletFromUser(this.user.getUsername());
            System.out.println("------"+ wallet.getBalance());
            walletBalance.setText(String.valueOf(wallet.getBalance()));
        }
    }

    public void buttonTopupOnClick(View v) {
        // 1. Ask for the credit card number
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_dialog_topup, null);

        builder.setView(dialogView);
        Spinner spinner = dialogView.findViewById(R.id.card_spinner);

        AccessWallet accessWallet = new AccessWallet();
        Wallet wallet = accessWallet.getWalletFromUser(this.user.getUsername());

        ArrayList<Paymentcard> paymentcards = new ArrayList<>();
        AccessPaymentcards accessPaymentcards = new AccessPaymentcards();
        accessPaymentcards.getPaymentCards(paymentcards, wallet);

        ArrayList<String> stringPaymentcards = new ArrayList<>();
        for (int i=0; i < paymentcards.size(); i++){
            stringPaymentcards.add(paymentcards.get(i).getCardNumbers());
        }
        //Paymentcard paymentcard = accessWallet.get(this.user.getUsername());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stringPaymentcards);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        builder.setPositiveButton("Top Up", (dialog, which) -> {
            EditText topupAmount = dialogView.findViewById(R.id.topUpAmount);
            String amount = topupAmount.getText().toString();
            if (amount.isEmpty()) {
                Toast.makeText(this, "Please enter an amount", Toast.LENGTH_LONG).show();
            } else if (Double.parseDouble(amount) <= 0) {
                Toast.makeText(this, "Please enter a positive amount", Toast.LENGTH_LONG).show();
            } else {
                double topUp = Double.parseDouble(amount);
                topUp = Math.round(topUp * 100.0) / 100.0;
                wallet.topUp(topUp);
                // save topping up
                accessWallet.updateWallet(wallet);

                walletBalance.setText(String.valueOf(wallet.getBalance()));
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
        });

        builder.show();

    }
}