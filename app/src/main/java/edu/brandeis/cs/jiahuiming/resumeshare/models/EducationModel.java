package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.brandeis.cs.jiahuiming.resumeshare.beans.Skill;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.DBOpenHelper;

/**
 * Created by jiahuiming on 11/8/16.
 */

public class EducationModel {
    private Context context;
    private DBOpenHelper mDBOpenHelper;
    private SQLiteDatabase db;

    public EducationModel(Context context) {
        this.context = context;
        mDBOpenHelper=new DBOpenHelper(this.context,"User");
        db=mDBOpenHelper.getReadableDatabase();
    }

    public ArrayList<Skill> getSkillsfromlocal(String account){
        Cursor cursor=db.rawQuery("select * from Skill Where Account = "+"account",null);
        ArrayList<Skill> skillslist=new ArrayList<Skill>();
        if(cursor.getCount()>0){
            Skill skill=new Skill();
            while (cursor.moveToNext()){
                skill.setAccount(cursor.getString(cursor.getColumnIndex("Account")));
                skill.setSkill(cursor.getString(cursor.getColumnIndex("Skill")));
                skillslist.add(skill);
            }
        }
        return skillslist;

    }

    public int getSkillsCountfromlocal(String account){{
        Cursor cursor=db.rawQuery("select * from Skill Where Account = "+"account ",null);
        return cursor.getCount();
    }

    }

    public Skill getSkillfromlocal(String account,String skill){
        Cursor cursor=db.rawQuery("select * from Skill Where Account = "+"account and Skill = "+skill,null);
        Skill mskill=new Skill();
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            mskill.setAccount(cursor.getString(cursor.getColumnIndex("Account")));
            mskill.setSkill(cursor.getString(cursor.getColumnIndex("Skill")));
        }
        return mskill;
    }
}
