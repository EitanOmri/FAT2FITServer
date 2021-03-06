package com.fat2fit.controller;

import com.fat2fit.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        //adding training list to user in the DB
        RequestDispatcher dispatcher = null;
        ITrainingListExercisesDAO listExercisesDAO = new HibernateTrainingListExercisesDAO();
        IExerciseHistoryDAO historyDAO = new HibernateExerciseHistoryDAO();
        int id;
        try {
            if (request.getParameter("id") != null) {
                if (request.getParameter("id").matches("-?\\d+(\\d+)?")) {
                    id = Integer.parseInt(request.getParameter("id"));
                    String username = (String) request.getSession().getAttribute("userName");
                    if (username != null) {
                        TrainingListExercises[] trainingListExercises = listExercisesDAO.getbyTrainigId(id);
                        for (TrainingListExercises listExercises : trainingListExercises) {
                            ExerciseHistory exerciseHistory = new ExerciseHistory(username,
                                    listExercises.getIdExercise(),
                                    listExercises.getSets(),
                                    listExercises.getReps(), new Date(), 1);
                            historyDAO.saveExercise(exerciseHistory);
                        }
                        dispatcher = request.getServletContext().getRequestDispatcher("/controller/TrainingController/workoutMenu");
                    } else {
                        dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
                    }
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
        } finally {
            if (dispatcher == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        }
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
        //dispatcher to my history page
        RequestDispatcher dispatcher = null;
        IExerciseHistoryDAO historyDAO = new HibernateExerciseHistoryDAO();
        IExercisesDAO hibernateExercisesDAO = new HibernateExercisesDAO();
        String username = (String) request.getSession().getAttribute("userName");
        try {
            if (username != null) {
                ExerciseHistory[] exerciseHistory = historyDAO.getAllHistoryPerUser(username);
                StringBuffer sb = new StringBuffer();
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                for (ExerciseHistory history : exerciseHistory) {
                    sb.append("<tr>");
                    sb.append("<th>");
                    sb.append(df.format(history.getDate()));
                    sb.append("</th>");
                    sb.append("<th>");
                    sb.append(hibernateExercisesDAO.getExercise(history.getIdExercise()).getName());
                    sb.append("</th>");
                    sb.append("<td>");
                    sb.append(history.getSets());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append(history.getReps());
                    sb.append("</td>");
                    sb.append("<td>");
                    sb.append("<a href=\"/controller/HistoryController/editOrDeleteView?id=");
                    sb.append(history.getId() + "&sets=" + history.getSets() + "&reps=" + history.getReps() + "\"");
                    sb.append("data-role=\"button\" data-rel=\"dialog\" data-transition=\"pop\">Edit/Delete");
                    sb.append("</a></td>");
                    sb.append("</tr>");
                }
                HttpSession session = request.getSession();
                request.setAttribute("myHistoryTable", sb.toString());
                dispatcher = request.getServletContext().getRequestDispatcher("/myHistory.jsp");
            } else {
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            }
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            if (dispatcher == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        }
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
        //dispatcher to edit or delete dialog
        RequestDispatcher dispatcher = null;
        int id, sets, reps;
        if (request.getSession().getAttribute("userName") != null) {
            if (request.getParameter("id") != null) {
                if (request.getParameter("id").matches("-?\\d+(\\.\\d+)?")
                        && request.getParameter("sets").matches("-?\\d+(\\.\\d+)?")
                        && request.getParameter("reps").matches("-?\\d+(\\.\\d+)?")) {
                    id = Integer.parseInt(request.getParameter("id"));
                    sets = Integer.parseInt(request.getParameter("sets"));
                    reps = Integer.parseInt(request.getParameter("reps"));
                    HttpSession session = request.getSession();
                    session.setAttribute("idEditOrView", id);
                    request.setAttribute("setsEditOrView", sets);
                    request.setAttribute("repsEditOrView", reps);
                    dispatcher = request.getServletContext().getRequestDispatcher("/editHistory.jsp");
                }
            }
        } else {
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
        }
        if (dispatcher == null)
            dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
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
        //remove action from DB
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        if (session.getAttribute("idEditOrView") != null && session.getAttribute("userName") != null) {
            int id = (int) session.getAttribute("idEditOrView");
            IExerciseHistoryDAO historyDAO = new HibernateExerciseHistoryDAO();
            try {
                historyDAO.deleteExercise(id);
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/HistoryController/myHistory");
            } catch (DBException e) {
                e.printStackTrace();
            } finally {
                if (dispatcher == null)
                    dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
                dispatcher.forward(request, response);
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
        //update action from DB
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        try {
            if (request.getParameter("sets") != null && request.getParameter("reps") != null) {
                if (request.getParameter("sets").matches("-?\\d+(\\.\\d+)?")
                        && request.getParameter("reps").matches("-?\\d+(\\.\\d+)?")) {
                    int sets = Integer.parseInt(request.getParameter("sets"));
                    int reps = Integer.parseInt(request.getParameter("reps"));
                    if (session.getAttribute("idEditOrView") != null && session.getAttribute("userName") != null) {
                        int id = (int) session.getAttribute("idEditOrView");
                        IExerciseHistoryDAO historyDAO = new HibernateExerciseHistoryDAO();
                        historyDAO.updateExercise(id, reps, sets);
                        dispatcher = request.getServletContext().getRequestDispatcher("/controller/HistoryController/myHistory");
                    }
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            if (dispatcher == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
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
        //save "my day" workout in the DB
        RequestDispatcher dispatcher = null;
        IExerciseHistoryDAO historyDAO = new HibernateExerciseHistoryDAO();
        IExercisesDAO exercisesDAO = new HibernateExercisesDAO();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("userName");
        try {
            if (session.getAttribute("categoryId") != null) {
                if (username != null) {
                    int categoryId = (int) session.getAttribute("categoryId");
                    Exercises[] exercises = exercisesDAO.getExercisesByCategory(categoryId);
                    for (Exercises exercise : exercises) {
                        int exId = exercise.getId();
                        if (request.getParameter("sets" + exId) != null && request.getParameter("reps" + exId) != null) {
                            if (request.getParameter("reps" + exId) != "" && request.getParameter("sets" + exId) != "") {
                                if (request.getParameter("reps" + exId).matches("-?\\d+(\\.\\d+)?") &&
                                        request.getParameter("sets" + exId).matches("-?\\d+(\\.\\d+)?")) {
                                    int reps = Integer.parseInt(request.getParameter("reps" + exId));
                                    int sets = Integer.parseInt(request.getParameter("sets" + exId));
                                    if (reps > 0 && sets > 0) {
                                        ExerciseHistory exerciseHistory = new ExerciseHistory(username,
                                                exId, sets, reps, new Date(), 1);
                                        historyDAO.saveExercise(exerciseHistory);
                                    }
                                }
                            }
                        }
                    }
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");

                } else {
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
                }
            }

        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            if (dispatcher == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}