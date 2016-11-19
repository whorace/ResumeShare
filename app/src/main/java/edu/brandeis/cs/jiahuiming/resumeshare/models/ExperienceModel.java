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
    private String result;

    public ExperienceModel(Context context) {
        this.context = context;
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
