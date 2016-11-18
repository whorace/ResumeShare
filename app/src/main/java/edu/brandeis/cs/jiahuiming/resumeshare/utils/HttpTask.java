package edu.brandeis.cs.jiahuiming.resumeshare.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import edu.brandeis.cs.jiahuiming.resumeshare.commons.Urls;

/**
 * Created by jiahuiming on 11/17/16.
 */

public class HttpTask extends AsyncTask<String, Integer, StringBuffer> {
    private static final String TAG = "HTTP_TASK";

    @Override
    protected StringBuffer doInBackground(String... params) {
        StringBuffer json=new StringBuffer();
        String url=Urls.HOST+params[0]+"&method="+ params[1]+"&"+params[2];
        try{
            URL httpUrl=new URL(url);
            try {
                HttpURLConnection conn=(HttpURLConnection)httpUrl.openConnection();
                conn.setReadTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                json=new StringBuffer();
                BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String temp;
                while((temp=reader.readLine())!=null)
                {json.append(temp);}

            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(StringBuffer jsonbuffer) {
        // Done on UI Thread
        String json=jsonbuffer.toString();
        if (json != null && json != "") {
            Log.d(TAG, "taskSuccessful");
            int i1 = json.indexOf("["), i2 = json.indexOf("{"), i = i1 > -1
                    && i1 < i2 ? i1 : i2;
            if (i > -1) {
                json = json.substring(i);
                taskHandler.taskSuccessful(json);
            } else {
                Log.d(TAG, "taskFailed");
                taskHandler.taskFailed();
            }
        } else {
            Log.d(TAG, "taskFailed");
            taskHandler.taskFailed();
        }
    }

    public static interface HttpTaskHandler {
        void taskSuccessful(String json);
        void taskFailed();
    }

    HttpTaskHandler taskHandler;
    public void setTaskHandler(HttpTaskHandler taskHandler) {
        this.taskHandler = taskHandler;
    }
}
