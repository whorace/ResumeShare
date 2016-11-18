package edu.brandeis.cs.jiahuiming.resumeshare.views.fragments;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.EducationAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ExperienceAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.SkillAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Experience;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Skill;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;
import edu.brandeis.cs.jiahuiming.resumeshare.views.widgets.WaveView;

/**
 * Created by Horace on 16/11/9.
 */
public class ResumeFragment extends Fragment {

    private ListView mLv_Educations;
    private ListView mLv_Experiences;
    private ListView mLv_Skills;
    private TextView mTv_Email;
    private TextView mTv_Name;
    private String account;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<Education> meducationlist=new ArrayList<Education>();
        for(int i=0;i<3;i+=1){
            Education education=new Education();
            education.setSchool("Brandeis University");
            education.setDegree("Mater");
            education.setMajor("Computer Science");
            education.setStartYear("2010");
            education.setEndYear("2016");
            meducationlist.add(education);
        }
        ArrayList<Experience> mexperiencelist=new ArrayList<Experience>();
        for(int i=0;i<3;i+=1){
            Experience experience=new Experience();
            experience.setCompany("Apple Corp.");
            experience.setPosition("Software Engineer");
            mexperiencelist.add(experience);

        }
        ArrayList<Skill> mskilllist=new ArrayList<Skill>();
        for(int i=0;i<3;i+=1){
            Skill skill=new Skill();
            skill.setSkill("Java Programming");
            mskilllist.add(skill);
        }


        account=((HomeActivity)getActivity()).getResumeAccount();
        View mFragment = inflater.inflate(R.layout.fragment_resume, container, false);
        mTv_Email=(TextView)mFragment.findViewById(R.id.tv_resume_account);
        mTv_Name=(TextView)mFragment.findViewById(R.id.tv_resume_name);

        mLv_Educations=(ListView)mFragment.findViewById(R.id.lv_education);
        mLv_Experiences=(ListView)mFragment.findViewById(R.id.lv_experience);
        mLv_Skills=(ListView)mFragment.findViewById(R.id.lv_skill);

        EducationAdapter mEducationAdapter=new EducationAdapter(getActivity(),((HomeActivity)getActivity()).getResumeAccount(),meducationlist,0);
        ExperienceAdapter mExperienceAdapter=new ExperienceAdapter(getActivity(),((HomeActivity)getActivity()).getResumeAccount(),mexperiencelist,0);
        SkillAdapter mSkillAdapter=new SkillAdapter(getActivity(),((HomeActivity)getActivity()).getResumeAccount(),mskilllist);

        mLv_Educations.setAdapter(mEducationAdapter);
        mLv_Experiences.setAdapter(mExperienceAdapter);
        mLv_Skills.setAdapter(mSkillAdapter);

        ListUtils.setDynamicHeight(mLv_Educations);
        ListUtils.setDynamicHeight(mLv_Experiences);
        ListUtils.setDynamicHeight(mLv_Skills);

        Toast.makeText(getActivity(),((HomeActivity)getActivity()).getResumeAccount(),Toast.LENGTH_LONG).show();

        return mFragment;
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            BaseAdapter mListAdapter = (BaseAdapter) mListView.getAdapter();
            if (mListAdapter == null) {
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }
}