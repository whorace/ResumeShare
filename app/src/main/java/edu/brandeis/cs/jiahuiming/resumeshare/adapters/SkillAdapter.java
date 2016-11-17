package edu.brandeis.cs.jiahuiming.resumeshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Experience;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Skill;

/**
 * Created by jiahuiming on 11/10/16.
 */

public class SkillAdapter extends BaseAdapter {
    private List<Skill> mList;
    private Context context;
    private String account;
    private LayoutInflater mInflater;

    public SkillAdapter(Context context,String account,List<Skill> list) {
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
        SkillAdapter.ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new SkillAdapter.ViewHolder();
            convertView=mInflater.inflate(R.layout.list_item_skill,null);
            viewHolder.mSkill=(TextView) convertView.findViewById(R.id.tv_skill);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(SkillAdapter.ViewHolder) convertView.getTag();

        }

        Skill skill=mList.get(position);
        viewHolder.mSkill.setText(skill.getSkill());
        return convertView;
    }

    class ViewHolder{
        public TextView mSkill;
    }
}
