package edu.brandeis.cs.jiahuiming.resumeshare.beans;

/**
 * Created by jiahuiming on 11/7/16.
 */

public class Contact {
    private String Id;
    public String getId()
    {
        return this.Id;

    }
    public void setId(String Id){
        this.Id=Id;
    }
    private String HostAccount;

    public String getGuestAccount() {
        return GuestAccount;
    }

    public void setGuestAccount(String guestAccount) {
        GuestAccount = guestAccount;
    }

    public String getHostAccount() {
        return HostAccount;
    }

    public void setHostAccount(String hostAccount) {
        HostAccount = hostAccount;
    }

    private String GuestAccount;

}
