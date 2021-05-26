package com.laptrinhdidong.electroniccomunications.model;

import java.io.Serializable;

public class ClassEntity implements Serializable {
    private int id;
    private String name;
    private int numberStudent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberStudent() {
        return numberStudent;
    }

    public void setNumberStudent(int numberStudent) {
        this.numberStudent = numberStudent;
    }
}
