package edu.brandeis.cs.jiahuiming.resumeshare.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import edu.brandeis.cs.jiahuiming.resumeshare.commons.Urls;

/**
 * Created by jiahuiming on 11/29/16.
 */

public class HttpUploadImageTask extends AsyncTask<String, Float, StringBuffer> {
    private static final String TAG = "HTTP_TASK";

    @Override
    protected StringBuffer doInBackground(String... params) {
        StringBuffer json=new StringBuffer();
        String imagePath=params[0];
        String fileName=params[1]+".jpg";
        Log.d("imagePath",imagePath);
        Log.d("fileName",fileName);
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(imagePath);
        if (!sourceFile.isFile()) {
            Log.e("uploadFile", "Source File not exist :"+imagePath);
        }
        else
        {
            try {
                Log.d("Test","fileName");
                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(Urls.HOST+"upload.php");
                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);
                dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                // Responses from the server (code and message)
                int serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();
                Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);
                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

                json=new StringBuffer();
                BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String temp;
                while((temp=reader.readLine())!=null)
                {json.append(temp);}

            } catch (MalformedURLException ex) {
                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } // End else block
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

    public static interface HttpUploadImageTaskHandler {
        void taskSuccessful(String json);
        void taskFailed();
    }

    HttpUploadImageTask.HttpUploadImageTaskHandler taskHandler;
    public void setTaskHandler(HttpUploadImageTask.HttpUploadImageTaskHandler taskHandler) {
        this.taskHandler = taskHandler;
    }
}
