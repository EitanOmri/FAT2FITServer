package com.fat2fit.model;

public class TopNMapping {
    String username;
    int totalCal;

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

    public int getTotalCal() {
        return totalCal;
    }

    public void setTotalCal(int totalCal) {
        this.totalCal = totalCal;
    }

    public TopNMapping(String username, int totalCal) {
        this.username = username;
        this.totalCal = totalCal;
    }
}
