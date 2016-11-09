package edu.brandeis.cs.jiahuiming.resumeshare.beans;

import java.io.File;
import java.util.List;

/**
 * Created by jiahuiming on 10/25/16.
 */
public class User {
    private String Account;
    private String Password;
    private String FirstName;
    private String SecondName;

    private List<String> contacts;

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }
}
