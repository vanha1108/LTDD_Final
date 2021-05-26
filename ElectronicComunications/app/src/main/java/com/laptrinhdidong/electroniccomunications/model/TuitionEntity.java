package com.laptrinhdidong.electroniccomunications.model;

import java.io.Serializable;

public class TuitionEntity implements Serializable {
    private int id;
    private String subjectName;
    private int numberCredit;
    private int price;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumberCredit() {
        return numberCredit;
    }

    public void setNumberCredit(int numberCredit) {
        this.numberCredit = numberCredit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
