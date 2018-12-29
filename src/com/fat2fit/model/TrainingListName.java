package com.fat2fit.model;

public class TrainingListName {
    private int id;
    private String name;

    public TrainingListName(int id, String name) {
        setId(id);
        setName(name);
    }

    public TrainingListName() {
        super();
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

    @Override
    public String toString() {
        return "TrainingListName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
