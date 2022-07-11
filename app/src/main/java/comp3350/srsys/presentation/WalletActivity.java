package comp3350.srsys.presentation;

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

import comp3350.srsys.R;
import comp3350.srsys.business.AccessUsers;
import comp3350.srsys.objects.User;

public class WalletActivity extends AppCompatActivity {

    public TextView walletBalance;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_wallet);
        walletBalance = findViewById(R.id.balance);
        if(bundle != null) {
            String user = bundle.getString("user");
            AccessUsers accessUsers = new AccessUsers();
            List<User> users = new ArrayList<User>();
            accessUsers.getUsers(users);
            for(User u : users) {
                if(u.getUsername().equals(user)) {
                    this.user = u;
                }
            }
        }
        if(this.user == null) {
            System.out.println("User is null! Shouldn't happen");
        }
        else{
            walletBalance.setText(String.valueOf(this.user.getWallet().getBalance()));
        }
    }

    public void buttonTopupOnClick(View v) {
        // 1. Ask for the credit card number
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_dialog_topup, null);

        builder.setView(dialogView);
        Spinner spinner = dialogView.findViewById(R.id.card_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, user.getWallet().getCards());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        builder.setPositiveButton("Top Up", (dialog, which) -> {
            EditText topupAmount = dialogView.findViewById(R.id.topUpAmount);
            String amount = topupAmount.getText().toString();
            if(amount.isEmpty()) {
                Toast.makeText(this, "Please enter an amount", Toast.LENGTH_LONG).show();
            }
            else if (Double.parseDouble(amount) <= 0) {
                Toast.makeText(this, "Please enter a positive amount", Toast.LENGTH_LONG).show();
            }
            else {
                double topUp = Double.parseDouble(amount);
                topUp = Math.round(topUp * 100.0) / 100.0;
                user.getWallet().topUp(topUp);
                walletBalance.setText(String.valueOf(user.getWallet().getBalance()));
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.cancel();
        });

        builder.show();

    }
}