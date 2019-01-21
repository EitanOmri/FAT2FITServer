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
        //dispatcher to message page and load all messages from DB
        RequestDispatcher dispatcher = null;
        try {
            if (request.getSession().getAttribute("userName") != null) {
                IMessageFromAdminDAO messageFromAdminDAO = new HibernateMessageFromAdminDAO();
                MessageFromAdmin[] messageFromAdmins = messageFromAdminDAO.getAllMessageFromAdmin();
                StringBuffer sb = new StringBuffer();
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                for (MessageFromAdmin fromAdmin : messageFromAdmins) {
                    sb.append("<tr>");
                    sb.append("<th>");
                    sb.append(df.format(fromAdmin.getDate()));
                    sb.append("</th>");
                    sb.append("<th>");
                    sb.append(fromAdmin.getContent());
                    sb.append("</th>");
                    sb.append("</tr>");
                }
                HttpSession session = request.getSession();
                request.setAttribute("messageFromAdminTable", sb.toString());
                dispatcher = request.getServletContext().getRequestDispatcher("/message.jsp");
            } else
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            if (dispatcher == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        }
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
        //save a new message to admin in the DB
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        try {
            if (username != null) {
                String content = request.getParameter("content");
                if (content != null) {
                    IMessageToAdminDAO messageToAdminDAO = new HibernateMessageToAdminDAO();
                    MessageToAdmin messageToAdmin = new MessageToAdmin(1, new Date(), content, username);
                    messageToAdminDAO.saveMessage(messageToAdmin);
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
                }
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
     * Delete message to admin.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void deleteMessageToAdmin(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //delete message from DB
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        IUserDAO hibernateUserDAO = new HibernateUserDAO();
        int id;
        try {
            if (username != null) {
                if (hibernateUserDAO.isManager(username)) {
                    if (request.getParameter("id") != null) {
                        if (request.getParameter("id").matches("-?\\d+(\\.\\d+)?")) {
                            id = Integer.parseInt(request.getParameter("id"));
                            IMessageToAdminDAO messageToAdminDAO = new HibernateMessageToAdminDAO();
                            messageToAdminDAO.deleteMessage(id);
                            dispatcher = request.getServletContext().getRequestDispatcher("/controller/AdminController/managerMessage");
                        }
                    }
                } else {//no admin
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
                }
            } else { //no session
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
     * Add message from admin.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void addMessageFromAdmin(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //save message to admin in the DB
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        IUserDAO hibernateUserDAO = new HibernateUserDAO();
        try {
            if (username != null) {
                if (hibernateUserDAO.isManager(username)) {
                    String content = request.getParameter("content");
                    if (content != null) {
                        IMessageFromAdminDAO messageFromAdminDAO = new HibernateMessageFromAdminDAO();
                        MessageFromAdmin messageFromAdmin = new MessageFromAdmin(1, new Date(), content);
                        messageFromAdminDAO.saveMessage(messageFromAdmin);
                        dispatcher = request.getServletContext().getRequestDispatcher("/controller/AdminController/managerMessage");
                    }
                } else {
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
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
        //delete message from DB
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        IUserDAO hibernateUserDAO = new HibernateUserDAO();
        int id;
        try {
            if (username != null) {
                if (hibernateUserDAO.isManager(username)) {
                    if (request.getParameter("id") != null) {
                        if (request.getParameter("id").matches("-?\\d+(\\.\\d+)?")) {
                            id = Integer.parseInt(request.getParameter("id"));
                            IMessageFromAdminDAO messageFromAdminDAO = new HibernateMessageFromAdminDAO();
                            messageFromAdminDAO.deleteMessage(id);
                            dispatcher = request.getServletContext().getRequestDispatcher("/controller/AdminController/managerMessage");
                        }
                    }
                } else {//no admin
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
                }
            } else { //no session
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

}
