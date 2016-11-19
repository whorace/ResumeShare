package edu.brandeis.cs.jiahuiming.resumeshare.beans;

/**
 * Created by jiahuiming on 10/25/16.
 */
public class Education {
    private String Account;
    private String School;
    private String StartYear;
    private String EndYear;
    private String Degree;
    private String Major;

    private String Id;
    public String getId(){
        return this.Id;

    }
    public void setId(String Id){
        this.Id=Id;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getEndYear() {
        return EndYear;
    }

    public void setEndYear(String endYear) {
        EndYear = endYear;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getStartYear() {
        return StartYear;
    }

    public void setStartYear(String startYear) {
        StartYear = startYear;
    }
}
