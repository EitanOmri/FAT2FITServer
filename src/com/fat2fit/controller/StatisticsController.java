package com.fat2fit.controller;

import com.fat2fit.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class StatisticsController {
    public void top3(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String username=(String)request.getSession().getAttribute("userName");
        if (username != null) {
            HibernateExerciseHistoryDAO exerciseHistoryDAO=new HibernateExerciseHistoryDAO();
               try {
                   TopNMapping[] topNMappings = exerciseHistoryDAO.getTop3();
                   StringBuffer sb = new StringBuffer();
                for (int i = 0; i < topNMappings.length; i++) {
                    sb.append("<tr>");
                    sb.append("<th>");
                    sb.append(i+1);
                    sb.append("</th>");
                    sb.append("<th>");
                    sb.append(topNMappings[i].getUsername());
                    sb.append("</th>");
                    sb.append("<th>");
                    sb.append(topNMappings[i].getTotalCal());
                    sb.append("</th>");
                    sb.append("</tr>");

                }
                HttpSession session = request.getSession();
                session.setAttribute("topNTable", sb.toString());

                dispatcher = request.getServletContext().getRequestDispatcher("/Top3.jsp");
            } catch (DBException e) {
                e.printStackTrace();
            }

        }else
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
        dispatcher.forward(request, response);


    }
    }