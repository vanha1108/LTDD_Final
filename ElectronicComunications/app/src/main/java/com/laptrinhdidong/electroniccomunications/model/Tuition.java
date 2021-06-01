package com.laptrinhdidong.electroniccomunications.model;

import java.io.Serializable;

public class Tuition implements Serializable {
    String subject;
    Integer NoC, price;

    public Tuition(String subject, Integer noC, Integer price) {
        this.subject = subject;
        NoC = noC;
        this.price = price;
    }

    public Tuition() {
    }

    public String getSubject() {
        return subject;
    }

    public Integer getNoC() {
        return NoC;
    }

    public Integer getPrice() {
        return price;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setNoC(Integer noC) {
        NoC = noC;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
