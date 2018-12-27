package com.fat2fit.controller;

import com.fat2fit.model.DBException;
import com.fat2fit.model.HibernateUserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NavigatorController {
    public void home(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        dispatcher = request.getServletContext().getRequestDispatcher("/Home.jsp");
        HibernateUserDAO hibernateUserDAO = new HibernateUserDAO();
        HttpSession session = request.getSession();
        StringBuffer sb = new StringBuffer();

        try {
            if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName"))) {
                sb.append("     <li><a href=\"/controller/AdminController/home\">\n" +
                        "                        <img src=" + request.getContextPath() + "/IMG/admin.png alt=\" admin\">\n" +
                        "                    <h2 style=\"font-size: 40px;color: white\">admin</h2>\n" +
                        "                    <p style=\"font-size: 20px;color: white\">manage your FAT2FIT gym</p></a>  \n" +
                        "            </li>");

                session.setAttribute("adminLink", sb.toString());
                session.setAttribute("messageLink", "");

            } else {


                sb.append(" <li><a href=\"/controller/MessageController/message\">\n" +
                        "                        <img src=\"<%=request.getContextPath()%>/IMG/message.png\" alt=\"message\">\n" +
                        "                    <h2 style=\"font-size: 40px;color: white\">Message</h2>\n" +
                        "                    <p style=\"font-size: 20px;color: white\">these are your messages</p></a>\n" +
                        "                    \n" +
                        "            </li>\n" +
                        "           ");
                session.setAttribute("adminLink", "");
                session.setAttribute("messageLink", sb.toString());
            }

        dispatcher.forward(request, response);
    } catch(
    DBException e)

    {
        e.printStackTrace();
    }

}

    public void myHistory(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        dispatcher = request.getServletContext().getRequestDispatcher("/MyHistory.jsp");
        dispatcher.forward(request, response);
    }

    public void signUp(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        dispatcher = request.getServletContext().getRequestDispatcher("/SignUp.jsp");
        dispatcher.forward(request, response);
    }


}
