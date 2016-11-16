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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.views.dialogs.BaseDialog;
import edu.brandeis.cs.jiahuiming.resumeshare.views.widgets.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements DialogInterface.OnClickListener{

    private BaseDialog mBaseDialog;
    private BaseDialog mConfirmDialog;
    private CircleImageView civ_profile_image;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mFragment = inflater.inflate(R.layout.fragment_profile, container, false);
//        mTv_FirstName = (TextView) mFragment.findViewById(R.id.Tv_firstname);
//        mTv_SecondName = (TextView) mFragment.findViewById(R.id.Tv_secondname);


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

        civ_profile_image=(CircleImageView)mFragment.findViewById(R.id.civ_profile_image);
        civ_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseDialog.show();
            }
        });
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
