package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.brandeis.cs.jiahuiming.resumeshare.beans.User;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.DBOpenHelper;

/**
 * Created by jiahuiming on 11/8/16.
 */

public class UserModel {
    private User user;
    private Context context;
    private DBOpenHelper mDBOpenHelper;
    private SQLiteDatabase db;

    public UserModel(Context context){
        this.context=context;
    }

    public User getUserfromlocal(String account){
        mDBOpenHelper=new DBOpenHelper(this.context,"User");
        db=mDBOpenHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from User Where Account = "+"account",null);
        user=new User();
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            user.setAccount(cursor.getString(cursor.getColumnIndex("Account")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("Password")));
            user.setFirstName(cursor.getString(cursor.getColumnIndex("FirstName")));
            user.setSecondName(cursor.getString(cursor.getColumnIndex("SecondName")));

        }
        return user;
    }

    public long addUserToLocal(String account,String password){
        mDBOpenHelper=new DBOpenHelper(this.context,"User");
        db=mDBOpenHelper.getReadableDatabase();
        ContentValues value=new ContentValues();
        value.put("Account",account);
        value.put("Password",password);
        return db.insert("User",null,value);
    }
}
