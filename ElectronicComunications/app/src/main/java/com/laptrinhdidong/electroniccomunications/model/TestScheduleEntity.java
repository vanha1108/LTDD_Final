package com.laptrinhdidong.electroniccomunications.model;

import java.io.Serializable;
import java.util.Date;

public class TestScheduleEntity implements Serializable {
    private int id;
    private String subject;
    private int room;
    private Date date;

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

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
