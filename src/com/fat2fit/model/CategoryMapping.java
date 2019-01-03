package com.fat2fit.model;

/**
 * Mapping the result of query about category statistics to java object.
 */
public class CategoryMapping {
    private String categoryName;
    private long totalExercises;

    /**
     * Instantiates a new Category mapping.
     *
     * @param categoryName   the category name
     * @param totalExercises the total exercises
     */
    public CategoryMapping(String categoryName, long totalExercises) {
        setCategoryName(categoryName);
        setTotalExercises(totalExercises);
    }

    /**
     * Empty constructor, needs for hibernate.
     */
    public CategoryMapping() {
        super();
    }

    /**
     * Gets category name.
     *
     * @return the category name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets category name.
     *
     * @param categoryName the category name
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Gets total exercises.
     *
     * @return the total exercises
     */
    public long getTotalExercises() {
        return totalExercises;
    }

    /**
     * Sets total exercises.
     *
     * @param totalExercises the total exercises
     */
    public void setTotalExercises(long totalExercises) {

        if (totalExercises > 0)
            this.totalExercises = totalExercises;
        else
            this.totalExercises = 0;
    }

    @Override
    public String toString() {
        return "CategoryMapping{" +
                "categoryName='" + categoryName + '\'' +
                ", totalExercises=" + totalExercises +
                '}';
    }
}
