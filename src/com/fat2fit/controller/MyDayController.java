package com.fat2fit.controller;

import com.fat2fit.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyDayController {
    public void myDayMenu(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HibernateCategoryDAO categoryDAO = new HibernateCategoryDAO();
        if (request.getSession().getAttribute("userName") != null) {
            try {
                Category[] categories = categoryDAO.getCategories();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < categories.length; i++) {
                    sb.append("<li> <a href=\"/controller/MyDayController/workout?id=");
                    sb.append(categories[i].getId() + "\"");
                    sb.append(" data-rel=\"dialog\" data-transition=\"popup\"  class=\"ui-shadow-icon ui-btn ui-shadow   ui-btn-icon-left\"  style=\"font-size: 45px;background-color: #323131;color: white;\">");
                    sb.append(categories[i].getName());
                    sb.append("</a></li>");
                }
                request.setAttribute("categoriesMenuNames", sb.toString());
                dispatcher = request.getServletContext().getRequestDispatcher("/MyDayMenu.jsp");

            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
        }
        dispatcher.forward(request, response);
    }

    public void workout(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HibernateExercisesDAO exercisesDAO = new HibernateExercisesDAO();
        HibernateCategoryDAO categoryDAO=new HibernateCategoryDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        if (request.getSession().getAttribute("userName") != null) {
        try {
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
            session.setAttribute("categoryName",categoryDAO.getCategory(id).getName());
            request.setAttribute("categoriesForm", sb.toString());
            dispatcher = request.getServletContext().getRequestDispatcher("/MyDayExercise.jsp");
        } catch (DBException e) {
            e.printStackTrace();
        }
        } else {
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
        }
        dispatcher.forward(request, response);
    }

}
