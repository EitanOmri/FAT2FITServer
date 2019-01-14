package com.fat2fit.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * The type Hibernate category dao.
 * this class responsible to makes queries to the category table in the data base.
 */
public class HibernateCategoryDAO implements ICategoryDAO {

    private Factory factoryInstance;

    /**
     * Instantiates a new Hibernate category dao.
     */
    public HibernateCategoryDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public void addCategory(Category category) throws DBException {
        //the basic way to save new object by session
        if (getCategory(category.getId()) == null) {
            Session session = factoryInstance.getFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(category);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                throw new DBException("add category error", e);
            } finally {
                session.close();
            }

        }
    }

    @Override
    public void deleteCategory(int id) throws DBException {
        //the basic way to delete object by session
        Category category = getCategory(id);
        if (category != null) {
            Session session = factoryInstance.getFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(category);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                throw new DBException("delete category error", e);
            } finally {
                session.close();
            }
        }
    }

    @Override
    public Category getCategory(int id) throws DBException {
        //the basic way to get  one object by session
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        Category category = null;
        try {
            tx = session.beginTransaction();
            category = (Category) session.get(Category.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get category error", e);
        } finally {
            session.close();
            return category;
        }


    }

    @Override
    public Category[] getCategories() throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        List categories = null;
        try {
            tx = session.beginTransaction();
            categories = session.createQuery("FROM com.fat2fit.model.Category").list();// hql
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get all category error", e);
        } finally {
            session.close();
            Category[] returnArr = new Category[categories.size()];
            returnArr = (Category[]) categories.toArray(returnArr);
            return returnArr;
        }
    }

    @Override
    public Category getCategoryByName(String name) throws DBException {
        /* in the view, when the admin adds exercise he has to associate to a category.
        this function converts the name category to category object.
        */
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        List category = null;
        try {
            tx = session.beginTransaction();
            category = session.createQuery("FROM com.fat2fit.model.Category category WHERE Name=:parm ").setParameter("parm", name).list();// hql
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get category by name error", e);
        } finally {
            session.close();
            if (category.size() == 0) //there is no category with this name
                return null;
            else
                return (Category) category.get(0);
        }
    }


}
