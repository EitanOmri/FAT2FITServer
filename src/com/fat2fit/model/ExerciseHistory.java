package com.fat2fit.model;

import java.util.Date;

/**
 * The type Exercise history.
 */
public class ExerciseHistory {
    private String username;
    private int idExercise;
    private int sets;
    private int reps;
    private Date date;
    private int id;

    /**
     * Instantiates a new Exercise history.
     *
     * @param username   the username
     * @param idExercise the id exercise
     * @param sets       the sets
     * @param reps       the reps
     * @param date       the date
     * @param id         the id
     */
    public ExerciseHistory(String username, int idExercise, int sets, int reps, Date date, int id) {
        setUsername(username);
        setIdExercise(idExercise);
        setSets(sets);
        setReps(reps);
        setDate(date);
        setId(id);
    }

    /**
     * Instantiates a new Exercise history.
     */
    public ExerciseHistory() {
        super();
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

    /**
     * Gets id exercise.
     *
     * @return the id exercise
     */
    public int getIdExercise() {
        return idExercise;
    }

    /**
     * Sets id exercise.
     *
     * @param idExercise the id exercise
     */
    public void setIdExercise(int idExercise) {
        if (idExercise > 0)
            this.idExercise = idExercise;
        else
            this.idExercise = 0;
    }

    /**
     * Gets sets.
     *
     * @return the sets
     */
    public int getSets() {
        return sets;
    }

    /**
     * Sets sets.
     *
     * @param sets the sets
     */
    public void setSets(int sets) {

        if (sets > 0)
            this.sets = sets;
        else
            this.sets = 0;
    }

    /**
     * Gets reps.
     *
     * @return the reps
     */
    public int getReps() {
        return reps;
    }

    /**
     * Sets reps.
     *
     * @param reps the reps
     */
    public void setReps(int reps) {
        if (reps > 0)
            this.reps = reps;
        else this.reps = 0;
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

    @Override
    public String toString() {
        return "ExerciseHistory{" +
                "username='" + username + '\'' +
                ", idExercise=" + idExercise +
                ", sets=" + sets +
                ", reps=" + reps +
                ", date=" + date +
                ", id=" + id +
                '}';
    }

}
