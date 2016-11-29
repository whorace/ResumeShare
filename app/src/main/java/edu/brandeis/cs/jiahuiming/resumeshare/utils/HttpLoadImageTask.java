package edu.brandeis.cs.jiahuiming.resumeshare.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import edu.brandeis.cs.jiahuiming.resumeshare.commons.Urls;
import edu.brandeis.cs.jiahuiming.resumeshare.views.widgets.CircleImageView;

/**
 * Created by jiahuiming on 11/11/16.
 */

public class HttpLoadImageTask extends AsyncTask<String, Float, Bitmap> {
    private Handler handler;

    @Override
    protected Bitmap doInBackground(String... params) {
        StringBuffer json=new StringBuffer();
        String url= Urls.HOST+"img/"+params[0]+".jpg";
        Log.d("url",url);
        try{
            URL httpUrl=new URL(url);
            try {
                HttpURLConnection conn=(HttpURLConnection)httpUrl.openConnection();
                conn.setReadTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                StringBuffer sb=new StringBuffer();
                InputStream in=conn.getInputStream();

                FileOutputStream out=null;
                File downloadFile=null;
                String fileName=String.valueOf(System.currentTimeMillis());

                File parent=Environment.getExternalStorageDirectory();
                downloadFile=new File(parent,fileName);
                out=new FileOutputStream(downloadFile);

                byte[] imageByte=new byte[2*1024];
                int len;
                if(out!=null){
                    while ((len=in.read(imageByte))!=-1){
                        out.write(imageByte,0,len);
                    }
                }
                Bitmap bitmap= BitmapFactory.decodeFile(downloadFile.getAbsolutePath());
                return bitmap;


            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static interface HttpLoadImageTaskHandler {
        void taskSuccessful(Bitmap bitmap);
        void taskFailed();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap!=null)
            taskHandler.taskSuccessful(bitmap);
        else{
            taskHandler.taskFailed();
        }
    }

    HttpLoadImageTask.HttpLoadImageTaskHandler taskHandler;
    public void setTaskHandler(HttpLoadImageTask.HttpLoadImageTaskHandler taskHandler) {
        this.taskHandler = taskHandler;
    }
}