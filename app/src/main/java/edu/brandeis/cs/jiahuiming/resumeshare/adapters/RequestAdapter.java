package edu.brandeis.cs.jiahuiming.resumeshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Request;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.User;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.ContactController;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.UserController;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;
import edu.brandeis.cs.jiahuiming.resumeshare.views.fragments.RequestDetailFragment;
import edu.brandeis.cs.jiahuiming.resumeshare.views.fragments.ResumeFragment;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class RequestAdapter extends BaseAdapter {
    private List<User> mList;
    private List<Request> mRequestList;
    private LayoutInflater mInflater;
    private int position;
    private Context context;
    public RequestAdapter(Context context){
        this.mList=new ArrayList<User>();
        this.context=context;
        mInflater=LayoutInflater.from(context);
    }
    public void putUserData(User user){
        this.mList.add(user);
        notifyDataSetChanged();
    }

    public void putRequest(Request request){
        this.mRequestList.add(request);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRequestList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final int id=position;
        final RequestAdapter.ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new RequestAdapter.ViewHolder();
            convertView=mInflater.inflate(R.layout.list_item_contact,null);
            viewHolder.imageView=(ImageView) convertView.findViewById(R.id.tv_image);
            viewHolder.name=(TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.account=(TextView)convertView.findViewById(R.id.tv_account);
            viewHolder.message=(TextView)convertView.findViewById(R.id.tv_message);
            viewHolder.mAccept=(Button)convertView.findViewById(R.id.btn_accept);
            viewHolder.mRefuse=(Button)convertView.findViewById(R.id.btn_refuse);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(RequestAdapter.ViewHolder) convertView.getTag();

        }
        viewHolder.message.setVisibility(View.GONE);
        viewHolder.mAccept.setVisibility(View.GONE);
        viewHolder.mRefuse.setVisibility(View.GONE);

        User user=mList.get(id);
        viewHolder.imageView.setImageResource(R.drawable.kellyisgenius);
        viewHolder.name.setText(user.getFirstName()+" "+user.getSecondName());
        viewHolder.account.setText(user.getAccount());
        viewHolder.message.setText(mRequestList.get(id).getMessage());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.message.setVisibility(View.VISIBLE);
                viewHolder.mAccept.setVisibility(View.VISIBLE);
                viewHolder.mRefuse.setVisibility(View.VISIBLE);
               // ((HomeActivity)context).setRequestAccount(mList.get(position).getAccount());
                //((HomeActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new RequestDetailFragment()).commit();
            }
        });
        viewHolder.mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserController userController=new UserController(context);
                userController.addContact(((HomeActivity)context).getCurrentUser(),mList.get(id).getAccount());
            }
        });
        viewHolder.mRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserController userController=new UserController(context);
                userController.refuseRequest(mRequestList.get(id).getId());
            }
        });
        return convertView;
    }

    class ViewHolder{
        public ImageView imageView;
        public TextView name;
        public TextView account;
        public TextView message;
        public Button mAccept;
        public Button mRefuse;
    }
}
