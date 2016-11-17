package edu.brandeis.cs.jiahuiming.resumeshare.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.EducationAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;

/**
 * Created by jiahuiming on 11/16/16.
 */

public class EducationFragment extends Fragment {
    private ListView mLv_Educations;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mFragment =  inflater.inflate(R.layout.fragment_education, container, false);

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
        mLv_Educations=(ListView)mFragment.findViewById(R.id.lv_education);
        EducationAdapter mEducationAdapter=new EducationAdapter(getActivity(),((HomeActivity)getActivity()).getResumeAccount(),meducationlist);
        mLv_Educations.setAdapter(mEducationAdapter);

        return mFragment;
    }
}
