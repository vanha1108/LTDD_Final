package com.laptrinhdidong.electroniccomunications.model;

import java.util.Date;

public class TimeTableEntity {

    private String key;
    private String className;
    private String teacherName;
    private String subjectName;
    private String roomName;
    private String startLesson;
    private String numberLesson;
    private String date;

    public TimeTableEntity() {
    }

    public TimeTableEntity(String key, String className, String teacherName, String subjectName, String roomName, String startLesson, String numberLesson, String date) {
        this.key = key;
        this.className = className;
        this.teacherName = teacherName;
        this.subjectName = subjectName;
        this.roomName = roomName;
        this.startLesson = startLesson;
        this.numberLesson = numberLesson;
        this.date = date;
    }

    public TimeTableEntity(String className, String teacherName, String subjectName, String roomName, String startLesson, String numberLesson, String date) {
        this.className = className;
        this.teacherName = teacherName;
        this.subjectName = subjectName;
        this.roomName = roomName;
        this.startLesson = startLesson;
        this.numberLesson = numberLesson;
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStartLesson() {
        return startLesson;
    }

    public void setStartLesson(String startLesson) {
        this.startLesson = startLesson;
    }

    public String getNumberLesson() {
        return numberLesson;
    }

    public void setNumberLesson(String numberLesson) {
        this.numberLesson = numberLesson;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
