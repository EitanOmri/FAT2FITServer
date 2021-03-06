package com.fat2fit.controller;

import com.fat2fit.model.DBException;
import com.fat2fit.model.HibernateUserDAO;
import com.fat2fit.model.IUserDAO;
import com.fat2fit.model.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type User controller.
 */
public class UserController {
    /**
     * Add user.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void addUser(HttpServletRequest request, HttpServletResponse response, String strAfterAction)
            throws ServletException, IOException {
        //adding new user to the DB
        RequestDispatcher dispatcher = null;
        IUserDAO hibernateUserDAO = new HibernateUserDAO();
        String userName = request.getParameter("UserName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String birthday = request.getParameter("birthday");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        double weight;
        double height;
        try {
            if (userName != null && firstName != null && lastName != null && email != null && password != null && birthday != null) {
                if (request.getParameter("weight").matches("-?\\d+(\\.\\d+)?")
                        && request.getParameter("height").matches("-?\\d+(\\.\\d+)?")) {
                    weight = Double.parseDouble(request.getParameter("weight"));
                    height = Double.parseDouble(request.getParameter("height"));
                    int isManager = 0; //in default new user is not admin
                    Date birthdayDate = null;
                    birthdayDate = sdf.parse(birthday);
                    User user = new User(userName, firstName, lastName, email, password, birthdayDate, weight, height, isManager);
                    if (!hibernateUserDAO.isUserExsits(userName)) {
                        hibernateUserDAO.saveUser(user);
                        HttpSession session = request.getSession();
                        session.setAttribute("userName", userName);
                        dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
                    }
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (dispatcher == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Login.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void login(HttpServletRequest request, HttpServletResponse response, String strAfterAction)
            throws ServletException, IOException {
        //login and dispatcher to home page
        RequestDispatcher dispatcher = null;
        IUserDAO hibernateUserDAO = new HibernateUserDAO();
        String userName = request.getParameter("UserName");
        String password = request.getParameter("password");
        try {
            if (userName != null && password != null) {
                if (hibernateUserDAO.testLogin(userName, password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("userName", userName);
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
     * Logout.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void logout(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws
            ServletException, IOException {
        //logout from the server
        HttpSession session = request.getSession();
        session.invalidate(); //remove all attributes
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/logout.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Update.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void update(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws
            ServletException, IOException {
        //update the user's weight and height
        RequestDispatcher dispatcher = null;
        IUserDAO hibernateUserDAO = new HibernateUserDAO();
        try {
            if (request.getParameter("weight") != null && request.getParameter("height") != null) {
                if (request.getParameter("weight").matches("-?\\d+(\\.\\d+)?")
                        && request.getParameter("height").matches("-?\\d+(\\.\\d+)?")) {
                    double height = Double.parseDouble(request.getParameter("height"));
                    double weight = Double.parseDouble(request.getParameter("weight"));
                    if (request.getSession().getAttribute("userName") != null) {
                        hibernateUserDAO.updateUser((String) request.getSession().getAttribute("userName"), weight, height);
                        dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
                    } else {
                        dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
                    }
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

    public void isExistUserName(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws
            ServletException, IOException {
        //update the user's weight and height
        RequestDispatcher dispatcher = null;
        IUserDAO hibernateUserDAO = new HibernateUserDAO();
        String userName = request.getParameter("userName");
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        try {
            if (userName != null) {
                if (!hibernateUserDAO.isUserExsits(userName)) {
                    out.print("false");
                } else {
                    out.print("true");
                }
            } else {
                out.print("true");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
    public void testLogIn(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws
            ServletException, IOException {
        //update the user's weight and height
        RequestDispatcher dispatcher = null;
        IUserDAO hibernateUserDAO = new HibernateUserDAO();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        try {
            if (userName != null && password!=null) {
                if (hibernateUserDAO.testLogin(userName,password)) {// test pass
                    out.print("true");
                } else {
                    out.print("false");
                }
            } else {
                out.print("false");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
