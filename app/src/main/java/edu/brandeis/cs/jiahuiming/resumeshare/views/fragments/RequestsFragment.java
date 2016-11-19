package edu.brandeis.cs.jiahuiming.resumeshare.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ContactsAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.RequestAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Request;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.User;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.UserController;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class RequestsFragment extends Fragment {

    private ListView mListView;
    private UserController mUserController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mFragment = inflater.inflate(R.layout.fragment_requests, container, false);
        mListView=(ListView)mFragment.findViewById(R.id.lv_requests);
        RequestAdapter mRequestAdapter=new RequestAdapter(getActivity());
        mListView.setAdapter(mRequestAdapter);
//
        mUserController=new UserController(getActivity());
        mUserController.showRequests(mRequestAdapter);

//        for(int i=0;i<3;i++){
//            Request request=new Request();
//
//            request.setHostAccount("hostaccount");
//            request.setId(new Integer(i).toString());
//            request.setGuestAccount("Guestaccount");
//            request.setMessage("Message");
//            request.setHostImageId("ImageId");
//            request.setHostName("Name");
//
//            mRequestAdapter.putData(request);
//            mRequestAdapter.notifyDataSetChanged();
//        }

        return mFragment;
    }
}
