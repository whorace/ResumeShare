package edu.brandeis.cs.jiahuiming.resumeshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Experience;

/**
 * Created by jiahuiming on 10/25/16.
 */
public class ExperienceAdapter extends BaseAdapter {
    private List<Experience> mList;
    private Context context;
    private String account;
    private LayoutInflater mInflater;

    public ExperienceAdapter(Context context,String account,List<Experience> list) {
        this.mList=list;
        this.account = account;
        this.context = context;
        this.mInflater= LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {


            final int id=position;
            ExperienceAdapter.ViewHolder viewHolder;
            if(convertView==null){
                viewHolder=new ExperienceAdapter.ViewHolder();
                convertView=mInflater.inflate(R.layout.list_item_experience,null);
                viewHolder.mCompany=(TextView) convertView.findViewById(R.id.tv_company);
                viewHolder.mPosition=(TextView)convertView.findViewById(R.id.tv_position);
                convertView.setTag(viewHolder);
            }else{
                viewHolder=(ExperienceAdapter.ViewHolder) convertView.getTag();

            }

            Experience experience=mList.get(position);
            viewHolder.mCompany.setText(experience.getCompany());
            viewHolder.mPosition.setText(experience.getPosition());
            return convertView;
    }

    class ViewHolder{
        public TextView mCompany;
        public TextView mPosition;
    }
}
