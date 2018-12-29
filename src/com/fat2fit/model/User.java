package com.fat2fit.model;

import java.util.Date;

public class User {
    private String username;
    private String firsName;
    private String lastName;
    private String email;
    private String password;
    private Date dateOfBirth;
    private double weight;
    private double height;
    private int isManager;

    public User(String username, String firsName, String lastName, String email, String password, Date dateOfBirth, double weight, double height, int isManager) {

        setUsername(username);
        setFirsName(firsName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setDateOfBirth(dateOfBirth);
        setWeight(weight);
        setHeight(height);
        setIsManager(isManager);
    }

    public User() {
        super();
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

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
    }

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
                ", isManager=" + (isManager == 1 ? "true" : "false") +
                '}';
    }
}
