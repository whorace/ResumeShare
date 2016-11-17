package edu.brandeis.cs.jiahuiming.resumeshare.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.brandeis.cs.jiahuiming.resumeshare.R;

/**
 * Created by jiahuiming on 11/16/16.
 */

public class SkillFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}
