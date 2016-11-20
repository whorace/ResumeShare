package edu.brandeis.cs.jiahuiming.resumeshare.beans;

/**
 * Created by jiahuiming on 11/18/16.
 */

public class InstantLocation {
    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
    public String getTime() {
        return Time;
    }
    public void setTime(String time) {
        Time = time;
    }
    private String Location;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    private String Id;


    private String Time;
    private String Account;

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }



}
