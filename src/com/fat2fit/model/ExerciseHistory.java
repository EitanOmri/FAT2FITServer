package com.fat2fit.model;

import java.util.Date;

public class ExerciseHistory {
    private String username;
    private int idExercise;
    private int sets;
    private int reps;
    private Date date;
    private int id;

    public ExerciseHistory(String username, int idExercise, int sets, int reps, Date date, int id) {
        this.username = username;
        this.idExercise = idExercise;
        this.sets = sets;
        this.reps = reps;
        this.date = date;
        this.id = id;
    }

    public ExerciseHistory() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(int idExercise) {
        this.idExercise = idExercise;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
