package com.fat2fit.controller;

import com.fat2fit.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type History controller.
 */
public class HistoryController {

    /**
     * Add training list.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void addTrainingList(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        ITrainingListExercises listExercisesDAO = new HibernateTrainingListExercisesDAO();
        IExerciseHistory historyDAO = new HibernateExerciseHistoryDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        String username = (String) request.getSession().getAttribute("userName");
        if (username != null) {
            try {
                TrainingListExercises[] trainingListExercises = listExercisesDAO.getbyTrainigId(id);
                for (int i = 0; i < trainingListExercises.length; i++) {
                    ExerciseHistory exerciseHistory = new ExerciseHistory(username,
                            trainingListExercises[i].getIdExercise(),
                            trainingListExercises[i].getSets(),
                            trainingListExercises[i].getReps(), new Date(), 1);
                    historyDAO.saveExercise(exerciseHistory);
                }
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/TrainingController/workoutMenu");
            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
        }
        dispatcher.forward(request, response);

    }

    /**
     * My history.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void myHistory(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        IExerciseHistory historyDAO = new HibernateExerciseHistoryDAO();
        IExercises hibernateExercisesDAO = new HibernateExercisesDAO();
        String username = (String) request.getSession().getAttribute("userName");
        if (username != null) {
            try {
                ExerciseHistory[] exerciseHistory = historyDAO.getAllHistoryPerUser(username);
                StringBuffer sb = new StringBuffer();
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                for (int i = 0; i < exerciseHistory.length; i++) {
                    sb.append("<tr>");
                    sb.append("<th>");
                    sb.append(df.format(exerciseHistory[i].getDate()));
                    sb.append("</th>");
                    sb.append("<th>");
                    sb.append(hibernateExercisesDAO.getExercise(exerciseHistory[i].getIdExercise()).getName());
                    sb.append("</th>");

                    sb.append("<td>");
                    sb.append(exerciseHistory[i].getSets());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append(exerciseHistory[i].getReps());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append("<a href=\"/controller/HistoryController/editOrDeleteView?id=");
                    sb.append(exerciseHistory[i].getId() + "&sets=" + exerciseHistory[i].getSets() + "&reps=" + exerciseHistory[i].getReps() + "\"");
                    sb.append("data-role=\"button\" data-rel=\"dialog\" data-transition=\"pop\">Edit/Delete");
                    sb.append("</a></td>");
                    sb.append("</tr>");

                }
                HttpSession session = request.getSession();
                session.setAttribute("myHistoryTable", sb.toString());
                dispatcher = request.getServletContext().getRequestDispatcher("/MyHistory.jsp");


            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
        }
        dispatcher.forward(request, response);
    }

    /**
     * Edit or delete view.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void editOrDeleteView(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        if (request.getSession().getAttribute("userName") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            int sets = Integer.parseInt(request.getParameter("sets"));
            int reps = Integer.parseInt(request.getParameter("reps"));
            HttpSession session = request.getSession();
            session.setAttribute("idEditOrView", id);
            session.setAttribute("setsEditOrView", sets);
            session.setAttribute("repsEditOrView", reps);
            dispatcher = request.getServletContext().getRequestDispatcher("/EditHistory.jsp");
        } else {
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");
        }

        dispatcher.forward(request, response);

    }

    /**
     * Delete action.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void deleteAction(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        if (session.getAttribute("idEditOrView") != null && session.getAttribute("userName")!=null) {
            int id = (int) session.getAttribute("idEditOrView");
            IExerciseHistory historyDAO = new HibernateExerciseHistoryDAO();
            try {
                historyDAO.deleteExercise(id);
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/HistoryController/myHistory");
                dispatcher.forward(request, response);

            } catch (DBException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update action.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void updateAction(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        int sets = Integer.parseInt(request.getParameter("sets"));
        int reps = Integer.parseInt(request.getParameter("reps"));
        if (session.getAttribute("idEditOrView") != null && session.getAttribute("userName")!=null) {
            int id = (int) session.getAttribute("idEditOrView");
            IExerciseHistory historyDAO = new HibernateExerciseHistoryDAO();
            try {
                historyDAO.updateExercise(id, reps, sets);
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/HistoryController/myHistory");
                dispatcher.forward(request, response);
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Add my day.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void addMyDay(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        IExerciseHistory historyDAO = new HibernateExerciseHistoryDAO();
        IExercises exercisesDAO = new HibernateExercisesDAO();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("userName");
        if(username!=null && session.getAttribute("categoryId")!=null){
        int categoryId = (int) session.getAttribute("categoryId");
        try {
            Exercises[] exercises = exercisesDAO.getExercisesByCategory(categoryId);
            for (int i = 0; i < exercises.length; i++) {
                int exId = exercises[i].getId();
                if (request.getParameter("reps" + exId) != "" && request.getParameter("sets" + exId) != "") {
                    int reps = Integer.parseInt(request.getParameter("reps" + exId));
                    int sets = Integer.parseInt(request.getParameter("sets" + exId));
                    if (reps > 0 && sets > 0) {
                        ExerciseHistory exerciseHistory = new ExerciseHistory(username,
                                exId, sets, reps, new Date(), 1);
                        historyDAO.saveExercise(exerciseHistory);
                    }
                }
            }
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
            dispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
        }}
        else{
            dispatcher = request.getServletContext().getRequestDispatcher("/Login.jsp");}
        dispatcher.forward(request, response);

    }
}