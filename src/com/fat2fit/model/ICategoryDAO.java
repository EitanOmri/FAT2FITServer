package com.fat2fit.model;

/**
 * The interface Category.
 */
public interface ICategoryDAO {
    /**
     * Add category.
     *
     * @param category the category
     * @throws DBException the db exception
     */
    void addCategory(Category category) throws DBException;

    /**
     * Delete category.
     *
     * @param id the id
     * @throws DBException the db exception
     */
    void deleteCategory(int id) throws DBException;

    /**
     * Gets category.
     *
     * @param id the id
     * @return the category
     * @throws DBException the db exception
     */
    Category getCategory(int id) throws DBException;

    /**
     * Get all categories.
     *
     * @return the category [ ]
     * @throws DBException the db exception
     */
    Category[] getCategories() throws DBException;

    /**
     * Gets category by name.
     *
     * @param name the name
     * @return the category by name
     * @throws DBException the db exception
     */
    Category getCategoryByName(String name) throws DBException;
}
