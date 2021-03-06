package com.fat2fit.model;

import java.util.Date;

/**
 * Mapping Message from admin table to java object.
 */
public class MessageFromAdmin {
    private int id;
    private String content;
    private Date date;

    /**
     * Instantiates a new Message from admin.
     *
     * @param id      the id
     * @param date    the date
     * @param content the content
     */
    public MessageFromAdmin(int id, Date date, String content) {
        setId(id);
        setDate(date);
        setContent(content);
    }

    /**
     * Empty constructor, needs for hibernate.
     */
    public MessageFromAdmin() {
        super();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {

        if (id > 0)
            this.id = id;
        else
            this.id = 0;

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
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageFromAdmin{" +
                "id=" + id +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }

}
