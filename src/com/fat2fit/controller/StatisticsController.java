package com.fat2fit.controller;

import com.fat2fit.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import com.google.gson.*;

/**
 * The type Statistics controller.
 */
public class StatisticsController {
    /**
     * Top 3.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void top3(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //dispatcher to top 3 users
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        try {
            if (username != null) {
                IExerciseHistory exerciseHistoryDAO = new HibernateExerciseHistoryDAO();
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
                request.setAttribute("topNTable", sb.toString());
                dispatcher = request.getServletContext().getRequestDispatcher("/Top3.jsp");
            } else
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            if (dispatcher == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Statistics.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void statistics(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        /*
         *this function creates lists of maps(necessary for CanavsJS library).
         * the first part of the takes the last 7 works days of the user and check if they were in the last week.
         * the second part of the function calculates the distribution of training by category.
         */
        RequestDispatcher dispatcher = null;
        String username = (String) request.getSession().getAttribute("userName");
        try {
            if (username != null) {
                IExerciseHistory exerciseHistoryDAO = new HibernateExerciseHistoryDAO();
                WeeklyCalMapping[] calories = exerciseHistoryDAO.getStatisticsWeeklyCal(username);
                LocalDate date = LocalDate.now();
                date = date.minusDays(6);
                DayOfWeek today = date.getDayOfWeek();
                Map<Object, Object> map = null;
                List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
                int j = calories.length - 1;
                for (int i = 0; i < 7; i++) {
                    map = new HashMap<Object, Object>();
                    if (j > 0)

                        while (calories[j].getDate().getYear() < date.getYear() && j > 0) //there are days in array before the last week
                            j--;
                        while (calories[j].getDate().getMonth() < date.getMonthValue() && j > 0) //there are days in array before the last week
                            j--;
                        while (calories[j].getDate().getDate() < date.getDayOfMonth() && j > 0) //there are days in array before the last week
                            j--;
                    if (j >= 0)
                        if (calories[j].getDate().getDate() == date.getDayOfMonth()) {
                            map.put("label", today.toString());
                            map.put("y", calories[j].getCal());
                            if (j > 0)
                                j--;
                        } else {
                            map.put("label", today.toString()); //no training in this day
                            map.put("y", 0);
                        }
                    list.add(map);
                    date = date.plusDays(1);//go to next day
                    today = today.plus(1); //go to next day
                }
                Gson gsonObj = new Gson();
                HttpSession session = request.getSession();
                request.setAttribute("dataPointsWeekly", gsonObj.toJson(list));

                //pie chart
                list = new ArrayList<Map<Object, Object>>();
                CategoryMapping[] categories = exerciseHistoryDAO.getStatisticsCategory(username);
                int totalPercents = 0;
                int totalExercise = 0;
                for (CategoryMapping categoryMapping : categories)
                    totalExercise = +(int) categoryMapping.getTotalExercises();

                for (int i = 0; i < categories.length; i++) {
                    map = new HashMap<Object, Object>();
                    map.put("label", categories[i].getCategoryName());
                    if (i == categories.length - 1) {
                        if (categories.length - 1 == 0) //there are only 1 category
                            map.put("y", 100);
                        else  //there are more then 1 category
                            map.put("y", 100 - totalPercents);
                    } else
                        map.put("y", (int) (categories[i].getTotalExercises() / totalExercise));
                    list.add(map);
                    totalPercents += (int) (categories[i].getTotalExercises() / totalExercise);
                }
                request.setAttribute("dataPieCategory", gsonObj.toJson(list));
                dispatcher = request.getServletContext().getRequestDispatcher("/Statistics.jsp");
            } else
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            if (dispatcher == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/ErrorPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}