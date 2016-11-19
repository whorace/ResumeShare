package edu.brandeis.cs.jiahuiming.resumeshare.adapters;

import android.content.Context;
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
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;
import edu.brandeis.cs.jiahuiming.resumeshare.views.fragments.RequestDetailFragment;
import edu.brandeis.cs.jiahuiming.resumeshare.views.fragments.ResumeFragment;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class RequestAdapter extends BaseAdapter {
    private List<User> mList;
    private LayoutInflater mInflater;
    private int position;
    private Context context;
    public RequestAdapter(Context context){
        this.mList=new ArrayList<User>();
        this.context=context;
        mInflater=LayoutInflater.from(context);
    }
    public void putData(User user){
        this.mList.add(user);
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
        this.position=position;
        final RequestAdapter.ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new RequestAdapter.ViewHolder();
            convertView=mInflater.inflate(R.layout.list_item_contact,null);
            viewHolder.imageView=(ImageView) convertView.findViewById(R.id.tv_image);
            viewHolder.name=(TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.account=(TextView)convertView.findViewById(R.id.tv_account);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(RequestAdapter.ViewHolder) convertView.getTag();

        }

        User user=mList.get(position);
        viewHolder.imageView.setImageResource(R.drawable.kellyisgenius);
        viewHolder.name.setText(user.getFirstName()+" "+user.getSecondName());
        viewHolder.account.setText(user.getAccount());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)context).setRequestAccount(mList.get(position).getAccount());
                ((HomeActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new RequestDetailFragment()).commit();
            }
        });
        return convertView;
    }

    class ViewHolder{
        public ImageView imageView;
        public TextView name;
        public TextView account;
    }
}
