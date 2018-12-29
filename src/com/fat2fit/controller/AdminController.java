package com.fat2fit.controller;

import com.fat2fit.model.DBException;
import com.fat2fit.model.HibernateUserDAO;
import com.fat2fit.model.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminController {
    public void home(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HibernateUserDAO hibernateUserDAO = new HibernateUserDAO();
        HttpSession session = request.getSession();
        StringBuffer sb = new StringBuffer();
        try {
            if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName")))
                dispatcher = request.getServletContext().getRequestDispatcher("/AdminHome.jsp");
            else
                dispatcher = request.getServletContext().getRequestDispatcher("/Home.jsp");

            dispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public void addAdminHome(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        try {
            HibernateUserDAO hibernateUserDAO = new HibernateUserDAO();

            if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName"))) {
                User[] users = hibernateUserDAO.getUseresWithOutAdmin();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < users.length; i++) {
                    sb.append("<tr>");
                    sb.append("<th>");
                    sb.append(users[i].getUsername());
                    sb.append("</th>");
                    sb.append("<th>");
                    sb.append(users[i].getFirsName());
                    sb.append("</th>");
                    sb.append("<td>");
                    sb.append(users[i].getLastName());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append("<a href=\"/controller/AdminController/addAdmin?userName=");
                    sb.append(users[i].getUsername() + "\"");
                    sb.append("data-role=\"button\" data-transition=\"pop\">Make gym admin");
                    sb.append("</a></td>");
                    sb.append("</tr>");
                }
                session.setAttribute("AddAdminHomeTable", sb.toString());
                dispatcher = request.getServletContext().getRequestDispatcher("/AddAdmin.jsp");
            } else
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");

            dispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public void addAdmin(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HibernateUserDAO hibernateUserDAO = new HibernateUserDAO();
        HttpSession session = request.getSession();
        StringBuffer sb = new StringBuffer();
        try {
            String username = request.getParameter("userName");
            if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName"))) {
                hibernateUserDAO.addAdmin(username);
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/AdminController/home");
            } else
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");

            dispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }


}
