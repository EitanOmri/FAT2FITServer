package com.fat2fit.controller;

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
        PrintWriter out = response.getWriter();
        String[] splited = str.split("/", 5);
        String controllerName = splited[2];
        String actionName = splited[3];
        String strAfterAction = splited.length == 5 ? splited[4] : null;
        try {
            Class controllerClass = Class.forName("com.fat2fit.controller." + controllerName);
            Object controller = controllerClass.newInstance();
            Method method = controllerClass.getMethod(actionName, HttpServletRequest.class, HttpServletResponse.class, String.class);
            method.invoke(controller, request, response, strAfterAction);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
