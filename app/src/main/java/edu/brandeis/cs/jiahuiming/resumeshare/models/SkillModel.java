package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.adapters.EducationAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ExperienceAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.SkillAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Experience;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Skill;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.User;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.DBOpenHelper;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.HttpTask;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.ListUtils;

/**
 * Created by jiahuiming on 11/8/16.
 */

public class SkillModel {
    private Context context;
    private DBOpenHelper mDBOpenHelper;
    private SQLiteDatabase db;
    private String result;

    public SkillModel(Context context) {
        this.context = context;
        mDBOpenHelper=new DBOpenHelper(this.context,"User");
        db=mDBOpenHelper.getReadableDatabase();
    }

    public void loadSkillFromRemote(String account, final SkillAdapter skillAdapter, final ListView lv_skill) {
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    Skill skill=new Skill();
                    JSONArray ja=new JSONArray(json);

                    for(int i =0; i<ja.length(); i++){
                        skill=new Skill();
                        skill.setSkill(((JSONObject)ja.get(i)).getString("skill"));
                        skill.setId(((JSONObject)ja.get(i)).getString("id"));
                        skill.setAccount(((JSONObject)ja.get(i)).getString("account"));
                        skillAdapter.putData(skill);
                    }

                    ListUtils mListUtils=new ListUtils();
                    mListUtils.setDynamicHeight(lv_skill);
                    skillAdapter.notifyDataSetChanged();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","showSkill","account="+account);
    }

    public void upDateSkillOnRemote(Skill skill){
        HttpTask task = new HttpTask();

        task.setTaskHandler(new HttpTask.HttpTaskHandler()
        {
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
        task.execute("user","modifySkill","id="+skill.getId()+"&account="+skill.getAccount()+"&skill="+skill.getSkill());

    }


    public void delSkillOnRemote(Skill skill,final ListView lv_skill){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler()
        {
            public void taskSuccessful(String json) {
                try {
                    ListUtils mListUtils=new ListUtils();
                    mListUtils.setDynamicHeight(lv_skill);
                    result=json;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","delSkill","id="+skill.getId());
    }

    public void addSkillToRemote(String account,final Skill skill,final SkillAdapter skillAdapter,final ListView lv_skill){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    skillAdapter.putData(skill);
                    skillAdapter.notifyDataSetChanged();
                    ListUtils mListUtils=new ListUtils();
                    mListUtils.setDynamicHeight(lv_skill);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","addSkill","account="+account+"&skill="+skill.getSkill());

    }
}
