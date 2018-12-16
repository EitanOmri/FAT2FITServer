package com.fat2fit.model;

import org.hibernate.Session;

import java.util.List;

public class HibernateMessageFromAdminDAO implements IMessageFromAdmin {
    Factory factoryInstance;
    public HibernateMessageFromAdminDAO() {
        factoryInstance = Factory.getFactoryInstance();
    }

    @Override
    public void saveMessage(MessageFromAdmin messageFromAdmin) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        session.save(messageFromAdmin);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public MessageFromAdmin getMessageFromAdmin(int id) throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        MessageFromAdmin message = (MessageFromAdmin) session.get(MessageFromAdmin.class, id);
        session.close();
        return message;
    }

    @Override
    public void deleteMessage(int id) throws DBException {
        MessageFromAdmin message=getMessageFromAdmin(id);
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        session.delete(message);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public MessageFromAdmin[] getAllMessageFromAdmin() throws DBException {
        Session session = factoryInstance.getFactory().openSession();
        session.beginTransaction();
        List messages = session.createQuery("FROM com.fat2fit.model.MessageFromAdmin").list();// hql
        session.close();
        MessageFromAdmin[] returnArr = new MessageFromAdmin[messages.size()];
        returnArr = (MessageFromAdmin[]) messages.toArray(returnArr);
        return returnArr;
    }
}
