package com.fat2fit.controller;

import com.fat2fit.model.DBException;
import com.fat2fit.model.HibernateUserDAO;
import com.fat2fit.model.IUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        if (username != null) {
            try {
                IUser userDAO = new HibernateUserDAO();
                HttpSession session = request.getSession();
                session.setAttribute("weightEdit", userDAO.getUser(username).getWeight());
                session.setAttribute("heightEdit", userDAO.getUser(username).getHeight());
                dispatcher = request.getServletContext().getRequestDispatcher("/Settings.jsp");
            } catch (
                    DBException e) {
                e.printStackTrace();
            }
        }
        else
        dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
        dispatcher.forward(request, response);

    }
}
