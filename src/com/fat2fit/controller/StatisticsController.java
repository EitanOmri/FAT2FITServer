package com.fat2fit.controller;

import com.fat2fit.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import com.google.gson.*;

public class StatisticsController {
    public void top3(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        if (username != null) {
            HibernateExerciseHistoryDAO exerciseHistoryDAO = new HibernateExerciseHistoryDAO();
            try {
                TopNMapping[] topNMappings = exerciseHistoryDAO.getTop3();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < topNMappings.length; i++) {
                    sb.append("<tr>");
                    sb.append("<th>");
                    sb.append(i + 1);
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

        } else
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
        dispatcher.forward(request, response);


    }

    public void weeklyBurnCal(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {

        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        if (username != null) {
            HibernateExerciseHistoryDAO exerciseHistoryDAO = new HibernateExerciseHistoryDAO();
            try {
                WeeklyCalMmaping[] calories = exerciseHistoryDAO.getStatisticsWeeklyCal(username);
                Date date = new Date();
                date = new Date(date.getTime() - 6 * 24 * 60 * 60 * 1000); //week before today
                int today = date.getDay();
                Map<Object, Object> map = null;
                List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
                int j = calories.length - 1;
                for (int i = 6; i >= 0; i--) {
                    map = new HashMap<Object, Object>();
                    if (calories[j].getDate().getDate() == date.getDate()) {
                        map.put("label", days[today]);
                        map.put("y", calories[j--].getCal());
                    } else if (calories[j].getDate().getDate() < date.getDate()) { //there are days in array before the last week
                        while (calories[j].getDate().getDate() < date.getDate())
                            j--;
                    } else { //no training in this day
                        map.put("label", days[today]);
                        map.put("y", 0);
                    }
                    list.add(map);
                    date = new Date(date.getTime() + 24 * 60 * 60 * 1000);//go to next day
                    today = (today + 1) % 7; //go to next day
                }
                Gson gsonObj = new Gson();
                HttpSession session = request.getSession();
                session.setAttribute("dataPointsWeekly", gsonObj.toJson(list));

                dispatcher = request.getServletContext().getRequestDispatcher("/Statistics.jsp");
            } catch (DBException e) {
                e.printStackTrace();
            }

        } else
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
        dispatcher.forward(request, response);


    }
}