package com.fat2fit.model;

import java.util.Date;

public class WeeklyCalMmaping {
    private Date date;
    private Long cal;

    @Override
    public String toString() {
        return "WeeklyCalMmaping{" +
                "date=" + date +
                ", cal=" + cal +
                '}';
    }

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

    public WeeklyCalMmaping() {
    }

    public WeeklyCalMmaping(Date date, Long cal) {
        this.date = date;
        this.cal = cal;
    }
}
