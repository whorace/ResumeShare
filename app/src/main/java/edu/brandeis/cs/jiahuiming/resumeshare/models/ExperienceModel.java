package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.brandeis.cs.jiahuiming.resumeshare.adapters.EducationAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ExperienceAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.SkillAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Experience;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Skill;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.DBOpenHelper;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.HttpTask;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.ListUtils;

/**
 * Created by jiahuiming on 11/8/16.
 */

public class ExperienceModel {
    private Context context;
    private String result;

    public ExperienceModel(Context context) {
        this.context = context;
    }

    public void loadExperienceFromRemote(String account, final ExperienceAdapter experienceAdapter, final ListView lv_experience) {
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    Experience experience=new Experience();

                    String result_id;
                    String result_account;
                    String result_company;
                    String result_position;
                    JSONArray ja=new JSONArray(json);

                    for(int i =0; i<ja.length(); i++){
                        JSONObject jo=(JSONObject)ja.get(i);

                        result_id=jo.getString("id");
                        result_account=jo.getString("account");
                        result_company=jo.getString("company");
                        result_position=jo.getString("position");


                        experience=new Experience();

                        experience.setId(result_id);
                        experience.setAccount(result_account);
                        experience.setCompany(result_company);
                        experience.setPosition(result_position);
                        experienceAdapter.putData(experience);


                    }

                    experienceAdapter.notifyDataSetChanged();

                    ListUtils mListUtils=new ListUtils();
                    mListUtils.setDynamicHeight(lv_experience);
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

    public void delExperienceOnRemote(Experience experience,final ListView lv_experience){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler()
        {
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    ListUtils mListUtils=new ListUtils();
                    mListUtils.setDynamicHeight(lv_experience);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","delExprience","id="+experience.getId());
    }
    public void addExperienceToRemote(String account,final Experience experience,final ExperienceAdapter experienceAdapter,final ListView lv_experience){
            HttpTask task = new HttpTask();
            task.setTaskHandler(new HttpTask.HttpTaskHandler(){
                public void taskSuccessful(String json) {
                    try {
                        result=json;
                        experienceAdapter.putData(experience);
                        experienceAdapter.notifyDataSetChanged();
                        ListUtils mListUtils=new ListUtils();
                        mListUtils.setDynamicHeight(lv_experience);
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
