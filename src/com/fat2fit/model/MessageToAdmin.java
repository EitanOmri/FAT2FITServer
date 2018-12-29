package com.fat2fit.model;

import java.util.Date;

public class MessageToAdmin {
    private int id;
    private Date date;
    private String content;
    private String username;

    public MessageToAdmin(int id, Date date, String content, String username) {
        setId(id);
        setDate(date);
        setContent(content);
        setUsername(username);
    }

    public MessageToAdmin() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

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
