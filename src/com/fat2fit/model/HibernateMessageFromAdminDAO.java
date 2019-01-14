package com.fat2fit.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * The type Hibernate exercises dao.
 * this class responsible to makes queries to the message from admin table in the data base.
 */
public class HibernateMessageFromAdminDAO implements IMessageFromAdminDAO {
    private Factory factoryInstance;

    /**
     * Instantiates a new Hibernate message from admin dao.
     */
    public HibernateMessageFromAdminDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public void saveMessage(MessageFromAdmin messageFromAdmin) throws DBException {
        //the basic way to save object by session
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(messageFromAdmin);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("add message from admin error", e);
        } finally {
            session.close();
        }
    }

    @Override
    public MessageFromAdmin getMessageFromAdmin(int id) throws DBException {
        //the basic way to get object by session
        Session session = factoryInstance.getFactory().openSession();
        Transaction tx = null;
        MessageFromAdmin fromAdmin = null;
        try {
            tx = session.beginTransaction();
            fromAdmin = (MessageFromAdmin) session.get(MessageFromAdmin.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get message from admin error", e);
        } finally {
            session.close();
            return fromAdmin;
        }
    }

    @Override
    public void deleteMessage(int id) throws DBException {
        //the basic way to delete object by session
        MessageFromAdmin message = getMessageFromAdmin(id);
        Transaction tx = null;
        Session session = factoryInstance.getFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(message);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new DBException("delete message from admin error", e);
        } finally {
            session.close();
        }
    }

    @Override
    public MessageFromAdmin[] getAllMessageFromAdmin() throws DBException {
        //getting array of all message from admin
        Session session = factoryInstance.getFactory().openSession();
        List messages = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            messages = session.createQuery("FROM com.fat2fit.model.MessageFromAdmin").list();// hql
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw new DBException("get all message from admin", e);
        } finally {
            MessageFromAdmin[] returnArr = new MessageFromAdmin[messages.size()];
            returnArr = (MessageFromAdmin[]) messages.toArray(returnArr);
            return returnArr;
        }
    }
}
