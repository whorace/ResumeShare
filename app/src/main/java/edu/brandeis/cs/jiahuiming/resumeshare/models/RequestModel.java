package edu.brandeis.cs.jiahuiming.resumeshare.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import edu.brandeis.cs.jiahuiming.resumeshare.adapters.ContactsAdapter;
import edu.brandeis.cs.jiahuiming.resumeshare.beans.User;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.DBOpenHelper;
import edu.brandeis.cs.jiahuiming.resumeshare.utils.HttpTask;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class RequestModel { private Context context;
    private String result;

    public RequestModel(Context context) {
        this.context = context;
    }

    public void addRequestToRemote(String hostaccount,String guestaccount,String message) {
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    User user=new User();
                    user.setFirstName(result);
                    user.setSecondName(result);
                    user.setAccount(result);
                    Log.d("addRequestToRemote",result);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","addRequest","hostaccount="+hostaccount+"&guestaccount="+guestaccount+"&message="+message);
    }
}
