package com.laptrinhdidong.electroniccomunications.model;

import java.io.Serializable;

public class Student implements Serializable {
    String studentCode, firstName, lastName, sex, age, phone, gmail, classID, facultyID, DoB;

    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setFacultyID(String facultyID) {
        this.facultyID = facultyID;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getStudentCode() { return  studentCode; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getGmail() {
        return gmail;
    }

    public String getClassID() {
        return classID;
    }

    public String getFacultyID() {
        return facultyID;
    }

    public String getDoB() {
        return DoB;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }



    public Student(String studentCode, String firstName, String lastName, String doB, String sex, String age, String phone, String gmail, String classID, String facultyID) {
        this.studentCode = studentCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.gmail = gmail;
        this.classID = classID;
        this.facultyID = facultyID;
        this.DoB = doB;
    }

    public Student() {
        // require for firebase
    }
}
