package edu.brandeis.cs.jiahuiming.resumeshare.beans;

/**
 * Created by jiahuiming on 10/25/16.
 */
public class Experience {
    private String Account;
    private int Order;
    private String Company;
    private String Position;


    public int getOrder() {
        return Order;
    }

    public void setOrder(int order) {
        Order = order;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }


}
