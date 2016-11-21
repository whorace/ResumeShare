package edu.brandeis.cs.jiahuiming.resumeshare.views.activities;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.UserController;

public class LoginActivity extends Activity {

    private Button mBtn_login_register;
    private Button mBtn_signup;
    private EditText mTv_account;
    private EditText mTv_password;
    private EditText mTv_password_again;
    private boolean mLoginStatue;
    private UserController mUserController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginStatue=true;
        mUserController=new UserController(LoginActivity.this);
        mTv_account=(EditText)findViewById(R.id.tv_account);
        mTv_password=(EditText)findViewById(R.id.tv_password);
        mTv_password_again=(EditText)findViewById(R.id.tv_password_again);
        mTv_password_again.setVisibility(View.INVISIBLE);
        mBtn_login_register=(Button)findViewById(R.id.btn_login);
        mBtn_signup=(Button)findViewById(R.id.btn_signup);
        mBtn_signup.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
        mBtn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLoginStatue==true){
                    mLoginStatue=false;
                    mBtn_login_register.setText(getString(R.string.btn_register));
                    AnimationSet animationSet=new AnimationSet(true);
                    AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
                    alphaAnimation.setDuration(1000);
                    TranslateAnimation translateAnimation=new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF,0f,
                            Animation.RELATIVE_TO_SELF,0f,
                            Animation.RELATIVE_TO_SELF,-1f,
                            Animation.RELATIVE_TO_SELF,0f);
                    translateAnimation.setDuration(500);
                    animationSet.addAnimation(translateAnimation);
                    animationSet.addAnimation(alphaAnimation);
                    mTv_password_again.startAnimation(animationSet);
                    mTv_password_again.setVisibility(View.VISIBLE);
                    mBtn_signup.setText(getString(R.string.hint_signin));
                }else{
                    mLoginStatue=true;
                    mBtn_login_register.setText(R.string.btn_login);
                    AnimationSet animationSet=new AnimationSet(true);
                    AlphaAnimation alphaAnimation=new AlphaAnimation(1,0);
                    alphaAnimation.setDuration(1000);
                    TranslateAnimation translateAnimation=new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF,0f,
                            Animation.RELATIVE_TO_SELF,0f,
                            Animation.RELATIVE_TO_SELF,0f,
                            Animation.RELATIVE_TO_SELF,-1f);
                    translateAnimation.setDuration(500);
                    animationSet.addAnimation(translateAnimation);
                    animationSet.addAnimation(alphaAnimation);
                    mTv_password_again.startAnimation(animationSet);
                    mTv_password_again.setVisibility(View.INVISIBLE);
                    mBtn_signup.setText(getString(R.string.hint_signup));
                }
            }
        });

        mBtn_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLoginStatue==true){
                    mUserController.login(mTv_account.getText().toString(),mTv_password.getText().toString());

                }else{
                    mUserController.register(mTv_account.getText().toString(),mTv_password.getText().toString(),mTv_password_again.getText().toString());
                }
            }
        });

    }
}
