package edu.brandeis.cs.jiahuiming.resumeshare.controllers;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by jiahuiming on 11/8/16.
 */

public class RegisterController {

    private String email;
    private String password;
    private String password2;
    private Context context;
    public RegisterController(Context context, String email, String password, String password2){

        this.context=context;
        this.email=email;
        this.password=password;
        this.password2=password2;


    }
    public void verifyInput(){

        if((!email.equals(""))  &&(!password.equals("")) && (!password2.equals("")))
        {
            //检查邮箱地址是否正确
            if(Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", email))
            {
                //检查两次密码是否一致
                if(password.equals(password2))
                {

                    Toast.makeText(context, "Registering",
                            Toast.LENGTH_LONG).show();
                    //register();
                }
                else
                {

                    Toast.makeText(context, "Passwords are not same",
                            Toast.LENGTH_LONG).show();
                }
            }
            else
            {

                Toast.makeText(context, "Account format should be email address",
                        Toast.LENGTH_LONG).show();

            }
        }
        else
        {
            Toast.makeText(context, "Please fill the information. No empty",
                    Toast.LENGTH_LONG).show();


        }



    }

}
