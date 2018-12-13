package com.fat2fit.model;

import java.util.Date;

public class Test {

    public static void main(String[] args) {
        HibernateUserDAO p = new HibernateUserDAO();
        try {
            System.out.println(p.getUser("omrieitan"));
        } catch (DBException e) {
            e.printStackTrace();
        }
//        User user=new User("tomerShats","tomer","shats","tomershats14@gmail.com","12345",new Date(1994,01,31),50,170,true);
//
//        try {
//            p.saveUser(user);
//        } catch (DBException e) {
//            e.printStackTrace();
//        }
        User[] allPr = new User[0];
        try {
            allPr = p.getUseres();
        } catch (DBException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < allPr.length; i++)
            System.out.println(allPr[i]);

        HibernateCategoryDAO c= new HibernateCategoryDAO();

        try {
            System.out.println(c.getCategory(1));
        } catch (DBException e) {
            e.printStackTrace();
        }

        Category[] allcr = new Category[0];
        try {
            allcr = c.getCategories();
        } catch (DBException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < allcr.length; i++)
            System.out.println(allcr[i]);


    }
}
