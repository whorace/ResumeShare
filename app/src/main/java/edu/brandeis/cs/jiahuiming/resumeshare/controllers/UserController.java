package edu.brandeis.cs.jiahuiming.resumeshare.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Pattern;

import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ContactsAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.EducationAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ExperienceAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.RequestAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.SearchResultAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.SkillAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Contact;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Experience;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Skill;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.User;
import edu.brandeis.cs.jiahuiming.resumeshare.models.ContactModel;
import edu.brandeis.cs.jiahuiming.resumeshare.models.EducationModel;
import edu.brandeis.cs.jiahuiming.resumeshare.models.ExperienceModel;
import edu.brandeis.cs.jiahuiming.resumeshare.models.RequestModel;
import edu.brandeis.cs.jiahuiming.resumeshare.models.SkillModel;
import edu.brandeis.cs.jiahuiming.resumeshare.models.UserModel;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.NetworkChecker;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;

/**
 * Created by jiahuiming on 11/13/16.
 */

public class UserController {

    private UserModel userModel;
    private ContactModel contactModel;
    private Context context;
    private EducationModel educationModel;
    private SkillModel skillModel;
    private ExperienceModel experienceModel;
    private RequestModel requestModel;

    public UserController(Context context){
        this.context=context;
    }

    public void login(String account,String password) {
        NetworkChecker networkerchecker = new NetworkChecker(this.context);

        if (account.equals("") || password.equals("")) {
            Toast.makeText(context, "Account and Password can not be empty!", Toast.LENGTH_LONG).show();
        } else if (!networkerchecker.isNetworkConnected()) {
            Toast.makeText(context, "no avaliable network connection can not be use!", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(context, HomeActivity.class);
            Bundle bundle = new Bundle();
            userModel=new UserModel(context);
            userModel.loginfromRemote(account,password);
        }
    }

    public void register(String email, String password, String password2){
        if((!email.equals(""))  &&(!password.equals("")) && (!password2.equals("")))
        {
            if(Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", email))
            {
                if(password.equals(password2))
                {
                    userModel=new UserModel(context);
                    userModel.addUserToRemote(email,password);
                }
                else
                {Toast.makeText(context, "Passwords are not same",
                        Toast.LENGTH_LONG).show();}
            }
            else
            {Toast.makeText(context, "Account format should be email address",
                    Toast.LENGTH_LONG).show();}
        } else
        {Toast.makeText(context, "Please fill the information. No empty",
                Toast.LENGTH_LONG).show();}
    }

    public void showInfo(final TextView mEmail, final TextView mName){
        userModel=new UserModel(context);
        userModel.showInfo(((HomeActivity)context).getCurrentUser(),mEmail,mName);
    }

    public void addEducation(Education education,final EducationAdapter educationAdapter){
        educationModel=new EducationModel(context);
        educationModel.addEducationToRemote(((HomeActivity)context).getCurrentUser(),education,educationAdapter);

    }

    public void addExperience(Experience experience,final ExperienceAdapter experienceAdapter){
        experienceModel=new ExperienceModel(context);
        experienceModel.addExperienceToRemote(((HomeActivity)context).getCurrentUser(),experience,experienceAdapter);

    }

    public void addSkill(Skill skill,final SkillAdapter skillAdapter){
        skillModel=new SkillModel(context);
        skillModel.addSkillToRemote(((HomeActivity)context).getCurrentUser(),skill,skillAdapter);

    }

    public void logout(){

    }

    public void addContact(String hostaccount,String guestaccount){
        contactModel=new ContactModel(context);
        contactModel.addContactToRemote(hostaccount,guestaccount);

    }

    public void refuseRequest(String id){
        requestModel=new RequestModel(context);
        requestModel.delRequestOnRemote(id);
    }



    public void searchPeople(){

    }

    public void deleteContact(){

    }

    public void uploadUserInfo(){

    }

    public void showContacts(final ContactsAdapter contactsAdapter){
        contactModel=new ContactModel(context);
        contactModel.loadContactsfromRemote(((HomeActivity)context).getCurrentUser(),contactsAdapter);
    }

    public void modifyEducation(Education education){
        educationModel= new EducationModel(context);
        educationModel.upDateEducationOnRemote(education);

    }

    public void modifyExperience(Experience experience){
        experienceModel= new ExperienceModel(context);
        experienceModel.upDateExperienceOnRemote(experience);

    }

    public void modifySkill(Skill skill){
        skillModel= new SkillModel(context);
        skillModel.upDateSkillOnRemote(skill);
    }

    public void delEducation(Education education){
        educationModel= new EducationModel(context);
        educationModel.delEducationOnRemote(education);

    }

    public void delExperience(Experience experience){
        experienceModel= new ExperienceModel(context);
        experienceModel.delExperienceOnRemote(experience);

    }

    public void delSkill(Skill skill){
        skillModel= new SkillModel(context);
        skillModel.delSkillOnRemote(skill);
    }

    public void showResume(final EducationAdapter educationAdapter, final ExperienceAdapter experienceAdapter, final SkillAdapter skillAdapter){
        Log.d("Test","UserController+showResume");
        Toast.makeText(context,"UserController+showResume",Toast.LENGTH_LONG).show();
        educationModel=new EducationModel(context);
        skillModel=new SkillModel(context);
        experienceModel=new ExperienceModel(context);

        educationModel.loadEducationFromRemote(((HomeActivity)context).getCurrentUser(),educationAdapter);
        experienceModel.loadExperienceFromRemote(((HomeActivity)context).getCurrentUser(),experienceAdapter);
        skillModel.loadSkillFromRemote(((HomeActivity)context).getCurrentUser(),skillAdapter);

    }

    public void sendRequest(String account,String name,String imageid,String message){
        requestModel=new RequestModel(context);
        requestModel.addRequestToRemote(((HomeActivity)context).getCurrentUser(),account,name,imageid,message);

    }

    public void sentIntantLocation(){
        Log.d("Test","sentIntantLocation");

    }

    public void getSearchResult(final SearchResultAdapter searchResultAdapter){
        searchResultAdapter.cleanData();
        for(int i=0;i<10;i++){
            User user=new User();
            user.setAccount("jiahm92@qq.com"+new Integer(i).toString());
            user.setFirstName("Huiming");
            user.setSecondName("Jia");
            searchResultAdapter.putData(user);
        }
        searchResultAdapter.notifyDataSetChanged();
    }

    public void showRequests(final RequestAdapter requestAdapter){
        requestModel=new RequestModel(context);
        requestModel.loadRequestsfromRemote(((HomeActivity)context).getCurrentUser(),requestAdapter);
    }

    public void showRequestDetail(){

    }


}
