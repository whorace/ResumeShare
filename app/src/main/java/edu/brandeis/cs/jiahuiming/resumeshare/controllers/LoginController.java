package edu.brandeis.cs.jiahuiming.resumeshare.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import edu.brandeis.cs.jiahuiming.resumeshare.models.UserModel;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.NetworkChecker;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;

/**
 * Created by jiahuiming on 11/8/16.
 */

public class LoginController {
    private UserModel userModel;
    private Context context;

    public LoginController(Context context){
        this.context=context;
    }

    public void login(String account,String password) {
        NetworkChecker networkerchecker = new NetworkChecker(this.context);

        /**
         * verify account and password input by user
         * if account or password equal to null, show the warn that "..."
         * if there is not available network, show the warn that "..."
         */

        if (account.equals("") || password.equals("")) {
            Toast.makeText(context, "Account and Password can not be empty!", Toast.LENGTH_LONG).show();
        } else if (!networkerchecker.isNetworkConnected()) {
            Toast.makeText(context, "no avaliable network connection can not be use!", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(context, HomeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("Account", account);
            intent.putExtra("Bundle", bundle);
            context.startActivity(intent);
        }
    }
}
