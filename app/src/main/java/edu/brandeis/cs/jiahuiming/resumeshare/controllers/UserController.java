package edu.brandeis.cs.jiahuiming.resumeshare.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import java.util.regex.Pattern;

import edu.brandeis.cs.jiahuiming.resumeshare.beans.Experience;
import edu.brandeis.cs.jiahuiming.resumeshare.models.EducationModel;
import edu.brandeis.cs.jiahuiming.resumeshare.models.SkillModel;
import edu.brandeis.cs.jiahuiming.resumeshare.models.UserModel;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.NetworkChecker;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;

/**
 * Created by jiahuiming on 11/13/16.
 */

public class UserController {

    private UserModel userModel;
    private Context context;
    private EducationModel educationModel;
    private SkillModel skillModel;
    private Experience experienceModel;

    public UserController(Context context){
        this.context=context;
    }

    public void login(String account,String password) {
        NetworkChecker networkerchecker = new NetworkChecker(this.context);

        if (account.equals("") || password.equals("")) {
            Toast.makeText(context, "Account and Password can not be empty!", Toast.LENGTH_LONG).show();
        } else if (!networkerchecker.isNetworkConnected()) {
            Toast.makeText(context, "no avaliable network connection can not be use!", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(context, HomeActivity.class);
            Bundle bundle = new Bundle();
            userModel=new UserModel(context);
            userModel.loginfromRemote(account,password);
//            if(password.equals(user.getPassword()))
//            {
//                bundle.putString("Account", account);
//                intent.putExtra("Bundle", bundle);
//                context.startActivity(intent);}
//            else{
//                Toast.makeText(context, "Wrong Password or Unexisted Account!", Toast.LENGTH_LONG).show();
//            }
        }
    }

    public void register(String email, String password, String password2){
        if((!email.equals(""))  &&(!password.equals("")) && (!password2.equals("")))
        {
            if(Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", email))
            {
                if(password.equals(password2))
                {
                    userModel=new UserModel(context);
                    userModel.addUserToRemote(email,password);
                }
                else
                {Toast.makeText(context, "Passwords are not same",
                        Toast.LENGTH_LONG).show();}
            }
            else
            {Toast.makeText(context, "Account format should be email address",
                    Toast.LENGTH_LONG).show();}
        } else
        {Toast.makeText(context, "Please fill the information. No empty",
                Toast.LENGTH_LONG).show();}
    }


    public void login(){

    }

    public void logout(){

    }

    public void addContact(){

    }

    public void searchPeople(){

    }

    public void deleteContact(){

    }

    public void uploadUserInfo(){

    }

}
