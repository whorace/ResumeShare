package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.brandeis.cs.jiahuiming.resumeshare.adapters.RequestAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.Request;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.HttpTask;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class RequestModel { private Context context;
    private String result;

    public RequestModel(Context context) {
        this.context = context;
    }

    public void addRequestToRemote(String hostaccount,String guestaccount,String hostname,String imageid,String message) {
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    Toast.makeText(context,"Invitation has been send",Toast.LENGTH_SHORT).show();
                    Log.d("addRequestToRemote",result);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","addRequest","hostaccount="+hostaccount+"&guestaccount="+guestaccount+"&message="+message+"&imageid="+imageid+"&hostname="+hostname);
    }

    public void loadRequestsfromRemote(String account,final RequestAdapter requestAdapter){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    Request request=new Request();

                    String result_id;
                    String result_hostaccount;
                    String result_guestaccount;
                    String result_message;
                    String result_hostname;
                    String result_imageid;


                    JSONArray ja=new JSONArray(json);

                    for(int i =0; i<ja.length(); i++){
                        JSONObject jo=(JSONObject)ja.get(i);

                        result_id=jo.getString("id");
                        result_hostaccount=jo.getString("hostaccount");
                        result_guestaccount=jo.getString("guestaccount");
                        result_message=jo.getString("message");
                        result_hostname=jo.getString("hostname");
                        result_imageid=jo.getString("imageid");



                        request=new Request();

                        request.setId(result_id);
                        request.setHostAccount(result_hostaccount);
                        request.setGuestAccount(result_guestaccount);
                        request.setMessage(result_message);
                        request.setHostName(result_hostname);
                        request.setHostImageId(result_imageid);


                        requestAdapter.putData(request);


                    }



                    requestAdapter.notifyDataSetChanged();
                    Log.d("loadRequestsfromRemote",result);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","showRequests","guestaccount="+account);

    }

    public void delRequestOnRemote(String id){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    Log.d("loadRequestsfromRemote",result);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","delRequest","id="+id);
    }
}
