package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import edu.brandeis.cs.jiahuiming.resumeshare.adapters.EducationAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Skill;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.DBOpenHelper;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.HttpTask;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;

/**
 * Created by jiahuiming on 11/8/16.
 */

public class EducationModel {
    private Context context;
    private String result;

    public EducationModel(Context context) {
        this.context = context;
    }

    public void loadEducationFromRemote(String account,final EducationAdapter educationAdapter) {
        HttpTask task = new HttpTask();

        task.setTaskHandler(new HttpTask.HttpTaskHandler()
        {
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    Education education=new Education();
                    education.setSchool(result);
                    education.setMajor(result);
                    education.setDegree(result);
                    education.setStartYear(result);
                    education.setEndYear(result);
                    educationAdapter.putData(education);
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

    public void delEducationOnRemote(Education education){
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
        task.execute("user","delEducation","id="+education.getId());
    }

    public void addEducationToRemote(String account,final Education education,final EducationAdapter educationAdapter){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler()
        {
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    educationAdapter.putData(education);
                    educationAdapter.notifyDataSetChanged();
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
