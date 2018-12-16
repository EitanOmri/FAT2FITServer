package com.fat2fit.model;

import org.hibernate.Session;

import java.util.List;

public class HibernateCategoryDAO implements ICategory {

    Factory factoryInstance;

    public HibernateCategoryDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }


    @Override
    public Category getCategory(int id) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        session.close();

        return category;
    }

    @Override
    public Category[] getCategories() throws DBException {

        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List categories = session.createQuery("FROM Category").list();// hql
        session.close();
        System.out.println("There are " + categories.size() + " user(s)");
        Category[] returnArr = new Category[categories.size()];
        returnArr = (Category[])  categories.toArray(returnArr);
        return returnArr;
    }
}
