package com.fat2fit.model;

/**
 * The type Category mapping.
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
     * Instantiates a new Category mapping.
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
        this.totalExercises = totalExercises;
    }

    @Override
    public String toString() {
        return "CategoryMapping{" +
                "categoryName='" + categoryName + '\'' +
                ", totalExercises=" + totalExercises +
                '}';
    }
}
