package edu.brandeis.cs.jiahuiming.resumeshare.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jiahuiming on 10/25/16.
 */
public class DBServices extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ResumeShare";
    private static final String TEXT_TYPE = " TEXT";

    private static final String COMMA_SEP = ",";
//    private static final String SQL_CREATE_ENTRIES =
//            "CREATE TABLE " + UsersPersistenceContract.UserEntry.TABLE_NAME + " (" +
//                    UsersPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + "PRIMARY KEY" + COMMA_SEP +
//                    UsersPersistenceContract.UserEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
//                    UsersPersistenceContract.UserEntry.COLUMN_NAME_PWD + TEXT_TYPE +
//                    " )";


    public DBServices(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
//
//    public DBServices(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    public DBServices(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
//        super(context, name, factory, version, errorHandler);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.beginTransaction();
        String createTable = "CREATE TABLE if not exists [](Id integer primary key autoincrement,ExpenseLogEntryData text)";
        db.execSQL(createTable);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
