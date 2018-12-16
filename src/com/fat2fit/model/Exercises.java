package com.fat2fit.model;

import java.util.Objects;

public class Exercises {
    int id;
    String name;
    int caloriesPerReps;
    int categoryID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercises exercises = (Exercises) o;
        return id == exercises.id &&
                caloriesPerReps == exercises.caloriesPerReps &&
                categoryID == exercises.categoryID &&
                Objects.equals(name, exercises.name);
    }


    @Override
    public String toString() {
        return "Exercises{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", caloriesPerReps=" + caloriesPerReps +
                ", categoryID=" + categoryID +
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

    public int getCaloriesPerReps() {
        return caloriesPerReps;
    }

    public void setCaloriesPerReps(int caloriesPerReps) {
        this.caloriesPerReps = caloriesPerReps;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public Exercises() {super();
    }

    public Exercises(int id, String name, int caloriesPerReps, int categoryID) {
        this.id = id;
        this.name = name;
        this.caloriesPerReps = caloriesPerReps;
        this.categoryID = categoryID;
    }
}
