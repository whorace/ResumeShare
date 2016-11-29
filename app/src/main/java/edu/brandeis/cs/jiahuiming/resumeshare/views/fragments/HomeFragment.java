package edu.brandeis.cs.jiahuiming.resumeshare.views.fragments;


import android.content.DialogInterface;
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
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.SearchResultAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.UserController;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.LocationUtil;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.TimeUtil;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.BaseDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.EditDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.widgets.WaveView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private SearchResultAdapter mSearchResultAdapter;
    private ListView mListView;
    private WaveView mWaveView;
    private Button mButton;
    private UserController mUserController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mFragment = inflater.inflate(R.layout.fragment_home, container, false);
        mWaveView = (WaveView)mFragment.findViewById(R.id.waveview);
        mWaveView.setDuration(5000);
        mWaveView.setStyle(Paint.Style.FILL);
        mWaveView.setColor(getResources().getColor(R.color.colorAccent_transparent));
        mWaveView.setInterpolator(new LinearOutSlowInInterpolator());
        mButton = (Button)mFragment.findViewById(R.id.button1);
        mListView=(ListView)mFragment.findViewById(R.id.lv_searchresult);
        mSearchResultAdapter=new SearchResultAdapter(getActivity());
        mListView.setAdapter(mSearchResultAdapter);
        mUserController=new UserController(getActivity());

        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    mWaveView.start();
                    Log.d("Wave,","start");
                    LocationUtil locUtil=new LocationUtil(getActivity());
                    locUtil.method();
                    Log.d("HomeFragment:","Long: "+locUtil.getLongitude()+" Lat: "+locUtil.getLatitude());
                    String longitude=""+locUtil.getLongitude();
                    String laititude=""+locUtil.getLatitude();

                    TimeUtil timeUtil=new TimeUtil();
                    timeUtil.displayTime();
                    Log.d("HomeFragment:","current time mills: "+ timeUtil.getTimecurrentTimeMillis());
                    String currentTime=""+timeUtil.getTimecurrentTimeMillis();
                    Toast.makeText(getActivity(),"NOW LOCATION:"+longitude+","+laititude,Toast.LENGTH_SHORT).show();
                    mUserController.sentIntantLocation(((HomeActivity)getActivity()).getCurrentUser(),currentTime,longitude,laititude);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    mWaveView.stop();
                    mListView.setVisibility(View.VISIBLE);
                    AnimationSet animationSet=new AnimationSet(true);
                    TranslateAnimation translateAnimation=new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF,0f,
                            Animation.RELATIVE_TO_SELF,0f,
                            Animation.RELATIVE_TO_SELF,1f,
                            Animation.RELATIVE_TO_SELF,0f);
                    translateAnimation.setDuration(500);
                    animationSet.addAnimation(translateAnimation);
                    mListView.startAnimation(animationSet);
                    mUserController.getSearchResult(mSearchResultAdapter);
                    Log.d("Wave,","stop");
                }
                return false;
            }
        });
        return mFragment;
    }

}