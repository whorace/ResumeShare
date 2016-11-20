package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ContactsAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Skill;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.User;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.DBOpenHelper;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.HttpTask;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;

/**
 * Created by jiahuiming on 11/8/16.
 */

public class ContactModel {
    private Context context;
    private DBOpenHelper mDBOpenHelper;
    private SQLiteDatabase db;
    private String result;

    public ContactModel(Context context) {
        this.context = context;
        mDBOpenHelper=new DBOpenHelper(this.context,"User");
        db=mDBOpenHelper.getReadableDatabase();
    }

    public void addContactToRemote(String hostaccount,String guestaccount){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    Log.d("addContactToRemote",result);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","addContact","hostaccount="+hostaccount+"&hostaccount="+guestaccount);
    }

    public void loadContactsfromRemote(String account,final ContactsAdapter contactsAdapter) {
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    User user=new User();
                    String result_id;
                    String result_account;
                    String result_password;
                    String result_firstname;
                    String result_secondname;
                    String result_imageid;
                    JSONArray ja=new JSONArray(json);
                    for(int i =0; i<ja.length(); i++){
                        JSONObject jo=(JSONObject)ja.get(i);
                        result_id=jo.getString("id");
                        result_account=jo.getString("account");
                        result_password=jo.getString("password");
                        result_firstname=jo.getString("firstname");
                        result_secondname=jo.getString("secondname");
                        result_imageid=jo.getString("imageid");

                        user=new User();
                        user.setId(result_id);
                        user.setAccount(result_account);
                        user.setPassword(result_password);
                        user.setFirstName(result_firstname);
                        user.setSecondName(result_secondname);
                        user.setImageId(result_imageid);
                        contactsAdapter.putData(user);
                    }
                    contactsAdapter.notifyDataSetChanged();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","showContacts","hostaccount="+account);
    }
}
