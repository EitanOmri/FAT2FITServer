package com.fat2fit.model;

import javax.jws.soap.SOAPBinding;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        HibernateExerciseHistoryDAO hibernateExerciseHistoryDAO = new HibernateExerciseHistoryDAO();
        ExerciseHistory exerciseHistory = new ExerciseHistory("tomer", 1, 4, 5, new Date(2018, 01, 31), 3);
        try {
            hibernateExerciseHistoryDAO.saveExercise(exerciseHistory);
            TopNMapping[] arr = hibernateExerciseHistoryDAO.getTop3();
            for (int i = 0; i < arr.length; i++)
                System.out.println(arr[i].getUsername() + " " + arr[i].getTotalCal());
           ExerciseHistory []arrAl=hibernateExerciseHistoryDAO.getAllHistoryPerUser("tomer");
            for (int i = 0; i < arrAl.length; i++)
                System.out.println(arrAl[i]);
        } catch (DBException e) {
            e.printStackTrace();
        }
        //        ExerciseHistory[] allPr = new ExerciseHistory[0];
//        try {
//            allPr = hibernateExerciseHistoryDAO.getAllHistoryPerUser("omrieitan");
//        } catch (
//                DBException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < allPr.length; i++)
//            System.out.println(allPr[i]);
//
//        HibernateCategoryDAO c = new HibernateCategoryDAO();
//
//        try {
//            System.out.println(c.getCategory(1));
//        } catch (DBException e) {
//            e.printStackTrace();
//        }
//
//        Category[] allcr = new Category[0];
//        try {
//            allcr = c.getCategories();
//        } catch (DBException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < allcr.length; i++)
//            System.out.println(allcr[i]);
//
//
    }

}
