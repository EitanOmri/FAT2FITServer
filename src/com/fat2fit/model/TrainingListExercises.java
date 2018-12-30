package com.fat2fit.model;

/**
 * The type Training list exercises.
 */
public class TrainingListExercises {
    private int id;
    private int idTraining;
    private int idExercise;
    private int sets;
    private int reps;

    /**
     * Instantiates a new Training list exercises.
     *
     * @param id         the id
     * @param idTraining the id training
     * @param idExercise the id exercise
     * @param sets       the sets
     * @param reps       the reps
     */
    public TrainingListExercises(int id, int idTraining, int idExercise, int sets, int reps) {
        setId(id);
        setIdTraining(idTraining);
        setIdExercise(idExercise);
        setSets(sets);
        setReps(reps);
    }

    /**
     * Instantiates a new Training list exercises.
     */
    public TrainingListExercises() {
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
        this.id = id;
    }

    /**
     * Gets id training.
     *
     * @return the id training
     */
    public int getIdTraining() {
        return idTraining;
    }

    /**
     * Sets id training.
     *
     * @param idTraining the id training
     */
    public void setIdTraining(int idTraining) {
        this.idTraining = idTraining;
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
        this.idExercise = idExercise;
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
        this.sets = sets;
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
        this.reps = reps;
    }

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
}
