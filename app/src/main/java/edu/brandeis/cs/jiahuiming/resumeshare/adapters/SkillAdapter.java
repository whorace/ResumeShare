package edu.brandeis.cs.jiahuiming.resumeshare.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Experience;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Skill;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.User;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.EditDialog;

/**
 * Created by jiahuiming on 11/10/16.
 */

public class SkillAdapter extends BaseAdapter {
    private List<Skill> mList;
    private Context context;
    private int editmode;
    private EditDialog mEditDialog;
    private LayoutInflater mInflater;

    public SkillAdapter(Context context,int editmode) {
        this.mList=new ArrayList<Skill>();
        this.editmode=editmode;
        this.context = context;
        mEditDialog=new EditDialog(context);
        mEditDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        this.mInflater= LayoutInflater.from(context);
    }

    public void putData(Skill skill){
        this.mList.add(skill);
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

        if(editmode==1){
            viewHolder.mSkill.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mEditDialog.setTitle("Edit Skill Info");
                    mEditDialog.setEditTextHint(mList.get(id).getSkill());
                    mEditDialog.setButton1("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context,"success to save",Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });
                    mEditDialog.show();
                    return false;
                }
            });

        }
        return convertView;
    }

    class ViewHolder{
        public TextView mSkill;
    }
}
