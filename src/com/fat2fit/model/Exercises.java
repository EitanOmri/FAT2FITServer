package com.fat2fit.model;


import java.util.Objects;


/**
 * Mapping Exercises table to java object.
 */
public class Exercises {
    private int id;
    private String name;
    private int caloriesPerReps;
    private int categoryID;

    /**
     * Instantiates a new Exercises.
     *
     * @param id              the id
     * @param name            the name
     * @param caloriesPerReps the calories per reps
     * @param categoryID      the category id
     */
    public Exercises(int id, String name, int caloriesPerReps, int categoryID) {
        setId(id);
        setName(name);
        setCaloriesPerReps(caloriesPerReps);
        setCategoryID(categoryID);
    }

    /**
     * Empty constructor, needs for hibernate.
     */
    public Exercises() {
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Gets calories per reps.
     *
     * @return the calories per reps
     */
    public int getCaloriesPerReps() {
        return caloriesPerReps;
    }

    /**
     * Sets calories per reps.
     *
     * @param caloriesPerReps the calories per reps
     */
    public void setCaloriesPerReps(int caloriesPerReps) {
        if (caloriesPerReps > 0)
            this.caloriesPerReps = caloriesPerReps;
        else
            this.caloriesPerReps = 0;
    }

    /**
     * Gets category id.
     *
     * @return the category id
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * Sets category id.
     *
     * @param categoryID the category id
     */
    public void setCategoryID(int categoryID) {
        if (categoryID > 0)
            this.categoryID = categoryID;
        else
            this.categoryID = 0;
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
