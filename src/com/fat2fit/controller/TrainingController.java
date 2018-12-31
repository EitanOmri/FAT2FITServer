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
        //dispatcher to workout menu
        RequestDispatcher dispatcher = null;
        ITrainingListName listNameDAO = new HibernateTrainingListNameDAO();
        try {
            if (request.getSession().getAttribute("userName") != null) {
                TrainingListName[] listNames = listNameDAO.getTrainingListNames();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < listNames.length; i++) {
                    sb.append("<li> <a href=\"/controller/TrainingController/workout?id=");
                    sb.append(listNames[i].getId() + "\"");
                    if (i % 2 == 0)
                        sb.append("class=\"ui-shadow-icon ui-btn ui-shadow   ui-btn-icon-left\" data-rel=\"dialog\"   data-transition=\"pop\" style=\"font-size: 45px;background-color: #323131;color: white\">");
                    else
                        sb.append("class=\"ui-shadow-icon ui-btn ui-shadow   ui-btn-icon-left\" data-rel=\"dialog\"   data-transition=\"pop\" style=\"font-size: 45px;background-color: white;color: #323131\">");
                    sb.append(listNames[i].getName());
                    sb.append("</a></li>");
                }
                request.setAttribute("workoutMenuNames", sb.toString());
                dispatcher = request.getServletContext().getRequestDispatcher("/TrainingListMenu.jsp");
            } else {
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
        finally {
            dispatcher.forward(request, response);
        }
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
        //dispatcher to specific workout
        RequestDispatcher dispatcher = null;
        IExercises hibernateExercisesDAO = new HibernateExercisesDAO();
        ITrainingListName listNameDAO = new HibernateTrainingListNameDAO();
        ITrainingListExercises listExercisesDAO = new HibernateTrainingListExercisesDAO();
        try {
            if (request.getParameter("id").matches("-?\\d+(\\.\\d+)?")) {
                int id = Integer.parseInt(request.getParameter("id"));
                if (request.getSession().getAttribute("userName") != null) {
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
                } else { //no session
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
                }
            } else { //id is non numeric
                //todo:non numeric
            }
        } catch (DBException e) {
            e.printStackTrace();
            //todo: error page
        } finally {
            dispatcher.forward(request, response);
        }
    }
}