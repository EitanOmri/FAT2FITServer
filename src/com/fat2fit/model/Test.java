package com.fat2fit.model;

public class Test {

    public static void main(String[] args) {
        HibernateUserDAO p = new HibernateUserDAO();
        try {
            System.out.println(p.getUser("omrieitan"));
        } catch (DBException e) {
            e.printStackTrace();
        }

        User[] allPr = new User[0];
        try {
            allPr = p.getUseres();
        } catch (DBException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < allPr.length; i++)
            System.out.println(allPr[i]);
    }
}
