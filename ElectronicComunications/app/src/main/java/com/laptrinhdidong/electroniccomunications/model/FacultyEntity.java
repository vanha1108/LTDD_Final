package com.laptrinhdidong.electroniccomunications.model;

import java.io.Serializable;

public class FacultyEntity implements Serializable {
    private String facultyName;
    private String deanName;
    private String addressDean;
    private String assistantName;
    private String addressAssistant;
    private String key;

    public FacultyEntity() {
    }

    public FacultyEntity(String key, String facultyName, String deanName, String addressDean, String assistantName, String addressAssistant) {
        this.facultyName = facultyName;
        this.deanName = deanName;
        this.addressDean = addressDean;
        this.assistantName = assistantName;
        this.addressAssistant = addressAssistant;
        this.key = key;
    }

    public FacultyEntity(String facultyName, String deanName, String addressDean, String assistantName, String addressAssistant) {
        this.facultyName = facultyName;
        this.deanName = deanName;
        this.addressDean = addressDean;
        this.assistantName = assistantName;
        this.addressAssistant = addressAssistant;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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
}
