package com.fat2fit.model;

import java.util.Date;

/**
 * The type Weekly cal mapping.
 */
public class WeeklyCalMapping {
    private Date date;
    private Long cal;

    /**
     * Instantiates a new Weekly cal mapping.
     *
     * @param date the date
     * @param cal  the cal
     */
    public WeeklyCalMapping(Date date, Long cal) {
        setDate(date);
        setCal(cal);
    }

    /**
     * Instantiates a new Weekly cal mapping.
     */
    public WeeklyCalMapping() {
        super();
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets cal.
     *
     * @return the cal
     */
    public Long getCal() {
        return cal;
    }

    /**
     * Sets cal.
     *
     * @param cal the cal
     */
    public void setCal(Long cal) {
        if (cal > 0)
            this.cal = cal;
        else
            this.cal = (long) 0;
    }

    @Override
    public String toString() {
        return "WeeklyCalMapping{" +
                "date=" + date +
                ", cal=" + cal +
                '}';
    }
}
