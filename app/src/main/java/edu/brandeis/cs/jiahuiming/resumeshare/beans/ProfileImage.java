package edu.brandeis.cs.jiahuiming.resumeshare.beans;

/**
 * Created by jiahuiming on 11/3/16.
 */
public class ProfileImage {
    private String Account;
    private String ScourceId;

    private String Id;
    public String getId(){
        return this.Id;

    }
    public void setId(String Id){
        this.Id=Id;
    }



    public String getScourceId() {
        return ScourceId;
    }

    public void setScourceId(String scourceId) {
        this.ScourceId = scourceId;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }
}
