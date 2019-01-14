package com.fat2fit.model;

import org.hibernate.Session;

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
        session.beginTransaction();
        session.save(messageFromAdmin);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public MessageFromAdmin getMessageFromAdmin(int id) throws DBException {
        //the basic way to get object by session
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        MessageFromAdmin message = (MessageFromAdmin) session.get(MessageFromAdmin.class, id);
        session.close();
        return message;
    }

    @Override
    public void deleteMessage(int id) throws DBException {
        //the basic way to delete object by session
        MessageFromAdmin message = getMessageFromAdmin(id);
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        session.delete(message);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public MessageFromAdmin[] getAllMessageFromAdmin() throws DBException {
        //getting array of all message from admin
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List messages = session.createQuery("FROM com.fat2fit.model.MessageFromAdmin").list();// hql
        session.close();
        MessageFromAdmin[] returnArr = new MessageFromAdmin[messages.size()];
        returnArr = (MessageFromAdmin[]) messages.toArray(returnArr);
        return returnArr;
    }
}
