package edu.brandeis.cs.jiahuiming.resumeshare.models;

/**
 * Created by jiahuiming on 11/18/16.
 */


//InstantLocationModel.java
        import android.content.Context;
        import android.util.Log;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONObject;

        import edu.brandeis.cs.jiahuiming.resumeshare.adapters.RequestAdapter;
        import edu.brandeis.cs.jiahuiming.resumeshare.adapters.SearchResultAdapter;
        import edu.brandeis.cs.jiahuiming.resumeshare.beans.InstantLocation;
        import edu.brandeis.cs.jiahuiming.resumeshare.beans.Request;
        import edu.brandeis.cs.jiahuiming.resumeshare.beans.User;
        import edu.brandeis.cs.jiahuiming.resumeshare.utils.HttpTask;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class InstantLocationModel {
    private Context context;
    private String result;

    public InstantLocationModel(Context context) {
        this.context=context;
    }

    public void addInstantLocationToRemote(String account,String time,String longitude,String laititude) {
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    result=json;
                    Toast.makeText(context,"Invitation has been send",Toast.LENGTH_SHORT).show();
                    Log.d("addInstantLocToRemote",result);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void taskFailed() {
            }
        });
        task.execute("user","shareLocation","account="+account+"&time="+time+"&longitude="+longitude+"&laititude="+laititude);
    }

    public void loadRequestsfromRemote(String account,final SearchResultAdapter searchResultAdapter){
        HttpTask task = new HttpTask();
        task.setTaskHandler(new HttpTask.HttpTaskHandler(){
            public void taskSuccessful(String json) {
                try {
                    User user=new User();
                    JSONArray ja=new JSONArray(json);
                    for(int i =0; i<ja.length(); i++){
                        JSONObject jo=(JSONObject)ja.get(i);
                        user=new User();
                        user.setId(jo.getString("id"));
                        user.setAccount(jo.getString("account"));
                        user.setFirstName(jo.getString("firstname"));
                        user.setSecondName(jo.getString("secondname"));
                        searchResultAdapter.putData(user);
                    }
                    searchResultAdapter.notifyDataSetChanged();
                    Log.d("loadInsLocfromRemote",result);
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
}
