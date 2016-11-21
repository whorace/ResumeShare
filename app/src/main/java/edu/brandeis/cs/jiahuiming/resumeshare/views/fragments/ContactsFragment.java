package edu.brandeis.cs.jiahuiming.resumeshare.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ContactsAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.User;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.UserController;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment{
    private UserController mUserController;
    private ContactsAdapter mContactsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View mFragment = inflater.inflate(R.layout.fragment_contact, container, false);
        mContactsAdapter=new ContactsAdapter(getActivity());
        ListView listView=(ListView)mFragment.findViewById(R.id.listView);
        listView.setAdapter(mContactsAdapter);
        mUserController=new UserController(getActivity());
        mUserController.showContacts(mContactsAdapter);
        return mFragment;
    }
}