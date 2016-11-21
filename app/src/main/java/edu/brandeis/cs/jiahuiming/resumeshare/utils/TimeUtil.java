package edu.brandeis.cs.jiahuiming.resumeshare.utils;
import android.util.Log;
/**
 * Created by Horace on 16/11/20.
 */
public class TimeUtil {
    private long timecurrentTimeMillis;
    public TimeUtil(){
        timecurrentTimeMillis = System.currentTimeMillis();
    }
    public long getTimecurrentTimeMillis() {
        return timecurrentTimeMillis;
    }
    public void displayTime(){
        Log.d("current time", "timecurrentTimeMillis" + timecurrentTimeMillis);
    }
}
