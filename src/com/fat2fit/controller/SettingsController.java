package com.fat2fit.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SettingsController {
    public void settings(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        dispatcher = request.getServletContext().getRequestDispatcher("/Settings.jsp");
        dispatcher.forward(request, response);

    }
}
