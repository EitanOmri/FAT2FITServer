<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 27/12/2018
  Time: 08:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FAT2FIT</title>
    <link rel="stylesheet" href="//code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="//code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

</head>
<body>
<div data-role="page" id="admin" style="font-size: x-large">
    <div data-role="header" data-position="fixed" data-theme="b">
        <a href="/controller/NavigatorController/home" data-role="button" data-icon="back" class="ui-btn-left">back to
            home</a>
        <h1>Admin</h1>
    </div>
    <div data-role="content" style="background-color: black">
        <ul data-role="listview" data-inset="true" data-theme="b" style="background-color:#a9a8a8;">   
            <%--add exercise--%>
            <li><a href="/controller/AdminController/manageExerciseHome" data-ajax="false">
                     <h2 style="font-size: 30px;color: white">Add Exercises</h2>
                    </a>    
            </li>
            <%--add training list--%>
            <li><a href="/controller/AdminController/manageTraininigListHome">
                      <h2 style="font-size: 30px;color: white">Add Training List</h2>
                    </a>   
            </li>
            <%--add admin--%>
            <li><a href="/controller/AdminController/addAdminHome">
                       <h2 style="font-size: 30px;color: white">Add admin</h2>
                    </a>
            </li>
            <%--manage the message page--%>
            <li style="background-color:#a9a8a8;"></li>
            <li><a href="/controller/AdminController/managerMessage">
                       <h2 style="font-size: 30px;color: white">Manage Messages</h2>
                    </a>   
            </li>

        </ul>

    </div>
    <div data-role="footer" data-position="fixed" data-theme="b">
        <h3>FAT2FIT</h3>
    </div>
</div>
</body>
</html>