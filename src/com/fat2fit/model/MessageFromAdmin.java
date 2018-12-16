package com.fat2fit.model;

import java.util.Date;

public class MessageFromAdmin {
    int id;
    String content;
    Date date;

    @Override
    public String toString() {
        return "MessageFromAdmin{" +
                "id=" + id +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
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

    public MessageFromAdmin() {
        super();
    }

    public MessageFromAdmin(int id, Date date, String content) {
        this.id = id;
        this.date = date;
        this.content = content;
    }

}
