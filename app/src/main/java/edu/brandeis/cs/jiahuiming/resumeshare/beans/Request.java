package edu.brandeis.cs.jiahuiming.resumeshare.beans;

import android.app.Notification;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class Request {
    private String Id;
    private String Message;
    private String HostName;

    public String getHostImageId() {
        return HostImageId;
    }

    public void setHostImageId(String hostImageId) {
        HostImageId = hostImageId;
    }

    public String getHostName() {
        return HostName;
    }

    public void setHostName(String hostName) {
        HostName = hostName;
    }

    private String HostImageId;

    public String getGuestAccount() {
        return GuestAccount;
    }

    public void setGuestAccount(String guestAccount) {
        GuestAccount = guestAccount;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getHostAccount() {
        return HostAccount;
    }

    public void setHostAccount(String hostAccount) {
        HostAccount = hostAccount;
    }

    private String HostAccount;
    private String GuestAccount;

}
