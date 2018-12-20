package com.fat2fit.controller;

import com.fat2fit.model.DBException;
import com.fat2fit.model.HibernateUserDAO;
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

public class UserController {
    public void addUser(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HibernateUserDAO hibernateUserDAO = new HibernateUserDAO();
        String userName = request.getParameter("UserName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String birthday = request.getParameter("birthday");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        double weight = Double.parseDouble(request.getParameter("weight"));
        double height = Double.parseDouble(request.getParameter("height"));
        int isManager = 0;
        Date birthdayDate = null;
        try {
            birthdayDate = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User(userName, firstName, lastName, email, password, birthdayDate, weight, height, isManager);
        try {
            if (!hibernateUserDAO.isUserExsits(userName)) {
                hibernateUserDAO.saveUser(user);
                HttpSession session = request.getSession();
                session.setAttribute("userName", userName);
                dispatcher = request.getServletContext().getRequestDispatcher("/Home.jsp");
            } else {

                PrintWriter out = response.getWriter();
                out.println("there already user with the same username");
                //TODO: add dispatcher to new page with error "username has  already token please
            }

            dispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void login(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HibernateUserDAO hibernateUserDAO = new HibernateUserDAO();
        String userName = request.getParameter("UserName");
        String password = request.getParameter("password");
        try {
            if (hibernateUserDAO.testLogin(userName, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("userName", userName);
                dispatcher = request.getServletContext().getRequestDispatcher("/Home.jsp");

            } else {
                //TODO: dispatcher to error page -username or paswword incorrect
            }
            dispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
        }


    }

    public void logout(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HibernateUserDAO hibernateUserDAO = new HibernateUserDAO();
        HttpSession session = request.getSession();
session.invalidate();
        dispatcher = request.getServletContext().getRequestDispatcher("http://localhost:63343/FAT2FITClient/Login.html");
dispatcher.forward(request,response);

    }

}
