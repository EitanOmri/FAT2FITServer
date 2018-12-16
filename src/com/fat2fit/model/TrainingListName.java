package com.fat2fit.model;

public class TrainingListName {
    int id;
    String name;

    @Override
    public String toString() {
        return "TrainingListName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrainingListName() {super();
    }

    public TrainingListName(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
