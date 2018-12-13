package com.fat2fit.model;

import org.omg.DynamicAny.DynArray;

import javax.xml.soap.SAAJResult;
import java.util.Date;

public class User {
    String username;
    String firsName;
    String lastName;
    String email;
    String password;
    Date dateOfBirth;
    double weight;
    double height;
    boolean isManger;


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", weight=" + weight +
                ", height=" + height +
                ", isManger=" + isManger +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isManger() {
        return isManger;
    }

    public void setManger(boolean manger) {
        isManger = manger;
    }

    public User() {
        super();
    }

    public User(String username, String firsName, String lastName, String email, String password, Date dateOfBirth, double weight, double height, boolean isManger) {
        this.username = username;
        this.firsName = firsName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
        this.isManger = isManger;
    }
}
