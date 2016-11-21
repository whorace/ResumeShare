package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.brandeis.cs.jiahuiming.resumeshare.adapters.EducationAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Skill;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.DBOpenHelper;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.HttpTask;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.ListUtils;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;
import edu.brandeis.cs.jiahuiming.resumeshare.views.fragments.ResumeFragment;

/**
 * Created by jiahuiming on 11/8/16.
 */

public class EducationModel {
    private Context context;
    private String result;

    public EducationModel(Context context) {
        this.context = context;
    }

    public void loadEducationFromRemote(String account, final EducationAdapter educationAdapter, final ListView lv_education) {
        HttpTask task = new HttpTask();

        task.setTaskHandler(new HttpTask.HttpTaskHandler()
        {
            public void taskSuccessful(String json) {
                try {
                        Education education=new Education();
                        JSONArray ja=new JSONArray(json);
                    for(int i =0; i<ja.length(); i++) {
                        education = new Education();
                        education.setId(((JSONObject) ja.get(i)).getString("id"));
                        education.setAccount(((JSONObject) ja.get(i)).getString("account"));
                        education.setSchool(((JSONObject) ja.get(i)).getString("school"));
                        education.setDegree(((JSONObject) ja.get(i)).getString("degree"));
                        education.setMajor(((JSONObject) ja.get(i)).getString("major"));
                        education.setStartYear(((JSONObject) ja.get(i)).getString("startyear"));
                        education.setEndYear(((JSONObject) ja.get(i)).getString("endyear"));
                        educationAdapter.putData(education);
                        ListUtils mListUtils = new ListUtils();
                        mListUtils.setDynamicHeight(lv_education);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","showEducation","account="+account);
    }

    public void upDateEducationOnRemote(Education education){
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
        task.execute("user","modifyEducation","id="+education.getId()+"&account="+education.getAccount()+"&school="+education.getSchool()+"&major="+education.getMajor()+"&degree="+education.getDegree()+"&startyear="+education.getStartYear()+"&endyear="+education.getEndYear());

    }

    public void delEducationOnRemote(Education education,final ListView lv_education){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler()
        {
            public void taskSuccessful(String json) {
                try {

                    ListUtils mListUtils = new ListUtils();
                    mListUtils.setDynamicHeight(lv_education);
                    result=json;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","delEducation","id="+education.getId());
    }

    public void addEducationToRemote(String account,final Education education,final EducationAdapter educationAdapter,final ListView lv_education){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler()
        {
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    educationAdapter.putData(education);
                    educationAdapter.notifyDataSetChanged();
                    ListUtils mListUtils = new ListUtils();
                    mListUtils.setDynamicHeight(lv_education);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });

        task.execute("user","addEducation","account="+account+"&school="+education.getSchool()+"&major="+education.getMajor()+"&degree="+education.getDegree()+"&startyear="+education.getStartYear()+"&endyear="+education.getEndYear());

    }
}
