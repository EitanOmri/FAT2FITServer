package com.fat2fit.model;

import org.hibernate.Session;

import java.util.List;

/**
 * The type Hibernate category dao.
 */
public class HibernateCategoryDAO implements ICategory {

    private Factory factoryInstance;

    /**
     * Instantiates a new Hibernate category dao.
     */
    public HibernateCategoryDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public void addCategory(Category category) throws DBException {
        if (getCategory(category.getId()) == null) {
            Session session = factoryInstance.getFactory().openSession();
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void deleteCategory(int id) throws DBException {
        Category category = getCategory(id);
        if (category != null) {
            Session session = factoryInstance.getFactory().openSession();
            session.beginTransaction();
            session.delete(category);
            session.getTransaction().commit();
            session.close();
        }
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
        List categories = session.createQuery("FROM com.fat2fit.model.Category").list();// hql
        session.close();
        Category[] returnArr = new Category[categories.size()];
        returnArr = (Category[]) categories.toArray(returnArr);
        return returnArr;
    }

    @Override
    public Category getCategoryByName(String name) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List category = session.createQuery("FROM com.fat2fit.model.Category category WHERE Name=:parm ").setParameter("parm", name).list();// hql
        session.close();
        if (category.size() == 0)
            return null;
        else
            return (Category) category.get(0);
    }
}
