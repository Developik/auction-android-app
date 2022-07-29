package comp3350.bms.presentation;

// Purpose: Simple object that allows to alert the user with errors, this just shows alert dialogs
// to stop the program or to warn the user.

import android.app.Activity;
import android.app.AlertDialog;

import comp3350.bms.R;

public class Messages {
    public static void fatalError(final Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.fatalError));
        alertDialog.setMessage(message);
        alertDialog.setOnCancelListener(dialog -> owner.finish());

        alertDialog.show();
    }

    public static void warning(Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.warning));
        alertDialog.setMessage(message);

        alertDialog.show();
    }
}
