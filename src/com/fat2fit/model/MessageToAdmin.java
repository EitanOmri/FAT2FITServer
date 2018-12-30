package com.fat2fit.model;

import java.util.Date;

/**
 * The type Message to admin.
 */
public class MessageToAdmin {
    private int id;
    private Date date;
    private String content;
    private String username;

    /**
     * Instantiates a new Message to admin.
     *
     * @param id       the id
     * @param date     the date
     * @param content  the content
     * @param username the username
     */
    public MessageToAdmin(int id, Date date, String content, String username) {
        setId(id);
        setDate(date);
        setContent(content);
        setUsername(username);
    }

    /**
     * Instantiates a new Message to admin.
     */
    public MessageToAdmin() {
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

    @Override
    public String toString() {
        return "MessageToAdmin{" +
                "id=" + id +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
