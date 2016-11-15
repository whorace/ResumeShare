package edu.brandeis.cs.jiahuiming.resumeshare.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by jiahuiming on 11/13/16.
 */

public class HttpHelper {

    private String url;
    private String jsonResult;
    private StringBuffer stringBuffer;
    private Context context;

    public HttpHelper(String controller, String method,String parameter,Context context){
        this.url = "http://horacewang.com/ShareResumeBackEnd/?controller="+controller+"&method="+
                    method+"&"+parameter;
        this.context=context;
    }

    public String  doPost(){
        return jsonResult;

    }

    public String  doGet(){
//        Toast.makeText(context,url,Toast.LENGTH_LONG).show();
//        try{
//            URL httpUrl=new URL(url);
//            try {
//                HttpURLConnection conn=(HttpURLConnection)httpUrl.openConnection();
//                conn.setReadTimeout(5000);
//                conn.setRequestMethod("GET");
//                conn.setDoInput(true);
//                stringBuffer=new StringBuffer();
//                BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                String str;
//                while((str=reader.readLine())!=null){stringBuffer.append(str);}
//                jsonResult=stringBuffer.toString();
//
//
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        }
        new HttpThread(this.url,new Handler(),jsonResult).start();
        return jsonResult;
    }



    public class HttpThread extends Thread {
        private String  url;
        private Handler handler;
        private StringBuffer stringBuffer;
        private String json;

        public HttpThread(String url,Handler handler,String json){
            this.url=url;
            this.handler=handler;
            this.json=json;
        }

        @Override
        public void run() {
            try{
                URL httpUrl=new URL(url);
                try {
                    HttpURLConnection conn=(HttpURLConnection)httpUrl.openConnection();
                    conn.setReadTimeout(5000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    stringBuffer=new StringBuffer();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String str;
                    while((str=reader.readLine())!=null){stringBuffer.append(str);}
                    handler.post(new Runnable(){
                        @Override
                        public void run() {
                            json=stringBuffer.toString();
                        }
                    });

                }catch (IOException e){
                    e.printStackTrace();
                }
            }catch (MalformedURLException e){
                e.printStackTrace();
            }
        }
    }

}
