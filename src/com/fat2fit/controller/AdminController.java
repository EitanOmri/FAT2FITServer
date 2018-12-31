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
 * The type Admin controller.
 */
public class AdminController {
    /**
     * Home.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void home(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //dispatcher to the home admin page
        RequestDispatcher dispatcher = null;
        IUser hibernateUserDAO = new HibernateUserDAO();
        try {
            if (request.getSession().getAttribute("userName") == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName")))
                    dispatcher = request.getServletContext().getRequestDispatcher("/AdminHome.jsp");
                else
                    dispatcher = request.getServletContext().getRequestDispatcher("/Home.jsp");
            }
        } catch (DBException e) {
            dispatcher = request.getServletContext().getRequestDispatcher("/Home.jsp");
            e.printStackTrace();
        } finally {
            dispatcher.forward(request, response);
        }
    }

    /**
     * Add admin home.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void addAdminHome(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //dispatcher to the home page of setting user as admin
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        try {
            IUser hibernateUserDAO = new HibernateUserDAO();
            if (request.getSession().getAttribute("userName") == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName"))) {
                    User[] users = hibernateUserDAO.getUseresWithOutAdmin();
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < users.length; i++) {
                        sb.append("<tr>");
                        sb.append("<th>");
                        sb.append(users[i].getUsername());
                        sb.append("</th>");
                        sb.append("<th>");
                        sb.append(users[i].getFirsName());
                        sb.append("</th>");
                        sb.append("<td>");
                        sb.append(users[i].getLastName());
                        sb.append("</td>");
                        sb.append("<td>");
                        sb.append("<a href=\"/controller/AdminController/addAdmin?userName=");
                        sb.append(users[i].getUsername() + "\"");
                        sb.append("data-role=\"button\" data-transition=\"pop\">Make gym admin");
                        sb.append("</a></td>");
                        sb.append("</tr>");
                    }
                    session.setAttribute("AddAdminHomeTable", sb.toString());
                    dispatcher = request.getServletContext().getRequestDispatcher("/AddAdmin.jsp");
                } else
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
            }
        } catch (DBException e) {
            e.printStackTrace();
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
        } finally {
            dispatcher.forward(request, response);
        }
    }

    /**
     * Add admin.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void addAdmin(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //adding a new admin
        RequestDispatcher dispatcher = null;
        IUser hibernateUserDAO = new HibernateUserDAO();
        try {
            String username = request.getParameter("userName");
            if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName"))) {
                hibernateUserDAO.addAdmin(username);
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/AdminController/home");
            } else
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");

        } catch (DBException e) {
            e.printStackTrace();
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
        } finally {
            dispatcher.forward(request, response);
        }
    }

    /**
     * Manage exercise home.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void manageExerciseHome(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //dispatcher to the home page of adding exercise
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        try {
            IUser hibernateUserDAO = new HibernateUserDAO();
            ICategory categoryDAO = new HibernateCategoryDAO();
            if (request.getSession().getAttribute("userName") == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName"))) {
                    Category[] categories = categoryDAO.getCategories();
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < categories.length; i++) {
                        sb.append("<option value=\"");
                        sb.append(categories[i].getName());
                        sb.append("\">");
                    }
                    session.setAttribute("listOfCategories", sb.toString());
                    dispatcher = request.getServletContext().getRequestDispatcher("/AdminExercise.jsp");
                } else {
                    //todo:error page
                }
            }
        } catch (DBException e) {
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
            e.printStackTrace();
        } finally {
            dispatcher.forward(request, response);
        }
    }

    /**
     * Add exercise.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void addExercise(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //adding exercise
        RequestDispatcher dispatcher = null;
        IUser userDAO = new HibernateUserDAO();
        ICategory categoryDAO = new HibernateCategoryDAO();
        IExercises exercisesDAO = new HibernateExercisesDAO();
        try {
            if (request.getSession().getAttribute("userName") == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (userDAO.isManager((String) request.getSession().getAttribute("userName"))) {
                    String exerciseName = request.getParameter("exerciseName");
                    int cal = Integer.parseInt(request.getParameter("cal"));
                    String category = request.getParameter("category");
                    int categoryId = categoryDAO.getCategoryByName(category).getId();
                    int id = exercisesDAO.getAllExercises().length + 1;
                    Exercises exercises = new Exercises(id, exerciseName, cal, categoryId);
                    exercisesDAO.saveExercise(exercises);

                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/AdminController/home");
                } else
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
            }
        } catch (DBException e) {
            e.printStackTrace();
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
        } finally {
            dispatcher.forward(request, response);
        }
    }

    /**
     * Manage traininig list home.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void manageTraininigListHome(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //dispatcher to the home page of adding training list
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        try {
            IUser hibernateUserDAO = new HibernateUserDAO();
            IExercises exercisesDAO = new HibernateExercisesDAO();
            if (request.getSession().getAttribute("userName") == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName"))) {
                    Exercises[] exercises = exercisesDAO.getAllExercises();
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < exercises.length; i++) {
                        sb.append("<tr>");
                        sb.append("<th>");
                        sb.append(exercises[i].getName());
                        sb.append("</th>");
                        sb.append("<td>");
                        sb.append("<input type=\"number\" name=\"sets");
                        sb.append(exercises[i].getId());
                        sb.append("\" id=\"sets");
                        sb.append(exercises[i].getId());
                        sb.append("\"/></td>");

                        sb.append("<td>");
                        sb.append("<input type=\"number\" name=\"reps");
                        sb.append(exercises[i].getId());
                        sb.append("\" id=\"reps");
                        sb.append(exercises[i].getId());
                        sb.append("\"/></td>");
                        sb.append("</tr>");

                    }
                    session.setAttribute("listOfExercisesToAdd", sb.toString());
                    dispatcher = request.getServletContext().getRequestDispatcher("/AdminTrainingList.jsp");
                } else
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
            }
        } catch (DBException e) {
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
            e.printStackTrace();
        } finally {
            dispatcher.forward(request, response);
        }
    }

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
        //adding training list
        RequestDispatcher dispatcher = null;
        IExercises exercisesDAO = new HibernateExercisesDAO();
        ITrainingListExercises trainingListExercisesDAO = new HibernateTrainingListExercisesDAO();
        ITrainingListName trainingListNameDAO = new HibernateTrainingListNameDAO();
        IUser hibernateUserDAO = new HibernateUserDAO();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("userName");
        try {
            if (username == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (hibernateUserDAO.isManager(username)) {
                    String trainingListName = request.getParameter("trainingListName");
                    Exercises[] exercises = exercisesDAO.getAllExercises();
                    int id = trainingListNameDAO.getTrainingListNames().length + 1;
                    TrainingListName listName = new TrainingListName(id, trainingListName);
                    trainingListNameDAO.add(listName);
                    for (int i = 0; i < exercises.length; i++) {
                        int exId = exercises[i].getId();
                        if (request.getParameter("reps" + exId) != "" && request.getParameter("sets" + exId) != "") {
                            int reps = Integer.parseInt(request.getParameter("reps" + exId));
                            int sets = Integer.parseInt(request.getParameter("sets" + exId));
                            if (reps > 0 && sets > 0) {
                                TrainingListExercises trainingListExercises = new TrainingListExercises(1, id, exId, sets, reps);
                                trainingListExercisesDAO.add(trainingListExercises);
                            }
                        }
                    }
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
                } else {
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
                }
            }
        } catch (DBException e) {
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
            e.printStackTrace();
        } finally {
            dispatcher.forward(request, response);
        }
    }

    /**
     * Manager message.
     *
     * @param request        the request
     * @param response       the response
     * @param strAfterAction the str after action
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    public void managerMessage(HttpServletRequest request, HttpServletResponse response, String strAfterAction) throws ServletException, IOException {
        //dispatcher to the home page of message
        RequestDispatcher dispatcher = null;
        IUser hibernateUserDAO = new HibernateUserDAO();
        IMessageFromAdmin messageFromAdminDAO = new HibernateMessageFromAdminDAO();
        IMessageToAdmin toAdminDAO = new HibernateMessageToAdminDAO();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("userName");
        try {
            if (request.getSession().getAttribute("userName") == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (hibernateUserDAO.isManager(username)) {
                    MessageFromAdmin[] messageFromAdmins = messageFromAdminDAO.getAllMessageFromAdmin();
                    MessageToAdmin[] messageToAdmins = toAdminDAO.getAllMessageToAdmin();
                    StringBuffer sb = new StringBuffer();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    for (int i = 0; i < messageFromAdmins.length; i++) {
                        sb.append("<tr>");
                        sb.append("<th>");
                        sb.append(df.format(messageFromAdmins[i].getDate()));
                        sb.append("</th>");
                        sb.append("<th>");
                        sb.append(messageFromAdmins[i].getContent());
                        sb.append("</th>");
                        sb.append("<th>");
                        sb.append("<a href=\"/controller/MessageController/deleteMessageFromAdmin?id=");
                        sb.append(messageFromAdmins[i].getId() + "\"");
                        sb.append("data-role=\"button\">Delete");
                        sb.append("</a>");
                        sb.append("</th>");
                        sb.append("</tr>");
                    }
                    session.setAttribute("historyMessageFromAdminTable", sb.toString());
                    sb = new StringBuffer();
                    for (int i = 0; i < messageToAdmins.length; i++) {
                        sb.append("<tr>");
                        sb.append("<th>");
                        sb.append(df.format(messageToAdmins[i].getDate()));
                        sb.append("</th>");
                        sb.append("<th>");
                        sb.append(messageToAdmins[i].getUsername());
                        sb.append("</th>");
                        sb.append("<th>");
                        sb.append(messageToAdmins[i].getContent());
                        sb.append("</th>");
                        sb.append("<th>");
                        sb.append("<a href=\"/controller/MessageController/deleteMessageToAdmin?id=");
                        sb.append(messageToAdmins[i].getId() + "\"");
                        sb.append("data-role=\"button\">Delete");
                        sb.append("</a>");
                        sb.append("</th>");
                        sb.append("</tr>");
                    }
                    session.setAttribute("messageToAdminTable", sb.toString());
                    dispatcher = request.getServletContext().getRequestDispatcher("/AdminMessages.jsp");
                } else {
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
                }
            }
        } catch (DBException e) {
            e.printStackTrace();
            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");

        } finally {
            dispatcher.forward(request, response);
        }
    }

}
