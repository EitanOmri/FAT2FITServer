package com.fat2fit.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Hibernate user dao.
 */
public class HibernateUserDAO implements IUserDAO {
    private Factory factoryInstance;

    /**
     * Instantiates a new Hibernate user dao.
     */
    public HibernateUserDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public User[] getUsersWithOutAdmin() throws DBException {
        //getting array of all users without admins
        Session session = factoryInstance.getFactory().openSession();
        List users = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            users = session.createQuery("FROM com.fat2fit.model.User where isManager=0").list();// hql
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get all users without admins error", e);
        } finally {
            User[] returnArr = new User[users.size()];
            returnArr = (User[]) users.toArray(returnArr);
            return returnArr;
        }
    }

    @Override
    public boolean isManager(String username) throws DBException {
        //check if the user is admin
        User user = getUser(username);
        if (user == null)
            return false;
        if (user.getIsManager() == 1)
            return true;
        return false;
    }

    @Override
    public void addAdmin(String username) throws DBException {
        //the basic way to update object by session
        User user = getUser(username);
        Session session = factoryInstance.getFactory().openSession();
        if (user != null) {
            user.setIsManager(1);
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(user);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                throw new DBException("add admin error", e);
            } finally {
                session.close();
            }
        }
    }

    @Override
    public User getUser(String username) throws DBException {
        //the basic way to get object by session
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();
            user = (User) session.get(User.class, username);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get user error", e);
        } finally {
            session.close();
            return user;
        }
    }

    @Override
    public void removeUser(String username) throws DBException {
        //the basic way to delete object by session
        if (isUserExsits(username)) {
            Transaction tx = null;
            Session session = factoryInstance.getFactory().openSession();
            try {
                User user = getUser(username);
                tx = session.beginTransaction();
                session.delete(user);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw new DBException("delete user error", e);
            } finally {
                session.close();
            }
        }
    }

    @Override
    public User[] getUseres() throws DBException {
        //getting array of all users
        Session session = factoryInstance.getFactory().openSession();
        List users = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            users = session.createQuery("FROM com.fat2fit.model.User").list();// hql
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get all users list name admin error", e);
        } finally {
            User[] returnArr = new User[users.size()];
            returnArr = (User[]) users.toArray(returnArr);
            return returnArr;
        }
    }

    @Override
    public boolean isUserExsits(String username) throws DBException {
        //checking if the user exist
        if (getUser(username) != null)
            return true;
        return false;
    }

    @Override
    public void saveUser(User user) throws DBException {
        //the basic way to save object by session
        if (!isUserExsits(user.getUsername())) {
            Session session = factoryInstance.getFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(user);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                throw new DBException("add user error", e);
            } finally {
                session.close();
            }
        }
    }

    @Override
    public boolean testLogin(String username, String password) throws DBException {
        //checking if the username and password are correct
        User user = getUser(username);
        if (user != null)
            if (password.equals(user.getPassword()))
                return true; //test pass
        return false;
    }

    @Override
    public void updateUser(String username, double weight, double height) throws DBException {
        //the basic way to update object by session
        User user = getUser(username);
        if (user != null) {
            user.setHeight(height);
            user.setWeight(weight);
            Session session = factoryInstance.getFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(user);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                throw new DBException("update admin error", e);
            } finally {
                session.close();
            }
        }
    }
}
