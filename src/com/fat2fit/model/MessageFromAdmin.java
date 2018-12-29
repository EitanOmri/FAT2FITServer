package com.fat2fit.model;

import java.util.Date;

public class MessageFromAdmin {
    private int id;
    private String content;
    private Date date;

    public MessageFromAdmin(int id, Date date, String content) {
        setId(id);
        setDate(date);
        setContent(content);
    }

    public MessageFromAdmin() {
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

    @Override
    public String toString() {
        return "MessageFromAdmin{" +
                "id=" + id +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }

}
