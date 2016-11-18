package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

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

//    public User getUserfromlocal(String account){
//        mDBOpenHelper=new DBOpenHelper(this.context,"User");
//        db=mDBOpenHelper.getReadableDatabase();
//        Cursor cursor=db.rawQuery("select * from User Where Account = "+"account",null);
//        user=new User();
//        if(cursor.getCount()>0){
//            cursor.moveToFirst();
//            user.setAccount(cursor.getString(cursor.getColumnIndex("Account")));
//            user.setPassword(cursor.getString(cursor.getColumnIndex("Password")));
//            user.setFirstName(cursor.getString(cursor.getColumnIndex("FirstName")));
//            user.setSecondName(cursor.getString(cursor.getColumnIndex("SecondName")));
//        }
//        return user;
//    }

//    public long addUserToLocal(String account,String password){
//        mDBOpenHelper=new DBOpenHelper(this.context,"User");
//        db=mDBOpenHelper.getWritableDatabase();
//        mDBOpenHelper.dropTable(db,"User");
//        mDBOpenHelper.rebuildTable(db,"User");
//        ContentValues value=new ContentValues();
//        value.put("Account",account);
//        value.put("Password",password);
//        return db.insert("User",null,value);
//    }

    public void addUserToRemote(String account,String password){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
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
                    result=json;
                    Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, HomeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Account", user.getAccount());
                    intent.putExtra("Bundle", bundle);
                    context.startActivity(intent);
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
}
