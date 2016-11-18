package edu.brandeis.cs.jiahuiming.resumeshare.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.volley.toolbox.Volley;

/**
 * Created by jiahuiming on 10/25/16.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ResumeShare";
    private String createTable;
    private String updateTable;

    public DBOpenHelper(Context context,String TableName){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        updateTable="DROP TABLE IF IT EXISTS"+TableName;
        if(TableName.equals("User"))
            createTable = "CREATE TABLE if not exists [User](ID integer PRIMARY KEY AUTOINCREMENT, Account text not null, Password text not null, FirstName text, SecondName text)";
        else if(TableName.equals("Skill")){
            createTable = "CREATE TABLE if not exists [Skill](ID integer PRIMARY KEY AUTOINCREMENT, Account text primary key,Skill text primary key)";

        }else if(TableName.equals("Experience")){
            createTable = "CREATE TABLE if not exists [Experience](ID integer PRIMARY KEY AUTOINCREMENT, Account text primary key,Company text, Position text, Order integer)";

        }else if(TableName.equals("Education")){
            createTable = "CREATE TABLE if not exists [Education](ID integer PRIMARY KEY AUTOINCREMENT, Account text primary key,School text not null, StartYear text, EndYear text, Degree text, Major text, Order integer)";

        }else if(TableName.equals("ProfileImage")){
            createTable = "CREATE TABLE if not exists [ProfileImage](ID integer PRIMARY KEY AUTOINCREMENT, Account text primary key, ScourceId text)";

        }else if(TableName.equals("Contact")){
            createTable = "CREATE TABLE if not exists [Contact](ID integer PRIMARY KEY AUTOINCREMENT, HostAccount text primary key, GuestAccount text)";

        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        if(!createTable.equals("")){
            db.beginTransaction();
            db.execSQL(createTable);
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(!updateTable.equals("")){
            db.execSQL(updateTable);
            onCreate(db);

        }
    }

    public void rebuildTable(SQLiteDatabase db,String TableName){
        if(TableName.equals("User"))
            createTable = "CREATE TABLE if not exists [User](ID integer PRIMARY KEY AUTOINCREMENT, Account text not null, Password text not null, FirstName text, SecondName text)";
        else if(TableName.equals("Skill")){
            createTable = "CREATE TABLE if not exists [Skill](ID integer PRIMARY KEY AUTOINCREMENT, Account text primary key,Skill text primary key)";

        }else if(TableName.equals("Experience")){
            createTable = "CREATE TABLE if not exists [Experience](ID integer PRIMARY KEY AUTOINCREMENT, Account text primary key,Company text, Position text, Order integer)";

        }else if(TableName.equals("Education")){
            createTable = "CREATE TABLE if not exists [Education](ID integer PRIMARY KEY AUTOINCREMENT, Account text primary key,School text not null, StartYear text, EndYear text, Degree text, Major text, Order integer)";

        }else if(TableName.equals("ProfileImage")){
            createTable = "CREATE TABLE if not exists [ProfileImage](ID integer PRIMARY KEY AUTOINCREMENT, Account text primary key, ScourceId text)";

        }else if(TableName.equals("Contact")){
            createTable = "CREATE TABLE if not exists [Contact](ID integer PRIMARY KEY AUTOINCREMENT, HostAccount text primary key, GuestAccount text)";

        }

        db.beginTransaction();
        db.execSQL(createTable);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void dropTable(SQLiteDatabase db,String tableName){
        String drop="DROP TABLE IF EXISTS ["+tableName+"]";
        db.beginTransaction();
        db.execSQL(drop);
        db.setTransactionSuccessful();
        db.endTransaction();

    }
}
