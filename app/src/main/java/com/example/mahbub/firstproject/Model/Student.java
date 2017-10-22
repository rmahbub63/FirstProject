package com.example.mahbub.firstproject.Model;

/**
 * Created by MAHBUB on 13-Oct-17.
 */

public class Student {

    private String name;
    private String password;
    private String id;
    private String email;
    private String phone;
    private String gender;
    private double cgpa;

    public Student(){

    }

//    public Student(String name, String email){
//        this.name = name;
//        this.email = email;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "Name: "+ name+"\n"+
                "Email: "+email;
    }
}
