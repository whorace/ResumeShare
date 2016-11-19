package edu.brandeis.cs.jiahuiming.resumeshare.controllers;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ContactsAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.EducationAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ExperienceAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.SkillAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Experience;
import edu.brandeis.cs.jiahuiming.resumeshare.models.ContactModel;
import edu.brandeis.cs.jiahuiming.resumeshare.models.EducationModel;
import edu.brandeis.cs.jiahuiming.resumeshare.models.ExperienceModel;
import edu.brandeis.cs.jiahuiming.resumeshare.models.SkillModel;
import edu.brandeis.cs.jiahuiming.resumeshare.models.UserModel;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class ContactController {
    private Context context;
    private EducationModel educationModel;
    private SkillModel skillModel;
    private UserModel userModel;
    private ExperienceModel experienceModel;

    public ContactController(Context context){
        this.context=context;
    }
//
//    public ShowResume(EducationAdapter)

    public void showResume(final EducationAdapter educationAdapter, final ExperienceAdapter experienceAdapter, final SkillAdapter skillAdapter){
        educationModel=new EducationModel(context);
        skillModel=new SkillModel(context);
        experienceModel=new ExperienceModel(context);

        educationModel.loadEducationFromRemote(((HomeActivity)context).getResumeAccount(),educationAdapter);
        experienceModel.loadExperienceFromRemote(((HomeActivity)context).getResumeAccount(),experienceAdapter);
        skillModel.loadSkillFromRemote(((HomeActivity)context).getResumeAccount(),skillAdapter);

        Toast.makeText(context,"ContactController"+((HomeActivity)context).getResumeAccount(),Toast.LENGTH_LONG).show();

//        educationModel.loadEducationFromRemote("123@qq.com",educationAdapter);
//        experienceModel.loadExperienceFromRemote("123@qq.com",experienceAdapter);
//        skillModel.loadSkillFromRemote("123@qq.com",skillAdapter);


    }

    public void showInfo(final TextView mEmail,final TextView mName){
        userModel=new UserModel(context);
        userModel.showInfo(((HomeActivity)context).getResumeAccount(),mEmail,mName);
    }
}
