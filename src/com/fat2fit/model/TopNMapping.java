package com.fat2fit.model;

public class TopNMapping {
    private String username;
    private long totalCal;

    public TopNMapping(String username, long totalCal) {
        setUsername(username);
        setTotalCal(totalCal);
    }

    public TopNMapping() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTotalCal() {
        return totalCal;
    }

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
