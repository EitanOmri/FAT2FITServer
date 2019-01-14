package com.fat2fit.model;

import org.hibernate.Session;

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
        session.beginTransaction();
        session.save(messageToAdmin);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public MessageToAdmin getMessageToAdmin(int id) throws DBException {
        //the basic way to get object by session
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        MessageToAdmin message = (MessageToAdmin) session.get(MessageToAdmin.class, id);
        session.close();
        return message;
    }

    @Override
    public void deleteMessage(int id) throws DBException {
        //the basic way to delete object by session
        MessageToAdmin message = getMessageToAdmin(id);
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        session.delete(message);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public MessageToAdmin[] getAllMessageToAdmin() throws DBException {
        //getting array of all message to admin
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List messages = session.createQuery("FROM com.fat2fit.model.MessageToAdmin").list();// hql
        session.close();
        MessageToAdmin[] returnArr = new MessageToAdmin[messages.size()];
        returnArr = (MessageToAdmin[]) messages.toArray(returnArr);
        return returnArr;
    }
}
