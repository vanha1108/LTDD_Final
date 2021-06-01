package com.laptrinhdidong.electroniccomunications.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Classes implements Serializable {
    private Integer classID;
    private String className;
    private Integer Nos;

    public Integer getClassID() {
        return classID;
    }

    public String getClassName() {
        return className;
    }

    public Integer getNos() {
        return Nos;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setNos(Integer nos) {
        Nos = nos;
    }

    public Classes(Integer classID, String className, Integer nos) {
        this.classID = classID;
        this.className = className;
        Nos = nos;
    }

    public Classes() {
    }
}
