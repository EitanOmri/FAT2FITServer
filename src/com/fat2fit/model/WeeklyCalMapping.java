package com.fat2fit.model;

import java.util.Date;

public class WeeklyCalMapping {
    private Date date;
    private Long cal;

    public WeeklyCalMapping(Date date, Long cal) {
        setDate(date);
        setCal(cal);
    }

    public WeeklyCalMapping() {super(); }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCal() {
        return cal;
    }

    public void setCal(Long cal) {
        this.cal = cal;
    }

    @Override
    public String toString() {
        return "WeeklyCalMapping{" +
                "date=" + date +
                ", cal=" + cal +
                '}';
    }
}
