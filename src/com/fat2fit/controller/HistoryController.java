package com.fat2fit.controller;

import com.fat2fit.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryController {

    public void addTrainingList(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HibernateTrainingListExercisesDAO listExercisesDAO = new HibernateTrainingListExercisesDAO();
        HibernateExerciseHistoryDAO historyDAO = new HibernateExerciseHistoryDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        String username = (String) request.getSession().getAttribute("userName");
        try {
            TrainingListExercises[] trainingListExercises = listExercisesDAO.getbyTrainigId(id);
            for (int i = 0; i < trainingListExercises.length; i++) {
                ExerciseHistory exerciseHistory = new ExerciseHistory(username,
                        trainingListExercises[i].getIdExercise(),
                        trainingListExercises[i].getSets(),
                        trainingListExercises[i].getReps(), new Date(), 1);
                historyDAO.saveExercise(exerciseHistory);
            }
            dispatcher = request.getServletContext().getRequestDispatcher("/Home.jsp");
            dispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }


    public void myHistory(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HibernateExerciseHistoryDAO historyDAO = new HibernateExerciseHistoryDAO();
        HibernateExercisesDAO hibernateExercisesDAO = new HibernateExercisesDAO();
        String username = (String) request.getSession().getAttribute("userName");
        try {
            ExerciseHistory[] exerciseHistory = historyDAO.getAllHistoryPerUser(username);
            StringBuffer sb = new StringBuffer();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            for (int i = 0; i < exerciseHistory.length; i++) {
                sb.append("<tr>");
                sb.append("<th>");
                sb.append(df.format(exerciseHistory[i].getDate()));
                sb.append("</th>");
                sb.append("<th>");
                sb.append(hibernateExercisesDAO.getExercise(exerciseHistory[i].getIdExercise()).getName());
                sb.append("</th>");

                sb.append("<td>");
                sb.append(exerciseHistory[i].getSets());
                sb.append("</td>");
                sb.append("<td>");
                sb.append(exerciseHistory[i].getReps());
                sb.append("</td>");
                sb.append("<td>");
                sb.append("<a href=\"/controller/HistoryController/editOrDelete?id=");
                sb.append(exerciseHistory[i].getId() + "\"");
                sb.append("data-role=\"button\" data-rel=\"dialog\" data-transition=\"pop\">Edit/Delete");
                sb.append("</a></td>");
                sb.append("</tr>");

            }
            HttpSession session = request.getSession();
            session.setAttribute("myHistoryTable", sb.toString());
            dispatcher = request.getServletContext().getRequestDispatcher("/MyHistory.jsp");
            dispatcher.forward(request, response);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public void editOrDelete(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {

    }
}