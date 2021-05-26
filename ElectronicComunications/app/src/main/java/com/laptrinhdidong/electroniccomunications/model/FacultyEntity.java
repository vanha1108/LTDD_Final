package com.laptrinhdidong.electroniccomunications.model;

import java.io.Serializable;

public class FacultyEntity implements Serializable {
    private int id;
    private String facultyName;
    private String deanName;
    private String addressDean;
    private String assistantName;
    private String addressAssistant;



    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getDeanName() {
        return deanName;
    }

    public void setDeanName(String deanName) {
        this.deanName = deanName;
    }

    public String getAddressDean() {
        return addressDean;
    }

    public void setAddressDean(String addressDean) {
        this.addressDean = addressDean;
    }

    public String getAssistantName() {
        return assistantName;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    }

    public String getAddressAssistant() {
        return addressAssistant;
    }

    public void setAddressAssistant(String addressAssistant) {
        this.addressAssistant = addressAssistant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}