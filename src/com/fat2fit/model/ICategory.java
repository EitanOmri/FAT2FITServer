package com.fat2fit.model;

public interface ICategory {
    Category getCategory (int id) throws DBException;
    Category [] getCategories () throws DBException;

}
