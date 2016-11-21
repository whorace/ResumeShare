package edu.brandeis.cs.jiahuiming.resumeshare.views.fragments;


import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ScrollingView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.EducationAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ExperienceAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.adapters.SkillAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Education;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Experience;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Skill;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.ContactController;
import edu.brandeis.cs.jiahuiming.resumeshare.controllers.UserController;
import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.AddEducationDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.AddExperienceDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.AddSkillDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.BaseDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.DoubleEditDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.widgets.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements DialogInterface.OnClickListener{

    private ScrollView mScrollView;
    private BaseDialog mBaseDialog;
    private BaseDialog mConfirmDialog;
    private CircleImageView civ_profile_image;

    private TextView mEmail;
    private TextView mName;

    private ListView mLv_Educations;
    private ListView mLv_Experiences;
    private ListView mLv_Skills;

    private UserController mUserController;

    private Button mAddEducation;
    private Button mAddExperience;
    private Button mAddSkill;

    private AddEducationDialog mAddEducationDialog;
    private AddExperienceDialog mExperienceDialog;
    private AddSkillDialog mAddSkillDialog;
    private DoubleEditDialog mDoubleEditdalog;
    private ExpandableListView mExpandableListView;

    private byte[] mContent;
    private String imagePath;
    private Bitmap mBitmap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View mFragment = inflater.inflate(R.layout.fragment_profile, container, false);
        mEmail = (TextView) mFragment.findViewById(R.id.tv_resume_account);
        mName = (TextView) mFragment.findViewById(R.id.tv_resume_name);

        mBaseDialog=new BaseDialog(getActivity());
        mBaseDialog.setTitle("Update Profile Image");
        mBaseDialog.setMessage("Select the way to up load image");

        mConfirmDialog=new BaseDialog(getActivity());
        mConfirmDialog.setTitle("Confirmation");
        mConfirmDialog.setMessage("Are you sure to modify your personal Information?");

        mBaseDialog.setButton1("Camera",this);
        mBaseDialog.setButton2("Library",this);
        mBaseDialog.setButton3("Cancel",this);

        mConfirmDialog.setButton1("Confirm",this);
        mConfirmDialog.setButton2("Cancel",this);

        mDoubleEditdalog=new DoubleEditDialog(getActivity());
        mDoubleEditdalog.setButton2("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        civ_profile_image=(CircleImageView)mFragment.findViewById(R.id.civ_profile_image);
        civ_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseDialog.show();
            }
        });

        mLv_Educations=(ListView)mFragment.findViewById(R.id.lv_education);
        mLv_Experiences=(ListView)mFragment.findViewById(R.id.lv_experience);
        mLv_Skills=(ListView)mFragment.findViewById(R.id.lv_skill);

        final EducationAdapter mEducationAdapter=new EducationAdapter(getActivity(),1);
        final ExperienceAdapter mExperienceAdapter=new ExperienceAdapter(getActivity(),1);
        final SkillAdapter mSkillAdapter=new SkillAdapter(getActivity(),1);

        mLv_Educations.setAdapter(mEducationAdapter);
        mLv_Experiences.setAdapter(mExperienceAdapter);
        mLv_Skills.setAdapter(mSkillAdapter);

        mAddEducation=(Button)mFragment.findViewById(R.id.btn_addeducation);
        mAddExperience=(Button)mFragment.findViewById(R.id.btn_addexperience);
        mAddSkill=(Button)mFragment.findViewById(R.id.btn_addskill);

        mUserController=new UserController(getActivity());
        mUserController.showResume(mEducationAdapter,mExperienceAdapter,mSkillAdapter,mLv_Educations,mLv_Experiences,mLv_Skills);
        mUserController.showInfo(mEmail,mName);

        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDoubleEditdalog.setTitle("Change Name");
                mDoubleEditdalog.setFirstNameHint("FirstName");
                mDoubleEditdalog.setSecondNameHint("SecondName");
                mDoubleEditdalog.setButton1("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mDoubleEditdalog.getFirstNameText()!=null&&mDoubleEditdalog.getSecondNameText()!=null)
                            mUserController=new UserController(getActivity());
                        mUserController.modifyName(mDoubleEditdalog.getFirstNameText().replace(" ","%20"),mDoubleEditdalog.getSecondNameText().replace(" ","%20"));
                    }
                });
                mDoubleEditdalog.show();

            }
        });

        mAddEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddEducationDialog=new AddEducationDialog(getActivity());
                mAddEducationDialog.setTitle("Add Education Record");
                mAddEducationDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
//                ((NumberPicker)mAddEducationDialog.getStartYearPicker()).setValue(2016);
//                ((NumberPicker)mAddEducationDialog.getEndYearPicker()).setValue(2016);
                mAddEducationDialog.setButton1("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mAddEducationDialog.getAddSchoolText()!=null&&mAddEducationDialog.getAddMajorText()!=null&&
                                mAddEducationDialog.getAddDegreeText()!=null){
                            Education education=new Education();
                            education.setSchool(mAddEducationDialog.getAddSchoolText());
                            education.setDegree(mAddEducationDialog.getAddDegreeText());
                            education.setMajor(mAddEducationDialog.getAddMajorText());
                            education.setStartYear(mAddEducationDialog.getAddStartText());
                            education.setEndYear(mAddEducationDialog.getAddEndYearText());
                            mUserController.addEducation(education,mEducationAdapter,mLv_Educations);
                            dialog.cancel();
                        }else{
                            Toast.makeText(getActivity(),"Content should not be empty",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                mAddEducationDialog.show();
            }
        });

        mAddExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExperienceDialog=new AddExperienceDialog(getActivity());
                mExperienceDialog.setTitle("Add Experience Record");
                mExperienceDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                mExperienceDialog.setButton1("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mExperienceDialog.getAddCompanyText()!=null&&mExperienceDialog.getAddPositionText()!=null){
                            Experience experience=new Experience();
                            experience.setCompany(mExperienceDialog.getAddCompanyText());
                            experience.setPosition(mExperienceDialog.getAddPositionText());
                            mUserController.addExperience(experience,mExperienceAdapter,mLv_Experiences);
                            dialog.cancel();
                        }else{

                        }

                    }
                });
                mExperienceDialog.show();
            }
        });

        mAddSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAddSkillDialog=new AddSkillDialog(getActivity());
                mAddSkillDialog.setTitle("Add Skill Record");
                mAddSkillDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                mAddSkillDialog.setButton1("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mAddSkillDialog.getAddSkillText()!=null){
                            Skill skill=new Skill();
                            skill.setSkill(mAddSkillDialog.getAddSkillText());
                            mUserController.addSkill(skill,mSkillAdapter,mLv_Skills);
                            dialog.cancel();
                        }else{
                            Toast.makeText(getActivity(),"Content should not be empty",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mAddSkillDialog.show();


                if(mAddSkillDialog.getAddSkillText()!=null){
                    mAddSkillDialog=new AddSkillDialog(getActivity());
                }else{
                    Toast.makeText(getActivity(),"Content should not be empty",Toast.LENGTH_SHORT).show();
                }

            }
        });

        ListUtils.setDynamicHeight(mLv_Educations);
        ListUtils.setDynamicHeight(mLv_Experiences);
        ListUtils.setDynamicHeight(mLv_Skills);

        mScrollView=(ScrollView) mFragment.findViewById(R.id.sv_profile);
        mScrollView.smoothScrollTo(0,0);

        return mFragment;
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
        if(dialog==mBaseDialog)
        {
            switch(which)
            {
                case 0:
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intent, 0);
                    dialog.cancel();
                    break;
                case 1:
                    Intent intent2 = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent2, 1);
                    dialog.cancel();
                    break;
                case 2:
                    dialog.cancel();
                    break;

            }
        }
        else if(dialog==mConfirmDialog)
        {
            switch(which)
            {
                case 0:
                    dialog.cancel();
                    break;
                case 1:
                    dialog.cancel();
                    break;

            }
        }
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            BaseAdapter mListAdapter = (BaseAdapter) mListView.getAdapter();
            if (mListAdapter == null) {
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }


    public void showDialog(int dialog){
        if(dialog==0){
            mBaseDialog.show();
        }


    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        ContentResolver resolver = getActivity().getContentResolver();
//        File file = new File("/sdcard/ShareResume/");
//        file.mkdirs();
//        if (requestCode == 1)
//        {
//            if(data.getData()!=null)
//            {
//                try {
//                    Uri originalUri = data.getData();
//                    imagePath = originalUri.toString();
//                    mContent = readStream(resolver. (Uri.parse(originalUri.toString())));
//                    // convert Imageview to Bitmap that can be used by programe
//                    mBitmap = getPicFromBytes(mContent, null);
//                    // check the statue of sd card
//                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
//                    {
//                        imagePath = "/sdcard/myImage/tempImage.jpg";
//                        FileOutputStream writeImage = new FileOutputStream(imagePath);
//                        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, writeImage);
//                        //write the Bitmap into filesystem
////                        uploadUserImage(imagePath);
//                        return;
//                    }
//                    else
//                    {
//                        Toast.makeText(getActivity(),"SD卡目前不可读",Toast.LENGTH_SHORT);
//                    }
//                }
//                catch (Exception e)
//                {
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
//        else if (requestCode == 0)
//        {
//            if(data.getExtras()!=null)
//            {
//                try
//                {
//                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
//                    {
//                        // to get the data return by camera and save it in the bitmap
//                        //we use the time of creating picture as the filename of the photo
//                        mBitmap = (Bitmap) data.getExtras().get("data");
//                        Date fileName= new Date();;
//                        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//                        imagePath = "/sdcard/myImage/" + format.format(fileName) + ".jpg";
//
//                        FileOutputStream writeImage = new FileOutputStream(imagePath);
//                        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, writeImage);
//                        uploadUserImage(imagePath);
//                    }
//                }
//                catch(Exception e)
//                {
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
//    }

//    public static Bitmap getPicFromBytes(byte[] bytes,BitmapFactory.Options opts)
//    {
//        if (bytes != null)
//            if (opts != null)
//                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,opts);
//            else
//                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//        return null;
//    }
//
//    public static byte[] readStream(InputStream inStream) throws Exception
//    {
//        byte[] buffer = new byte[1024];
//        int len = -1;
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        while ((len = inStream.read(buffer)) != -1)
//        {
//            outStream.write(buffer, 0, len);
//        }
//        byte[] data = outStream.toByteArray();
//        outStream.close();
//        inStream.close();
//        return data;
//    }

}
