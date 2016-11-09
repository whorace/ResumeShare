package edu.brandeis.cs.jiahuiming.resumeshare.controllers;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Pattern;

import edu.brandeis.cs.jiahuiming.resumeshare.models.UserModel;

/**
 * Created by jiahuiming on 11/8/16.
 */

public class RegisterController {

    private String email;
    private String password;
    private String password2;
    private Context context;
    private UserModel userModel;

    public RegisterController(Context context){
        this.context=context;
    }

    public void register(String email, String password, String password2){
        this.email=email;
        this.password=password;
        this.password2=password2;
        if((!email.equals(""))  &&(!password.equals("")) && (!password2.equals("")))
        {
            if(Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", email))
            {
                if(password.equals(password2))
                {
                    userModel=new UserModel(context);
                    if(userModel.addUserToLocal(email,password)!=-1)
                        Toast.makeText(context, "Success to Register", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(context, "Unable to Register", Toast.LENGTH_LONG).show();
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
}
