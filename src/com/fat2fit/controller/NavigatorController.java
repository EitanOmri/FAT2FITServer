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
        dispatcher = request.getServletContext().getRequestDispatcher("/Home.jsp");
        IUser userDAO = new HibernateUserDAO();
        HttpSession session = request.getSession();
        StringBuffer sb = new StringBuffer();
        String username = (String) request.getSession().getAttribute("userName");
        try {
            if (username != null) {
                if (userDAO.isManager(username)) {
                    sb.append("     <li><a href=\"/controller/AdminController/home\">\n" +
                            "                        <img src=" + request.getContextPath() + "/IMG/admin.png alt=\" admin\">\n" +
                            "                    <h2 style=\"font-size: 40px;color: white\">admin</h2>\n" +
                            "                    <p style=\"font-size: 20px;color: white\">manage your FAT2FIT gym</p></a>  \n" +
                            "            </li>");

                    session.setAttribute("adminLink", sb.toString());
                    session.setAttribute("messageLink", "");

                } else {
                    sb.append(" <li><a href=\"/controller/MessageController/message\">\n" +
                            "                        <img src=" + request.getContextPath() + "/IMG/message.png alt=\"message\">\n" +
                            "                    <h2 style=\"font-size: 40px;color: white\">Message</h2>\n" +
                            "                    <p style=\"font-size: 20px;color: white\">these are your messages</p></a>\n" +
                            "                    \n" +
                            "            </li>\n" +
                            "           ");
                    session.setAttribute("adminLink", "");
                    session.setAttribute("messageLink", sb.toString());
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
                session.setAttribute("bmiColor", color);
                session.setAttribute("bmi", String.format("%.2f", bmi));
            } else {
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            }
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            if (dispatcher == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
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
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        if (username != null)
            dispatcher = request.getServletContext().getRequestDispatcher("/MyHistory.jsp");
        else
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
        dispatcher.forward(request, response);
    }

    /**
     * Sign up.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void signUp(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.sendRedirect("http://10.0.2.2:63343/FAT2FITClient/SignUp.html");
    }

    public void login(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.sendRedirect("http://10.0.2.2:63343/FAT2FITClient/Login.html");
    }


}
