package edu.brandeis.cs.jiahuiming.resumeshare.views.activities;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.LoginController;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.RegisterController;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.UserController;

public class LoginActivity extends AppCompatActivity {

    private Button mBtn_login_register;
    private Button mBtn_signup;
    private EditText mTv_account;
    private EditText mTv_password;
    private EditText mTv_password_again;
    private boolean mLoginStatue;
//    private LoginController mLoginController;
//    private RegisterController mRegisterController;
    private UserController mUserController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginStatue=true;
        mUserController=new UserController(LoginActivity.this);

//        mLoginPresenterImpl=new LoginPresenterImpl(this,this);
        mTv_account=(EditText)findViewById(R.id.tv_account);
        mTv_password=(EditText)findViewById(R.id.tv_password);
        mTv_password_again=(EditText)findViewById(R.id.tv_password_again);
        mTv_password_again.setVisibility(View.GONE);
        mBtn_login_register=(Button)findViewById(R.id.btn_login);
        mBtn_signup=(Button)findViewById(R.id.btn_signup);
        mBtn_signup.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );


        mBtn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLoginStatue==true){
                    mLoginStatue=false;
                    mBtn_login_register.setText(getString(R.string.btn_register));
                    mTv_password_again.setVisibility(View.VISIBLE);
                    mBtn_signup.setText(getString(R.string.hint_signin));
                }else{
                    mLoginStatue=true;
                    mBtn_login_register.setText(R.string.btn_login);
                    mTv_password_again.setVisibility(View.GONE);
                    mBtn_signup.setText(getString(R.string.hint_signup));
                }
            }
        });


        mBtn_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLoginStatue==true){
                    Toast.makeText(LoginActivity.this,""+mTv_password.getText().toString(),Toast.LENGTH_SHORT).show();
                    mUserController.login(mTv_account.getText().toString(),mTv_password.getText().toString());

                }else{
                    mUserController.register(mTv_account.getText().toString(),mTv_password.getText().toString(),mTv_password_again.getText().toString());


                }
            }
        });

    }
}
