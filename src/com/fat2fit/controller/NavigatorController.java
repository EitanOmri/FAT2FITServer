package com.fat2fit.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NavigatorController {
    public void home(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        dispatcher = request.getServletContext().getRequestDispatcher("/Home.jsp");
        dispatcher.forward(request, response);
    }
    public void myHistory(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        dispatcher = request.getServletContext().getRequestDispatcher("/MyHistory.jsp");
        dispatcher.forward(request, response);
    }
}
