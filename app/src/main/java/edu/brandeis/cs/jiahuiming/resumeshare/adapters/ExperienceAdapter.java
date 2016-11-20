package edu.brandeis.cs.jiahuiming.resumeshare.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Experience;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.UserController;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.BaseDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.DatePickerDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.EditDialog;

/**
 * Created by jiahuiming on 10/25/16.
 */
public class ExperienceAdapter extends BaseAdapter {
    private List<Experience> mList;
    private Context context;
    private String account;
    private BaseDialog mBaseDialog;
    private EditDialog mEditDialog;
    private int editmode;
    private LayoutInflater mInflater;

    public ExperienceAdapter(Context context,int editmode) {
        this.mList=new ArrayList<>();
        this.context = context;
        this.editmode=editmode;
        this.mInflater= LayoutInflater.from(context);
        mBaseDialog=new BaseDialog(context);
        mBaseDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        mEditDialog=new EditDialog(context);
        mEditDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    public void putData(Experience experience){
        this.mList.add(experience);
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
    public View getView(int position, View convertView,final ViewGroup parent) {

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

            final Experience experience=mList.get(position);
            viewHolder.mCompany.setText(experience.getCompany());
            viewHolder.mPosition.setText(experience.getPosition());

        if(editmode==1){
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mBaseDialog.setTitle("Warning");
                    mBaseDialog.setMessage("Do you sure to delete this record?");
                    mBaseDialog.setButton1("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            UserController userController=new UserController(context);
                            userController.delExperience(experience,(ListView)parent);
                            mList.remove(id);
                            notifyDataSetChanged();
                            dialog.cancel();
                        }
                    });
                    mBaseDialog.show();
                    return false;
                }
            });


            viewHolder.mCompany.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditDialog.setTitle("Edit Company Info");
                    mEditDialog.setEditTextHint(mList.get(id).getCompany());
                    mEditDialog.setButton1("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            experience.setCompany(mEditDialog.getEditText().trim());
                            UserController userController=new UserController(context);
                            userController.modifyExperience(experience);
                            mList.set(id,experience);
                            notifyDataSetChanged();
                            dialog.cancel();
                        }
                    });
                    mEditDialog.show();
                }
            });

            viewHolder.mPosition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditDialog.setTitle("Edit Major Info");
                    mEditDialog.setEditTextHint(mList.get(id).getPosition());
                    mEditDialog.setButton1("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            experience.setPosition(mEditDialog.getEditText().trim());
                            UserController userController=new UserController(context);
                            userController.modifyExperience(experience);
                            mList.set(id,experience);
                            notifyDataSetChanged();
                            dialog.cancel();
                        }
                    });
                    mEditDialog.show();
                }
            });
        }

        return convertView;
    }

    class ViewHolder{
        public TextView mCompany;
        public TextView mPosition;
    }
}
