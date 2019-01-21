package com.fat2fit.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The type Routing component.
 */
@WebServlet(name = "RoutingComponent", urlPatterns = {"/controller/*"})
public class RoutingComponent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getRequestURI();
        //mapping the url
        String[] splited = str.split("/", 5);
        String controllerName = splited[2];
        String actionName = splited[3];
        String strAfterAction = splited.length == 5 ? splited[4] : null;

        try {
            //routing to compatible method
            Class controllerClass = Class.forName("com.fat2fit.controller." + controllerName);
            Object controller = controllerClass.newInstance();
            Method method = controllerClass.getMethod(actionName, HttpServletRequest.class, HttpServletResponse.class, String.class);
            method.invoke(controller, request, response, strAfterAction);
        } catch (InstantiationException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        }

    }
}
