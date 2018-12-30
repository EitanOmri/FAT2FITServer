package com.fat2fit.model;

import java.util.Date;

/**
 * The type User.
 */
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

    /**
     * Instantiates a new User.
     *
     * @param username    the username
     * @param firsName    the firs name
     * @param lastName    the last name
     * @param email       the email
     * @param password    the password
     * @param dateOfBirth the date of birth
     * @param weight      the weight
     * @param height      the height
     * @param isManager   the is manager
     */
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

    /**
     * Instantiates a new User.
     */
    public User() {
        super();
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets firs name.
     *
     * @return the firs name
     */
    public String getFirsName() {
        return firsName;
    }

    /**
     * Sets firs name.
     *
     * @param firsName the firs name
     */
    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Gets is manager.
     *
     * @return the is manager
     */
    public int getIsManager() {
        return isManager;
    }

    /**
     * Sets is manager.
     *
     * @param isManager the is manager
     */
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
