package com.fat2fit.model;

public class TrainingListExercises {
    int id;
    int idTraining;
    int idExercise;
    int sets;
    int reps;

    @Override
    public String toString() {
        return "TrainingListExercises{" +
                "id=" + id +
                ", idTraining=" + idTraining +
                ", idExercise=" + idExercise +
                ", sets=" + sets +
                ", reps=" + reps +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(int idTraining) {
        this.idTraining = idTraining;
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

    public TrainingListExercises() {super();
    }

    public TrainingListExercises(int id, int idTraining, int idExercise, int sets, int reps) {
        this.id = id;
        this.idTraining = idTraining;
        this.idExercise = idExercise;
        this.sets = sets;
        this.reps = reps;
    }
}
