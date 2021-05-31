package com.laptrinhdidong.electroniccomunications.model;

import java.io.Serializable;

public class SubjectEntity implements Serializable {

    private String facultyName;
    private String name;
    private String credit;
    private String moneyPerCredit;

    public SubjectEntity() {
    }

    public SubjectEntity(String name, String credit, String moneyPerCredit) {
        this.name = name;
        this.credit = credit;
        this.moneyPerCredit = moneyPerCredit;
    }

    public SubjectEntity(String facultyName, String name, String credit, String moneyPerCredit) {
        this.facultyName = facultyName;
        this.name = name;
        this.credit = credit;
        this.moneyPerCredit = moneyPerCredit;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getMoneyPerCredit() {
        return moneyPerCredit;
    }

    public void setMoneyPerCredit(String moneyPerCredit) {
        this.moneyPerCredit = moneyPerCredit;
    }
}
