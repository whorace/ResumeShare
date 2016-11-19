package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

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

//    public ArrayList<Skill> getSkillsfromlocal(String account){
//        Cursor cursor=db.rawQuery("select * from Skill Where Account = "+"account",null);
//        ArrayList<Skill> skillslist=new ArrayList<Skill>();
//        if(cursor.getCount()>0){
//            Skill skill=new Skill();
//            while (cursor.moveToNext()){
//                skill.setAccount(cursor.getString(cursor.getColumnIndex("Account")));
//                skill.setSkill(cursor.getString(cursor.getColumnIndex("Skill")));
//                skillslist.add(skill);
//            }
//        }
//        return skillslist;
//
//    }
//
//    public int getSkillsCountfromlocal(String account){{
//        Cursor cursor=db.rawQuery("select * from Skill Where Account = "+"account ",null);
//        return cursor.getCount();
//    }
//
//    }
//
//    public Skill getSkillfromlocal(String account,String skill){
//        Cursor cursor=db.rawQuery("select * from Skill Where Account = "+"account and Skill = "+skill,null);
//        Skill mskill=new Skill();
//        if(cursor.getCount()>0){
//            cursor.moveToFirst();
//            mskill.setAccount(cursor.getString(cursor.getColumnIndex("Account")));
//            mskill.setSkill(cursor.getString(cursor.getColumnIndex("Skill")));
//        }
//        return mskill;
//    }

    public void loadContactsfromRemote(String account,final ContactsAdapter contactsAdapter) {
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    User user=new User();
                    user.setFirstName(result);
                    user.setSecondName(result);
                    user.setAccount(result);
                    contactsAdapter.putData(user);
                    contactsAdapter.notifyDataSetChanged();
//                    Intent intent = new Intent(context, HomeActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("account", user.getAccount());
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
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
