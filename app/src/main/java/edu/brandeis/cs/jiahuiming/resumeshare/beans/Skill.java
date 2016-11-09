package edu.brandeis.cs.jiahuiming.resumeshare.beans;

/**
 * Created by jiahuiming on 11/7/16.
 */

public class Skill {
    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public int getOrder() {
        return Order;
    }

    public void setOrder(int order) {
        Order = order;
    }

    private int Order;
    private String Account;
    private String Skill;
}
