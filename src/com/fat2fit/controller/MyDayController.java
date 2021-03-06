package com.fat2fit.controller;

import com.fat2fit.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type My day controller.
 */
public class MyDayController {
    /**
     * My day menu.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void myDayMenu(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //dispatcher to "my day" menu page
        RequestDispatcher dispatcher = null;
        ICategoryDAO categoryDAO = new HibernateCategoryDAO();
        try {
            if (request.getSession().getAttribute("userName") != null) {
                Category[] categories = categoryDAO.getCategories();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < categories.length; i++) {
                    sb.append("<li> <a href=\"/controller/MyDayController/workout?id=");
                    sb.append(categories[i].getId() + "\"");
                    if (i % 2 == 0)
                        sb.append(" data-rel=\"dialog\" data-transition=\"popup\"  class=\"ui-shadow-icon ui-btn ui-shadow   ui-btn-icon-left\"  style=\"background-color: #323131;color: white;\">");
                    else
                        sb.append(" data-rel=\"dialog\" data-transition=\"popup\"  class=\"ui-shadow-icon ui-btn ui-shadow   ui-btn-icon-left\"  style=\"background-color: white;color: #323131;\">");
                    sb.append(categories[i].getName());
                    sb.append("</a></li>");
                }
                request.getSession().setAttribute("categoriesMenuNames", sb.toString());
                dispatcher = request.getServletContext().getRequestDispatcher("/myDayMenu.jsp");
            } else {
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            }
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            if (dispatcher == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
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
        //dispatcher to specific workout page
        RequestDispatcher dispatcher = null;
        IExercisesDAO exercisesDAO = new HibernateExercisesDAO();
        ICategoryDAO categoryDAO = new HibernateCategoryDAO();
        int id;
        try {
            if (request.getParameter("id") != null) {
                if (request.getParameter("id").matches("-?\\d+(\\.\\d+)?")) {
                    id = Integer.parseInt(request.getParameter("id"));
                    if (request.getSession().getAttribute("userName") != null) {
                        Exercises[] exercises = exercisesDAO.getExercisesByCategory(id);
                        StringBuffer sb = new StringBuffer();
                        for (Exercises exercise : exercises) {
                            sb.append("<tr>");
                            sb.append("<td>");
                            sb.append(exercise.getName());
                            sb.append("</td>");
                            sb.append("<td>");
                            sb.append("<input type=\"number\" name=\"sets");
                            sb.append(exercise.getId());
                            sb.append("\" id=\"sets");
                            sb.append(exercise.getId());
                            sb.append("\"/></td>");
                            sb.append("<td>");
                            sb.append("<input type=\"number\" name=\"reps");
                            sb.append(exercise.getId());
                            sb.append("\" id=\"reps");
                            sb.append(exercise.getId());
                            sb.append("\"/></td>");
                            sb.append("</tr>");
                        }
                        HttpSession session = request.getSession();
                        session.setAttribute("categoryId", id);
                        session.setAttribute("categoryName", categoryDAO.getCategory(id).getName());
                        session.setAttribute("categoriesForm", sb.toString());
                        dispatcher = request.getServletContext().getRequestDispatcher("/myDayExercise.jsp");
                    } else {
                        dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
                    }
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            if (dispatcher == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }

}
