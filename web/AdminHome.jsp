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
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

</head>
<body>
<div data-role="page" id="manager" style="font-size: x-large">
    <div data-role="header" data-position="fixed" data-theme="b">
        <h1>Manager</h1>
    </div>
    <div data-role="content" style="background-color: black">
        <ul data-role="listview" data-inset="true" data-theme="b" style="background-color:#a9a8a8;">
               
            <li><a href="MangeExercises.html" data-ajax="false">
                     <h2 style="font-size: 40px;color: white">Manage Exercises</h2>
                    <p style="font-size: 20px;color: white">lets show my ability</p></a>
                    
            </li>
                
            <li><a href="ManageTraininigList.html">
                      <h2 style="font-size: 40px;color: white">Manage Training List</h2>
                    <p style="font-size: 20px;color: white">training list for you</p></a>
                    
            </li>
                
            <li><a href="/controller/AdminController/addAdminHome">
                       <h2 style="font-size: 40px;color: white">Add admin</h2>
                    <p style="font-size: 20px;color: white">build your own workout plan</p></a>
                    
            </li>
               
            <li style="font-size: 50px"><a href="ManageMessages" data-rel="dialog">
                       <h2 style="font-size: 40px;color: white">Manage Messages</h2>
                    <p style="font-size: 20px;color: white">the three best gymnasts of the week are...</p></a>
                    
            </li>

        </ul>

    </div>
    <div data-role="footer" data-position="fixed" data-theme="b">
        <h3>FAT2FIT</h3>
    </div>
</div>
</body>
</html>