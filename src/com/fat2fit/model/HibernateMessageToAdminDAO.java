package com.fat2fit.model;

import org.hibernate.Session;

import java.util.List;

/**
 * The type Hibernate message to admin dao.
 */
public class HibernateMessageToAdminDAO implements IMessageToAdmin {
    private Factory factoryInstance;

    /**
     * Instantiates a new Hibernate message to admin dao.
     */
    public HibernateMessageToAdminDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public void saveMessage(MessageToAdmin messageToAdmin) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        session.save(messageToAdmin);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public MessageToAdmin getMessageToAdmin(int id) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        MessageToAdmin message = (MessageToAdmin) session.get(MessageToAdmin.class, id);
        session.close();
        return message;
    }

    @Override
    public void deleteMessage(int id) throws DBException {
        MessageToAdmin message = getMessageToAdmin(id);
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        session.delete(message);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public MessageToAdmin[] getAllMessageToAdmin() throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List messages = session.createQuery("FROM com.fat2fit.model.MessageToAdmin").list();// hql
        session.close();
        MessageToAdmin[] returnArr = new MessageToAdmin[messages.size()];
        returnArr = (MessageToAdmin[]) messages.toArray(returnArr);
        return returnArr;
    }
}
