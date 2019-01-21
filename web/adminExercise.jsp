<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 30/12/2018
  Time: 08:40
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
<div data-role="page" id="adminExercise">
    <div data-role="header" data-position="fixed">
        <a href="/controller/AdminController/home" data-role="button" data-icon="back" class="ui-btn-left">back to
            home</a>
        <h1>Add exercise</h1>
    </div>
    <div data-role="content">
        <%--form to add a new exercise--%>
        <form name="addExercise" method="get" action="/controller/AdminController/addExercise">
            <div data-role="fieldcontain">
                <label for="exerciseName">Exercise's name:
                    <input type="text" name="exerciseName" id="exerciseName" value=""/></label>
            </div>
            <div data-role="fieldcontain">
                <label for="cal">Cal per reps:
                    <input type="number" name="cal" id="cal" value=""/>
                </label>
            </div>
            <div data-role="fieldcontain">
                <label for="category">Category:
                    <select name="category" id="category">
                        <%--get all categories from DB--%>
                        <%=request.getAttribute("listOfCategories")%>
                    </select>
                    <input type="submit" name="submit" id="submit" value="Add" data-inline="true"/></label>
            </div>
        </form>
    </div>
    <div data-role="footer" data-position="fixed">
        <h3>FAT2FIT</h3>
    </div>
</div>
</body>
</html>