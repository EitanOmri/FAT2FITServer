package com.fat2fit.model;

public class CategoryMapping {
    private String categoryName;
    private long totalExercises;

    public CategoryMapping(String categoryName, long totalExercises) {
        this.categoryName = categoryName;
        this.totalExercises = totalExercises;
    }

    public CategoryMapping() {
        super();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getTotalExercises() {
        return totalExercises;
    }

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
