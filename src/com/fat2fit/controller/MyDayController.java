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
        ICategory categoryDAO = new HibernateCategoryDAO();
        try {
            if (request.getSession().getAttribute("userName") != null) {
                Category[] categories = categoryDAO.getCategories();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < categories.length; i++) {
                    sb.append("<li> <a href=\"/controller/MyDayController/workout?id=");
                    sb.append(categories[i].getId() + "\"");
                    if (i % 2 == 0)
                        sb.append(" data-rel=\"dialog\" data-transition=\"popup\"  class=\"ui-shadow-icon ui-btn ui-shadow   ui-btn-icon-left\"  style=\"font-size: 45px;background-color: #323131;color: white;\">");
                    else
                        sb.append(" data-rel=\"dialog\" data-transition=\"popup\"  class=\"ui-shadow-icon ui-btn ui-shadow   ui-btn-icon-left\"  style=\"font-size: 45px;background-color: white;color: #323131;\">");
                    sb.append(categories[i].getName());
                    sb.append("</a></li>");
                }
                request.setAttribute("categoriesMenuNames", sb.toString());
                dispatcher = request.getServletContext().getRequestDispatcher("/MyDayMenu.jsp");
            } else {
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            }
        } catch (DBException e) {
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            e.printStackTrace();
        } finally {
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
        IExercises exercisesDAO = new HibernateExercisesDAO();
        ICategory categoryDAO = new HibernateCategoryDAO();
        int id;
        try {
            if (request.getParameter("id").matches("-?\\d+(\\.\\d+)?")) {
                id = Integer.parseInt(request.getParameter("id"));
                if (request.getSession().getAttribute("userName") != null) {
                    Exercises[] exercises = exercisesDAO.getExercisesByCategory(id);
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < exercises.length; i++) {
                        sb.append("<tr>");
                        sb.append("<th>");
                        sb.append(exercises[i].getName());
                        sb.append("</th>");
                        sb.append("<td>");
                        sb.append("<input type=\"number\" name=\"sets");
                        sb.append(exercises[i].getId());
                        sb.append("\" id=\"sets");
                        sb.append(exercises[i].getId());
                        sb.append("\"/></td>");

                        sb.append("<td>");
                        sb.append("<input type=\"number\" name=\"reps");
                        sb.append(exercises[i].getId());
                        sb.append("\" id=\"reps");
                        sb.append(exercises[i].getId());
                        sb.append("\"/></td>");
                        sb.append("</tr>");

                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("categoryId", id);
                    session.setAttribute("categoryName", categoryDAO.getCategory(id).getName());
                    request.setAttribute("categoriesForm", sb.toString());
                    dispatcher = request.getServletContext().getRequestDispatcher("/MyDayExercise.jsp");
                } else {
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
                }
            } else {
                //todo:error page
            }
        } catch (DBException e) {
            e.printStackTrace();
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
        } finally {
            dispatcher.forward(request, response);
        }
    }

}
