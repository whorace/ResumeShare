package edu.brandeis.cs.jiahuiming.resumeshare.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.ContactList;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.BaseDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.DatePickerDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.EditDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.fragments.ResumeFragment;

/**
 * Created by jiahuiming on 10/25/16.
 */
public class EducationAdapter extends BaseAdapter {
    private List<Education> mList;
    private Context context;
    private String account;
    private int editmode;
    private LayoutInflater mInflater;
    private BaseDialog mBaseDialog;
    private EditDialog mEditDialog;
    private DatePickerDialog mDatePickerDialog;

    public EducationAdapter(Context context,String account,List<Education> list,int editmode) {
        this.mList=list;
        this.account = account;
        this.context = context;
        this.editmode=editmode;
        this.mInflater= LayoutInflater.from(context);
        mBaseDialog=new BaseDialog(context);
        mEditDialog=new EditDialog(context);
        mDatePickerDialog=new DatePickerDialog(context);
        mEditDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        mDatePickerDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        mDatePickerDialog.setTitle("Edit Time");
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
        EducationAdapter.ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new EducationAdapter.ViewHolder();
            convertView=mInflater.inflate(R.layout.list_item_education,null);
            viewHolder.mSchool=(TextView) convertView.findViewById(R.id.tv_school);
            viewHolder.mMajor=(TextView)convertView.findViewById(R.id.tv_major);
            viewHolder.mDegree=(TextView)convertView.findViewById(R.id.tv_degree);
            viewHolder.mStartYear=(TextView)convertView.findViewById(R.id.tv_startyear);
            viewHolder.mEndYear=(TextView)convertView.findViewById(R.id.tv_endyear);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(EducationAdapter.ViewHolder) convertView.getTag();

        }

        Education education=mList.get(position);
        viewHolder.mSchool.setText(education.getSchool());
        viewHolder.mMajor.setText(education.getMajor());
        viewHolder.mDegree.setText(education.getDegree());
        viewHolder.mStartYear.setText(education.getStartYear());
        viewHolder.mEndYear.setText(education.getEndYear());

        mDatePickerDialog.setmStartYear(education.getStartYear());
        mDatePickerDialog.setmFinishYear(education.getEndYear());
        mDatePickerDialog.setButton1("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"success to save",Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        if(editmode==1){
            viewHolder.mSchool.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mEditDialog.setTitle("Edit School Info");
                    mEditDialog.setEditTextHint(mList.get(id).getSchool());
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

            viewHolder.mMajor.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mEditDialog.setTitle("Edit Major Info");
                    mEditDialog.setEditTextHint(mList.get(id).getMajor());
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

            viewHolder.mDegree.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mEditDialog.setTitle("Edit Degree Info");
                    mEditDialog.setEditTextHint(mList.get(id).getDegree());
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

            viewHolder.mStartYear.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mDatePickerDialog.show();
                    return false;
                }
            });

            viewHolder.mEndYear.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mDatePickerDialog.show();
                    return false;
                }
            });
        }


        return convertView;
    }

    class ViewHolder{
        public TextView mSchool;
        public TextView mMajor;
        public TextView mDegree;
        public TextView mStartYear;
        public TextView mEndYear;
    }
}
