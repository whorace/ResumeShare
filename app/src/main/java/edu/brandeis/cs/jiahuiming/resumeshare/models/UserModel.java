package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import edu.brandeis.cs.jiahuiming.resumeshare.beans.User;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.DBOpenHelper;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.HttpTask;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;

/**
 * Created by jiahuiming on 11/8/16.
 */

public class UserModel {
    private User user;
    private Context context;
    private DBOpenHelper mDBOpenHelper;
    private SQLiteDatabase db;
    private String result;
    public UserModel(Context context){
        this.context=context;
        this.user=new User();
    }

    public void addUserToRemote(String account,String password){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    result=json;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","register","account="+account+"&password="+password);
    }


    public void loginfromRemote(String account,String password) {
        HttpTask task = new HttpTask();
        user.setAccount(account);
        user.setPassword(password);
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    //result=json;
                    JSONObject jsObj=new JSONObject(json);
                    result = jsObj.getString("result");
                    Log.d("test",result);
                    if(result.equals("true")){

                        Toast.makeText(context,"Login Successed",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, HomeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("account", user.getAccount());
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                    else{

                        Toast.makeText(context,"Login Failed",Toast.LENGTH_LONG).show();


                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","login","account="+account+"&password="+password);
    }

    public void showInfo(String account, final TextView email,final TextView name){
        HttpTask task = new HttpTask();
        user.setAccount(account);
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    email.setText(result);
                    name.setText(result);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","showInfo","account="+account);

    }
}
