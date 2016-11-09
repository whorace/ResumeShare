package edu.brandeis.cs.jiahuiming.resumeshare.utils;

import android.content.Context;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkChecker {
	private Context context;
	ConnectivityManager connectivityManager;
	
	public NetworkChecker(Context context)
	{
		this.context = context;
		connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		}
	
	   public boolean isNetworkAvailable()
	    {

	        if (connectivityManager == null)
	        {
	            return false;
	        }
	        else
	        {
	            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
	            
	            if (networkInfo != null && networkInfo.length > 0)
	            {
	                for (int i = 0; i < networkInfo.length; i++)
	                {

	                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
	                    {
	                        return true;
	                    }
	                }
	            }
	        }
	        return false;
	    }
}
