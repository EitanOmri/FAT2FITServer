package com.fat2fit.controller;

import com.fat2fit.model.DBException;
import com.fat2fit.model.HibernateUserDAO;
import com.fat2fit.model.IUser;
import com.fat2fit.model.User;
import com.sun.deploy.net.HttpRequest;

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
    public void addUser(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        IUser hibernateUserDAO = new HibernateUserDAO();
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
            if (request.getParameter("weight").matches("-?\\d+(\\.\\d+)?")
                    && request.getParameter("height").matches("-?\\d+(\\.\\d+)?")) {
                weight = Double.parseDouble(request.getParameter("weight"));
                height = Double.parseDouble(request.getParameter("height"));
                int isManager = 0;
                Date birthdayDate = null;
                birthdayDate = sdf.parse(birthday);
                User user = new User(userName, firstName, lastName, email, password, birthdayDate, weight, height, isManager);
                if (!hibernateUserDAO.isUserExsits(userName)) {
                    hibernateUserDAO.saveUser(user);
                    HttpSession session = request.getSession();
                    session.setAttribute("userName", userName);
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
                } else {

                    PrintWriter out = response.getWriter();
                    out.println("there already user with the same username");
                    //TODO: add dispatcher to new page with error "username has  already token please
                }


                dispatcher.forward(request, response);


            } else {

                //todo: the  height or weight parmeters not numeric dispacther to error page

            }
        } catch (DBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
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
    public void login(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws
            ServletException, IOException {
        RequestDispatcher dispatcher = null;
        IUser hibernateUserDAO = new HibernateUserDAO();
        String userName = request.getParameter("UserName");
        String password = request.getParameter("password");
        try {
            if (hibernateUserDAO.testLogin(userName, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("userName", userName);
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");

            } else {
                //TODO: dispatcher to error page -username or paswword incorrect
            }
            dispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
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
        RequestDispatcher dispatcher = null;
        IUser hibernateUserDAO = new HibernateUserDAO();
        HttpSession session = request.getSession();
        session.invalidate();
        dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
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
        RequestDispatcher dispatcher = null;
        IUser hibernateUserDAO = new HibernateUserDAO();
        if (request.getParameter("weight").matches("-?\\d+(\\.\\d+)?")
                && request.getParameter("height").matches("-?\\d+(\\.\\d+)?")) {
            double height = Double.parseDouble(request.getParameter("height"));
            double weight = Double.parseDouble(request.getParameter("weight"));
            if (request.getSession().getAttribute("userName") != null) {
                try {
                    hibernateUserDAO.updateUser((String) request.getSession().getAttribute("userName"), weight, height);
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
                } catch (DBException e) {
                    e.printStackTrace();
                }
            } else {
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            }
        }else {
            //todo: the  height or weight parmeters not numeric dispacther to error page

        }
        dispatcher.forward(request, response);
    }
}
