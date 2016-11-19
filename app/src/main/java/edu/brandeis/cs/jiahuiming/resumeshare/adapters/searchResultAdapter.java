package edu.brandeis.cs.jiahuiming.resumeshare.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.User;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.UserController;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.EditDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.fragments.ResumeFragment;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class SearchResultAdapter extends BaseAdapter {
    private EditDialog mEditDialog;
    private List<User> mList;
    private LayoutInflater mInflater;
    private int position;
    private Context context;

    public SearchResultAdapter(Context context){
        this.mList=new ArrayList<User>();
        this.context=context;
        mInflater=LayoutInflater.from(context);
    }
    public void putData(User user){
        this.mList.add(user);
        notifyDataSetChanged();
    }

    public void cleanData(){
        mList.clear();
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final int id=position;
        final SearchResultAdapter.ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new SearchResultAdapter.ViewHolder();
            convertView=mInflater.inflate(R.layout.list_item_contact,null);
            viewHolder.imageView=(ImageView) convertView.findViewById(R.id.tv_image);
            viewHolder.name=(TextView)convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else{

            viewHolder=(SearchResultAdapter.ViewHolder) convertView.getTag();

        }

        User user=mList.get(position);
        viewHolder.imageView.setImageResource(R.drawable.kellyisgenius);
        viewHolder.name.setText(user.getFirstName()+" "+user.getSecondName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditDialog=new EditDialog(context);
                mEditDialog.setTitle("Add Contact Request");
                mEditDialog.setEditTextHint("Say Something");
                mEditDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                mEditDialog.setButton1("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserController mUserController=new UserController(context);
                        mUserController.sendRequest(mList.get(id).getAccount(),mEditDialog.getEditText());
                        dialog.cancel();
                    }
                });

                mEditDialog.show();
            }
        });

        return convertView;
    }

    class ViewHolder{
        public ImageView imageView;
        public TextView name;
    }
}