package com.fat2fit.controller;

import com.fat2fit.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;

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
        IUserDAO hibernateUserDAO = new HibernateUserDAO();
        try {
            if (request.getSession().getAttribute("userName") == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName")))
                    dispatcher = request.getServletContext().getRequestDispatcher("/adminHome.jsp");
                else
                    dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
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
            IUserDAO hibernateUserDAO = new HibernateUserDAO();
            if (request.getSession().getAttribute("userName") == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName"))) {
                    User[] users = hibernateUserDAO.getUsersWithOutAdmin();
                    StringBuffer sb = new StringBuffer();
                    for (User user : users) {
                        sb.append("<tr>");
                        sb.append("<th>");
                        sb.append(user.getUsername());
                        sb.append("</th>");
                        sb.append("<th>");
                        sb.append(user.getFirsName());
                        sb.append("</th>");
                        sb.append("<td>");
                        sb.append(user.getLastName());
                        sb.append("</td>");
                        sb.append("<td>");
                        sb.append("<a href=\"/controller/AdminController/addAdmin?userName=");
                        sb.append(user.getUsername() + "\"");
                        sb.append("data-role=\"button\" data-transition=\"pop\">Make gym admin");
                        sb.append("</a></td>");
                        sb.append("</tr>");
                    }
                    request.setAttribute("AddAdminHomeTable", sb.toString());
                    dispatcher = request.getServletContext().getRequestDispatcher("/addAdmin.jsp");
                } else
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
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
        IUserDAO hibernateUserDAO = new HibernateUserDAO();
        try {
            String username = request.getParameter("userName");
            if (username != null) {
                if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName"))) {
                    hibernateUserDAO.addAdmin(username);
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/AdminController/home");
                } else
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");

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
            IUserDAO hibernateUserDAO = new HibernateUserDAO();
            ICategoryDAO categoryDAO = new HibernateCategoryDAO();
            if (request.getSession().getAttribute("userName") == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName"))) {
                    Category[] categories = categoryDAO.getCategories();
                    StringBuffer sb = new StringBuffer();
                    for (Category category : categories) {
                        sb.append("<option value=\"");
                        sb.append(category.getName());
                        sb.append("\">");
                        sb.append(category.getName());
                        sb.append("</option>");
                    }
                    request.setAttribute("listOfCategories", sb.toString());
                    dispatcher = request.getServletContext().getRequestDispatcher("/adminExercise.jsp");
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
        IUserDAO userDAO = new HibernateUserDAO();
        ICategoryDAO categoryDAO = new HibernateCategoryDAO();
        IExercisesDAO exercisesDAO = new HibernateExercisesDAO();
        try {
            if (request.getSession().getAttribute("userName") == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (request.getParameter("cal") != null && request.getParameter("category") != null && request.getParameter("exerciseName") != null) {
                    if (request.getParameter("cal").matches("-?\\d+(\\.\\d+)?")) {
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
            IUserDAO hibernateUserDAO = new HibernateUserDAO();
            IExercisesDAO exercisesDAO = new HibernateExercisesDAO();
            if (request.getSession().getAttribute("userName") == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (hibernateUserDAO.isManager((String) request.getSession().getAttribute("userName"))) {
                    Exercises[] exercises = exercisesDAO.getAllExercises();
                    StringBuffer sb = new StringBuffer();
                    for (Exercises exercisesLoop : exercises) {
                        sb.append("<tr>");
                        sb.append("<th>");
                        sb.append(exercisesLoop.getName());
                        sb.append("</th>");
                        sb.append("<td>");
                        sb.append("<input type=\"number\" name=\"sets");
                        sb.append(exercisesLoop.getId());
                        sb.append("\" id=\"sets");
                        sb.append(exercisesLoop.getId());
                        sb.append("\"/></td>");

                        sb.append("<td>");
                        sb.append("<input type=\"number\" name=\"reps");
                        sb.append(exercisesLoop.getId());
                        sb.append("\" id=\"reps");
                        sb.append(exercisesLoop.getId());
                        sb.append("\"/></td>");
                        sb.append("</tr>");

                    }
                    request.setAttribute("listOfExercisesToAdd", sb.toString());
                    dispatcher = request.getServletContext().getRequestDispatcher("/adminTrainingList.jsp");
                } else
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
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
        IExercisesDAO exercisesDAO = new HibernateExercisesDAO();
        ITrainingListExercisesDAO trainingListExercisesDAO = new HibernateTrainingListExercisesDAO();
        ITrainingListNameDAO trainingListNameDAO = new HibernateTrainingListNameDAO();
        IUserDAO hibernateUserDAO = new HibernateUserDAO();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("userName");
        try {
            if (username == null)
                dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/login");
            else {
                if (hibernateUserDAO.isManager(username)) {
                    String trainingListName = request.getParameter("trainingListName");
                    if (trainingListName != null) {
                        if (!trainingListName.equals("")) {
                            Exercises[] exercises = exercisesDAO.getAllExercises();
                            int id = trainingListNameDAO.getTrainingListNames().length + 1;
                            TrainingListName listName = new TrainingListName(id, trainingListName);
                            trainingListNameDAO.add(listName);
                            for (Exercises exercise : exercises) {
                                int exId = exercise.getId();

                                if (request.getParameter("reps" + exId) != null && request.getParameter("sets" + exId) != null) {
                                    if (request.getParameter("reps" + exId) != "" && request.getParameter("sets" + exId) != "") {
                                        if (request.getParameter("reps" + exId).matches("-?\\d+(\\.\\d+)?")
                                                && request.getParameter("sets" + exId).matches("-?\\d+(\\.\\d+)?")) {
                                            int reps = Integer.parseInt(request.getParameter("reps" + exId));
                                            int sets = Integer.parseInt(request.getParameter("sets" + exId));
                                            if (reps > 0 && sets > 0) {
                                                TrainingListExercises trainingListExercises = new TrainingListExercises(1, id, exId, sets, reps);
                                                trainingListExercisesDAO.add(trainingListExercises);
                                            }
                                        }
                                    }
                                }
                            }
                            dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
                        }
                    }
                } else {
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
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
        IUserDAO hibernateUserDAO = new HibernateUserDAO();
        IMessageFromAdminDAO messageFromAdminDAO = new HibernateMessageFromAdminDAO();
        IMessageToAdminDAO toAdminDAO = new HibernateMessageToAdminDAO();
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
                    for (MessageFromAdmin fromAdmin : messageFromAdmins) {
                        sb.append("<tr>");
                        sb.append("<th>");
                        sb.append(df.format(fromAdmin.getDate()));
                        sb.append("</th>");
                        sb.append("<th>");
                        sb.append(fromAdmin.getContent());
                        sb.append("</th>");
                        sb.append("<th>");
                        sb.append("<a href=\"/controller/MessageController/deleteMessageFromAdmin?id=");
                        sb.append(fromAdmin.getId() + "\"");
                        sb.append("data-role=\"button\">Delete");
                        sb.append("</a>");
                        sb.append("</th>");
                        sb.append("</tr>");
                    }
                    request.setAttribute("historyMessageFromAdminTable", sb.toString());
                    sb = new StringBuffer();
                    for (MessageToAdmin toAdmin : messageToAdmins) {
                        sb.append("<tr>");
                        sb.append("<th>");
                        sb.append(df.format(toAdmin.getDate()));
                        sb.append("</th>");
                        sb.append("<th>");
                        sb.append(toAdmin.getUsername());
                        sb.append("</th>");
                        sb.append("<th>");
                        sb.append(toAdmin.getContent());
                        sb.append("</th>");
                        sb.append("<th>");
                        sb.append("<a href=\"/controller/MessageController/deleteMessageToAdmin?id=");
                        sb.append(toAdmin.getId() + "\"");
                        sb.append("data-role=\"button\">Delete");
                        sb.append("</a>");
                        sb.append("</th>");
                        sb.append("</tr>");
                    }
                    request.setAttribute("messageToAdminTable", sb.toString());
                    dispatcher = request.getServletContext().getRequestDispatcher("/adminMessages.jsp");
                } else {
                    dispatcher = request.getServletContext().getRequestDispatcher("/controller/NavigatorController/home");
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
