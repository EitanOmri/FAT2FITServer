package com.fat2fit.controller;

import com.fat2fit.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TrainingController {
    public void workoutMenu(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HibernateTrainingListNameDAO listNameDAO = new HibernateTrainingListNameDAO();
        try {
            TrainingListName[] listNames = listNameDAO.getTrainingListNames();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < listNames.length; i++) {
                sb.append("<li> <a href=\"/controller/TrainingController/workout?id=");
                sb.append(listNames[i].getId() + "\"");
                sb.append("class=\"ui-shadow-icon ui-btn ui-shadow   ui-btn-icon-left\" data-rel=\"dialog\"   data-transition=\"pop\" style=\"font-size: 45px;background-color: #323131;color: white\">");
                sb.append(listNames[i].getName());
                sb.append("</a></li>");
            }
            request.setAttribute("workoutMenuNames", sb.toString());
            dispatcher = request.getServletContext().getRequestDispatcher("/TrainingListMenu.jsp");
            dispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }

    public void workout(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HibernateExercisesDAO hibernateExercisesDAO= new HibernateExercisesDAO();
        HibernateTrainingListExercisesDAO listExercisesDAO = new HibernateTrainingListExercisesDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            TrainingListExercises[] trainingListExercises = listExercisesDAO.getbyTrainigId(id);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < trainingListExercises.length; i++) {
                sb.append("<tr>");
                sb.append("<th>");
                sb.append(hibernateExercisesDAO.getExercise(trainingListExercises[i].getIdExercise()).getName());
                sb.append("</th>");
                sb.append("<th>");
                sb.append(trainingListExercises[i].getSets());
                sb.append("</th>");
                sb.append("<th>");
                sb.append(trainingListExercises[i].getReps());
                sb.append("</th>");
                sb.append("</tr>");
            }
            HttpSession session = request.getSession();
            session.setAttribute("exerciseList", sb.toString());
            session.setAttribute("trainingListId", id);
            dispatcher = request.getServletContext().getRequestDispatcher("/TrainingListExercise.jsp");
            dispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}