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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.EducationAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ExperienceAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.SkillAdapter;
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
    private ImageView mIv_Profile;
    private String account;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        account=((HomeActivity)getActivity()).getResumeAccount();
        View mFragment = inflater.inflate(R.layout.fragment_resume, container, false);
        mIv_Profile=(ImageView)mFragment.findViewById(R.id.im_fr_profile);
        mTv_Email=(TextView)mFragment.findViewById(R.id.tv_fr_account);
        mTv_Name=(TextView)mFragment.findViewById(R.id.tv_fr_name);
        mLv_Educations=(ListView)mFragment.findViewById(R.id.lv_fr_education);
        mLv_Experiences=(ListView)mFragment.findViewById(R.id.lv_fr_experience);
        mLv_Skills=(ListView)mFragment.findViewById(R.id.lv_fr_skill);

        EducationAdapter mEducationAdapter=new EducationAdapter(getActivity(),account);
        ExperienceAdapter mExperienceAdapter=new ExperienceAdapter(getActivity(),account);
        SkillAdapter mSkillAdapter=new SkillAdapter(getActivity(),account);
        mLv_Educations.setAdapter(mEducationAdapter);
        mLv_Experiences.setAdapter(mExperienceAdapter);
        mLv_Skills.setAdapter(mSkillAdapter);

        String currentAccount=((HomeActivity)getActivity()).getResumeAccount();
        Toast.makeText(getActivity(),currentAccount,Toast.LENGTH_LONG).show();

        return mFragment;
    }
}