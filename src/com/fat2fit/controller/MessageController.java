package com.fat2fit.controller;

import com.fat2fit.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Message controller.
 */
public class MessageController {
    /**
     * Message.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void message(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        if (request.getSession().getAttribute("userName") != null) {
            HibernateMessageFromAdminDAO messageFromAdminDAO = new HibernateMessageFromAdminDAO();
            try {
                MessageFromAdmin[] messageFromAdmins = messageFromAdminDAO.getAllMessageFromAdmin();
                StringBuffer sb = new StringBuffer();
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                for (int i = 0; i < messageFromAdmins.length; i++) {
                    sb.append("<tr>");
                    sb.append("<th>");
                    sb.append(df.format(messageFromAdmins[i].getDate()));
                    sb.append("</th>");
                    sb.append("<th>");
                    sb.append(messageFromAdmins[i].getContent());
                    sb.append("</th>");
                    sb.append("</tr>");
                }
                HttpSession session = request.getSession();
                session.setAttribute("messageFromAdminTable", sb.toString());

                dispatcher = request.getServletContext().getRequestDispatcher("/Message.jsp");
            } catch (DBException e) {
                e.printStackTrace();
            }

        } else
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Add message to admin.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void addMessageToAdmin(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        if (username != null) {
            String content = request.getParameter("content");
            HibernateMessageToAdminDAO messageToAdminDAO = new HibernateMessageToAdminDAO();
            MessageToAdmin messageToAdmin = new MessageToAdmin(1, new Date(), content, username);
            try {
                messageToAdminDAO.saveMessage(messageToAdmin);
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");

            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");

        }
        dispatcher.forward(request, response);

    }

    /**
     * Delete message to admin.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void deleteMessageToAdmin(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        HibernateUserDAO hibernateUserDAO = new HibernateUserDAO();
        try {
            if (hibernateUserDAO.isManager(username)) {
                int id =Integer.parseInt( request.getParameter("id"));
                HibernateMessageToAdminDAO messageToAdminDAO = new HibernateMessageToAdminDAO();
                messageToAdminDAO.deleteMessage(id);
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/AdminController/managerMessage");
            } else {
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }

        dispatcher.forward(request, response);

    }

    /**
     * Add message from admin.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void addMessageFromAdmin(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        HibernateUserDAO hibernateUserDAO = new HibernateUserDAO();
        try {
            if (hibernateUserDAO.isManager(username)) {
                String content = request.getParameter("content");
                HibernateMessageFromAdminDAO messageFromAdminDAO = new HibernateMessageFromAdminDAO();
                MessageFromAdmin messageFromAdmin = new MessageFromAdmin(1, new Date(), content);
                messageFromAdminDAO.saveMessage(messageFromAdmin);
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/AdminController/managerMessage");
            } else {
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }

        dispatcher.forward(request, response);

    }

    /**
     * Delete message from admin.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void deleteMessageFromAdmin(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        HibernateUserDAO hibernateUserDAO = new HibernateUserDAO();
        try {
            if (hibernateUserDAO.isManager(username)) {
                int id =Integer.parseInt( request.getParameter("id"));
                HibernateMessageFromAdminDAO messageFromAdminDAO = new HibernateMessageFromAdminDAO();
                messageFromAdminDAO.deleteMessage(id);
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/AdminController/managerMessage");
            } else {
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }

        dispatcher.forward(request, response);

    }

}
