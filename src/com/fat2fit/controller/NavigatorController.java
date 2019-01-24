package com.fat2fit.controller;

import com.fat2fit.model.DBException;
import com.fat2fit.model.HibernateUserDAO;
import com.fat2fit.model.IUserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Navigator controller.
 */
public class NavigatorController {
    /**
     * Home.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void home(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //dispatcher to home page
        RequestDispatcher dispatcher = null;
        dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
        IUserDAO userDAO = new HibernateUserDAO();
        HttpSession session = request.getSession();
        StringBuffer sb = new StringBuffer();
        String username = (String) request.getSession().getAttribute("userName");
        try {
            if (username != null) {
                if (userDAO.isManager(username)) {
                    sb.append("     <li><a href=\"/controller/AdminController/home\">\n" +
                            "                        <img src=" + request.getContextPath() + "/IMG/admin.png alt=\" admin\">\n" +
                            "                    <h2 style=\"font-size: 35px;color: white\">admin</h2>\n" +
                            "                    <p style=\"font-size: 15px;color: white\">manage your FAT2FIT gym</p></a>  \n" +
                            "            </li>");
                    request.setAttribute("adminLink", sb.toString());
                    request.setAttribute("messageLink", "");

                } else {
                    sb.append(" <li><a href=\"/controller/MessageController/message\">\n" +
                            "                        <img src=" + request.getContextPath() + "/IMG/message.png alt=\"message\">\n" +
                            "                    <h2 style=\"font-size: 35px;color: white\">Message</h2>\n" +
                            "                    <p style=\"font-size: 15px;color: white\">these are your messages</p></a>\n" +
                            "                    \n" +
                            "            </li>\n" +
                            "           ");
                    request.setAttribute("adminLink", "");
                    request.setAttribute("messageLink", sb.toString());
                }
                //calculate the BMI
                double height = userDAO.getUser(username).getHeight() / 100;
                double weight = userDAO.getUser(username).getWeight();
                double bmi = weight / (height * height);
                String color;
                if (bmi < 18.5 || bmi > 30)
                    color = "red";
                else if (bmi >= 25)
                    color = "yellow";
                else
                    color = "green";
                request.setAttribute("bmiColor", color);
                request.setAttribute("bmi", String.format("%.2f", bmi));
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
     * My history.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void myHistory(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
       //redirect to my history page
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        if (username != null)
            dispatcher = request.getServletContext().getRequestDispatcher("/myHistory.jsp");
        else
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
        dispatcher.forward(request, response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //redirect to login page in the client side
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.sendRedirect("//localhost:63343/FAT2FITClient/login.html");
    }


}
