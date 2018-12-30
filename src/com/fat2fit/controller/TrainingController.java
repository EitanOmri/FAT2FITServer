package com.fat2fit.controller;

import com.fat2fit.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Training controller.
 */
public class TrainingController {
    /**
     * Workout menu.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void workoutMenu(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        ITrainingListName listNameDAO = new HibernateTrainingListNameDAO();
        if (request.getSession().getAttribute("userName") != null) {
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

            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
        }
        dispatcher.forward(request, response);

    }

    /**
     * Workout.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void workout(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        IExercises hibernateExercisesDAO = new HibernateExercisesDAO();
        ITrainingListName listNameDAO=new HibernateTrainingListNameDAO();
        ITrainingListExercises listExercisesDAO = new HibernateTrainingListExercisesDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        if (request.getSession().getAttribute("userName") != null) {
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
                session.setAttribute("trainingListName", listNameDAO.getTrainigListName(id).getName());
                dispatcher = request.getServletContext().getRequestDispatcher("/TrainingListExercise.jsp");

            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
        }
        dispatcher.forward(request, response);
    }
}