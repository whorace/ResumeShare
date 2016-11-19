package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import edu.brandeis.cs.jiahuiming.resumeshare.adapters.EducationAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ExperienceAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.SkillAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Experience;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Skill;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.DBOpenHelper;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.HttpTask;

/**
 * Created by jiahuiming on 11/8/16.
 */

public class ExperienceModel {
    private Context context;
    private DBOpenHelper mDBOpenHelper;
    private SQLiteDatabase db;
    private String result;

    public ExperienceModel(Context context) {
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

    public void loadExperienceFromRemote(String account, final ExperienceAdapter experienceAdapter) {
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    Experience experience=new Experience();
                    experience.setCompany(result);
                    experience.setPosition(result);
                    experienceAdapter.putData(experience);
                    experienceAdapter.notifyDataSetChanged();
                    Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","showExperience","account="+account);
    }

    public void upDateExperienceOnRemote(Experience experience){
        HttpTask task = new HttpTask();

        task.setTaskHandler(new HttpTask.HttpTaskHandler()
        {
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
        task.execute("user","showEducation","id="+experience.getId()+"&account="+experience.getAccount()+"&company="+experience.getCompany()+"&position="+experience.getPosition());

    }

    public void delExperienceOnRemote(Experience experience){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler()
        {
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
        task.execute("user","delExeprience","id="+experience.getId());
    }
    public void addExperienceToRemote(String account,final Experience experience,final ExperienceAdapter experienceAdapter){
            HttpTask task = new HttpTask();
            task.setTaskHandler(new HttpTask.HttpTaskHandler(){
                public void taskSuccessful(String json) {
                    try {
                        result=json;
                        experienceAdapter.putData(experience);
                        experienceAdapter.notifyDataSetChanged();
                        Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                public void taskFailed() {
                }
            });
            task.execute("user","addExperience","account="+account+"&company="+experience.getCompany()+"&position="+experience.getPosition());
    }
}
