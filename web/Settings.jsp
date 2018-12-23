<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 23/12/2018
  Time: 14:33
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
<div data-role="page" id="settings">
    <div data-role="header" data-position="fixed">
        <a href="/controller/NavigatorController/home" data-role="button" data-icon="back" class="ui-btn-left">back to home</a>
        <h1>Settings</h1>
    </div>
    <div data-role="content">
        <form name="changeWeight" method="get" action="/controller/UserController/update">
            <div data-role="fieldcontain">
                <label for="Weight">change weight:
                    <input type="number" name="weight" id="weight" value=""/></label>
            </div>
                <div data-role="fieldcontain">
                <label for="Height">change height:
                    <input type="number" name="height" id="height" value=""/>
                    <input type="submit" name="submit" id="submit" value="Change" data-inline="true"/></label>
            </div>
        </form>
        <div data-role="fieldcontain">
            <form name="logout" method="post" action="/controller/UserController/logout">
                <input type="submit" name="submit" id="submitlogout" value="Log Out"/>
            </form>
        </div>
    </div>
    <div data-role="footer" data-position="fixed">

        <h3>FAT2FIT</h3>
    </div>
</div>

</body>
</html>