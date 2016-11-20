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

                        String result_id;
                        String result_account;
                        String result_school;
                        String result_degree;
                        String result_major;
                        String result_startyear;
                        String result_endyear;
                        JSONArray ja=new JSONArray(json);

                    for(int i =0; i<ja.length(); i++) {
                        JSONObject jo = (JSONObject) ja.get(i);

                        result_id = jo.getString("id");
                        result_account = jo.getString("account");
                        result_school = jo.getString("school");
                        result_degree = jo.getString("degree");
                        result_major = jo.getString("major");
                        result_startyear = jo.getString("startyear");
                        result_endyear = jo.getString("endyear");

                        education = new Education();

                        education.setId(result_id);
                        education.setAccount(result_account);
                        education.setSchool(result_school);
                        education.setDegree(result_degree);
                        education.setMajor(result_major);
                        education.setStartYear(result_startyear);
                        education.setEndYear(result_endyear);

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
        task.execute("user","modifyEducation","id="+education.getId()+"&account="+education.getAccount()+"&school="+education.getSchool()+"&major="+education.getSchool()+"&degree="+education.getDegree()+"&startyear="+education.getStartYear()+"&endyear="+education.getEndYear());

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

        task.execute("user","addEducation","account="+account+"&school="+education.getSchool()+"&major="+education.getSchool()+"&degree="+education.getDegree()+"&startyear="+education.getStartYear()+"&endyear="+education.getEndYear());

    }
}
