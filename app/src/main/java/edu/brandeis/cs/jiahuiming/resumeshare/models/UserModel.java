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

import org.json.JSONArray;
import org.json.JSONObject;

import edu.brandeis.cs.jiahuiming.resumeshare.adapters.SearchResultAdapter;
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
                    JSONObject jsObj=new JSONObject(json);
                    result = jsObj.getString("result");
                    Log.d("test",result);
                    if(result.equals("true")){
                        Toast.makeText(context,"Register Successed",Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Toast.makeText(context,"Register Failed",Toast.LENGTH_SHORT).show();
                    }
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

                    String result_firstname;
                    String result_lastname;
                    String result_email;
                    JSONObject jo=new JSONObject(json);
                    String user= jo.getString("user");
                    JSONObject userobject=new JSONObject(user);
                    result_firstname = userobject.getString("firstname");
                    result_lastname = userobject.getString("secondname");
                    result_email = userobject.getString("account");

                    email.setText(result_email);
                    name.setText(result_firstname+" "+result_lastname);
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

    public void upDateUserNameOnRemote(String account,String firstname,String secondname) {
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    //result=json;
                    JSONObject jsObj=new JSONObject(json);
                    result = jsObj.getString("result");
                    Log.d("test",result);
                    if(result.equals("true")){
                        Toast.makeText(context,"Mofify Successed",Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Toast.makeText(context,"Modify Failed",Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","modifyInfo","account="+account+"&firstname="+firstname+"&secondname="+secondname);
    }

    public void loadSearchResultFromRemote(String account, final SearchResultAdapter searchResultAdapter){
            HttpTask task = new HttpTask();
            task.setTaskHandler(new HttpTask.HttpTaskHandler(){
                public void taskSuccessful(String json) {
                    try {
                        User user=new User();
                        JSONArray ja=new JSONArray(json);
                        for(int i =0; i<ja.length(); i++){
                            user=new User();
                            user.setId(((JSONObject)ja.get(i)).getString("id"));
                            user.setAccount(((JSONObject)ja.get(i)).getString("account"));
                            user.setPassword(((JSONObject)ja.get(i)).getString("password"));
                            user.setFirstName(((JSONObject)ja.get(i)).getString("firstname"));
                            user.setSecondName(((JSONObject)ja.get(i)).getString("secondname"));
                            user.setImageId(((JSONObject)ja.get(i)).getString("imageid"));
                            searchResultAdapter.putData(user);
                        }
                        searchResultAdapter.notifyDataSetChanged();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                public void taskFailed() {
                }
            });
            task.execute("user","showSearchResult","account="+account);

    }
}
