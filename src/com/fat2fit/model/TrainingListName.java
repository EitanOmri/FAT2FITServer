package com.fat2fit.model;

/**
 * Mapping Training list name table to java object.
 */
public class TrainingListName {
    private int id;
    private String name;

    /**
     * Instantiates a new Training list name.
     *
     * @param id   the id
     * @param name the name
     */
    public TrainingListName(int id, String name) {
        setId(id);
        setName(name);
    }

    /**
     * Empty constructor, needs for hibernate.
     */
    public TrainingListName() {
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

    @Override
    public String toString() {
        return "TrainingListName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
