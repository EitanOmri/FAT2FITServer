package com.fat2fit.model;


import java.util.Objects;


public class Exercises {
    private int id;
    private String name;
    private int caloriesPerReps;
    private int categoryID;

    public Exercises(int id, String name, int caloriesPerReps, int categoryID) {
       setId(id);
       setName(name);
       setCaloriesPerReps(caloriesPerReps);
       setCategoryID(categoryID);
    }

    public Exercises() {
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
    public int hashCode() {
        return Objects.hash(name, id, caloriesPerReps, categoryID);
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

}
