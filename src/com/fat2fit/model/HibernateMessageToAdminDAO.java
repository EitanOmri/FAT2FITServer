package com.fat2fit.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * The type Hibernate exercises dao.
 * this class responsible to makes queries to the message to admin table in the data base.
 */
public class HibernateMessageToAdminDAO implements IMessageToAdminDAO {
    private Factory factoryInstance;

    /**
     * Instantiates a new Hibernate message to admin dao.
     */
    public HibernateMessageToAdminDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public void saveMessage(MessageToAdmin messageToAdmin) throws DBException {
        //the basic way to save object by session
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(messageToAdmin);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("add message to admin error", e);
        } finally {
            session.close();
        }
    }

    @Override
    public MessageToAdmin getMessageToAdmin(int id) throws DBException {
        //the basic way to get object by session
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        MessageToAdmin toAdmin = null;
        try {
            tx = session.beginTransaction();
            toAdmin = (MessageToAdmin) session.get(MessageToAdmin.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get message to admin error", e);
        } finally {
            session.close();
            return toAdmin;
        }
    }

    @Override
    public void deleteMessage(int id) throws DBException {
        //the basic way to delete object by session
        MessageToAdmin message = getMessageToAdmin(id);
        Transaction tx = null;
        Session session = factoryInstance.getFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(message);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new DBException("delete message to admin error", e);
        } finally {
            session.close();
        }
    }

    @Override
    public MessageToAdmin[] getAllMessageToAdmin() throws DBException {
        //getting array of all message to admin
        Session session = factoryInstance.getFactory().openSession();
        List messages = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            messages = session.createQuery("FROM com.fat2fit.model.MessageToAdmin").list();// hql
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get all message to admin", e);
        } finally {
            MessageToAdmin[] returnArr = new MessageToAdmin[messages.size()];
            returnArr = (MessageToAdmin[]) messages.toArray(returnArr);
            return returnArr;
        }
    }
}
