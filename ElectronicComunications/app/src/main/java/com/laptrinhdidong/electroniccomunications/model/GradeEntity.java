package com.laptrinhdidong.electroniccomunications.model;

import java.io.Serializable;

public class GradeEntity implements Serializable {
    private int id;
    private String subject;
    private  int halfScore;
    private int finalScore;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getHalfScore() {
        return halfScore;
    }

    public void setHalfScore(int halfScore) {
        this.halfScore = halfScore;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }
}
