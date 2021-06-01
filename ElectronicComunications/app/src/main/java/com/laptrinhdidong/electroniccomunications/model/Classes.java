package com.laptrinhdidong.electroniccomunications.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Classes implements Serializable {
    private Integer classID;
    private String className;
    private Integer Nos;
    private String facultyName;

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

    public Classes(Integer classID, String className, Integer nos, String facultyName) {
        this.classID = classID;
        this.className = className;
        Nos = nos;
        this.facultyName = facultyName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Classes() {
    }
}
