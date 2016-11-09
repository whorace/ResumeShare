package edu.brandeis.cs.jiahuiming.resumeshare.views.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.brandeis.cs.jiahuiming.resumeshare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment{

    private TextView mTv_FirstName;
    private TextView mTv_SecondName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mFragment = inflater.inflate(R.layout.fragment_profile, container, false);
        mTv_FirstName = (TextView) mFragment.findViewById(R.id.Tv_firstname);
        mTv_SecondName = (TextView) mFragment.findViewById(R.id.Tv_secondname);

        return mFragment;
    }
}
