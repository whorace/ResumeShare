package edu.brandeis.cs.jiahuiming.resumeshare.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.LoginController;

public class LoginActivity extends AppCompatActivity {

    private Button mBtn_login;
    private Button mBtn_register;
    private EditText mTv_account;
    private EditText mTv_password;
    private EditText mTv_password_again;
    private boolean mRegisterStatue;
    private boolean mLoginStatue;
    private LoginController mLoginController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mRegisterStatue=false;
        mLoginStatue=true;
        mLoginController=new LoginController();

//        mLoginPresenterImpl=new LoginPresenterImpl(this,this);
        mTv_account=(EditText)findViewById(R.id.tv_account);
        mTv_password=(EditText)findViewById(R.id.tv_password);
        mTv_password_again=(EditText)findViewById(R.id.tv_password_again);
        mTv_account.setText("account");
        mTv_password.setText("password");
        mTv_password_again.setText("password again");
        mTv_password_again.setVisibility(View.INVISIBLE);
        mBtn_login=(Button)findViewById(R.id.btn_login);
        mBtn_register=(Button)findViewById(R.id.btn_register);
        mBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mLoginStatue==true&&mRegisterStatue==false){

                }
//                    mLoginPresenterImpl.login(mTv_account.getText().toString(),mTv_password.getText().toString());}
                else if(mLoginStatue==false&&mRegisterStatue==true){
                    mRegisterStatue=false;
                    mLoginStatue=true;
                    //LoginController.login(mTv_account.getText().toString(),mTv_password.getText().toString());
                    mTv_password_again.setVisibility(View.INVISIBLE);

                }
            }
        });

        mBtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRegisterStatue==false&&mLoginStatue==true){
                    mRegisterStatue=true;
                    mLoginStatue=false;
                    mTv_password_again.setVisibility(View.VISIBLE);
                }else if(mRegisterStatue==true&&mLoginStatue==false){

//                    mLoginPresenterImpl.register(mTv_account.getText().toString(),mTv_password.getText().toString(),mTv_password_again.getText().toString());

                }
            }
        });

    }
}
