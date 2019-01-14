package com.fat2fit.controller;

import com.fat2fit.model.DBException;
import com.fat2fit.model.HibernateUserDAO;
import com.fat2fit.model.IUserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Settings controller.
 */
public class SettingsController {
    /**
     * Sets .
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void settings(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
       //dispatcher to setting page
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        try {
            if (username != null) {
                IUserDAO userDAO = new HibernateUserDAO();
                request.setAttribute("weightEdit", userDAO.getUser(username).getWeight());
                request.setAttribute("heightEdit", userDAO.getUser(username).getHeight());
                dispatcher = request.getServletContext().getRequestDispatcher("/Settings.jsp");
            } else
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            if (dispatcher == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}
