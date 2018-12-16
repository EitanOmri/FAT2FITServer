package com.fat2fit.model;

public class TopNMapping {

    String username;
    long totalCal;

    @Override
    public String toString() {
        return "TopNMapping{" +
                "username='" + username + '\'' +
                ", totalCal=" + totalCal +
                '}';
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

    public TopNMapping(String username, long totalCal) {
        this.username = username;
        this.totalCal = totalCal;
    }
}
