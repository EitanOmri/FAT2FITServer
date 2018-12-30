package com.fat2fit.model;

public interface ICategory {
    void addCategory(Category category) throws DBException;

    void deleteCategory(int id) throws DBException;

    Category getCategory(int id) throws DBException;

    Category[] getCategories() throws DBException;

    Category getCategoryByName(String name) throws DBException;
}
