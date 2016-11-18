package edu.brandeis.cs.jiahuiming.resumeshare.adapters;

import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.ContactList;

/**
 * Created by jiahuiming on 10/25/16.
 */
import android.widget.Button;
import android.widget.Toast;

import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;
import edu.brandeis.cs.jiahuiming.resumeshare.views.fragments.ResumeFragment;


/**
 * Created by jiahuiming on 10/25/16.
 */
public class ContactsAdapter extends BaseAdapter {
    private List<ContactList> mList;
    private LayoutInflater mInflater;
    private int position;
    private Context context;
    public ContactsAdapter(Context context, List<ContactList> list){
        mList=list;
        this.context=context;
        mInflater=LayoutInflater.from(context);

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
        final ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.list_item_contact,null);
            viewHolder.imageView=(ImageView) convertView.findViewById(R.id.tv_image);
            viewHolder.name=(TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.account=(TextView)convertView.findViewById(R.id.tv_account);
            convertView.setTag(viewHolder);
        }else{

            viewHolder=(ViewHolder) convertView.getTag();

        }

        ContactList bean=mList.get(position);
        viewHolder.imageView.setImageResource(bean.getImageId());
        viewHolder.name.setText(bean.getName());
        viewHolder.account.setText(bean.getAccount());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)context).setResumeAccount(mList.get(position).getAccount());
                ((HomeActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new ResumeFragment()).commit();
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