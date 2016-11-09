package edu.brandeis.cs.jiahuiming.resumeshare.views.fragments;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.views.widgets.WaveView;

/**
 * Created by jiahuiming on 11/9/16.
 */

public class ResumeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mFragment = inflater.inflate(R.layout.fragment_home, container, false);
        return mFragment;
    }
}
