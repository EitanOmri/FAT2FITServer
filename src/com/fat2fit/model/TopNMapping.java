package com.fat2fit.model;

/**
 * The type Top n mapping.
 */
public class TopNMapping {
    private String username;
    private long totalCal;

    /**
     * Instantiates a new Top n mapping.
     *
     * @param username the username
     * @param totalCal the total cal
     */
    public TopNMapping(String username, long totalCal) {
        setUsername(username);
        setTotalCal(totalCal);
    }

    /**
     * Instantiates a new Top n mapping.
     */
    public TopNMapping() {
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
     * Gets total cal.
     *
     * @return the total cal
     */
    public long getTotalCal() {
        return totalCal;
    }

    /**
     * Sets total cal.
     *
     * @param totalCal the total cal
     */
    public void setTotalCal(long totalCal) {
        this.totalCal = totalCal;
    }

    @Override
    public String toString() {
        return "TopNMapping{" +
                "username='" + username + '\'' +
                ", totalCal=" + totalCal +
                '}';
    }
}
