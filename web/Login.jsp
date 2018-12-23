<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 23/12/2018
  Time: 08:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FAT2FIT</title>
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>


</head>
<body>
<form name="login" method="post" action="/controller/UserController/login">
    <div data-role="dialog" id="login">
        <div data-role="header" data-position="fixed"  >
            <h1 style="color: black">Welcome to FAT2FIT</h1>
        </div>
        <div data-role="content" style="background: #d3d3d3">
            <div data-role="fieldcontain">
                <label for="UserName" style="color: black; font-size: large ">user name:</label>
                <input type="text" name="UserName" id="UserName" value="" style="background: white"/>
            </div>
            <div data-role="fieldcontain">
                <label for="password" style="color: black; font-size: large">password:</label>
                <input type="password" name="password" id="password" value="" autocomplete="off"  style="background: white" />
            </div>
            <div data-role="fieldcontain">
                <input type="submit" name="submit" value="Log In"/>
            </div>
            <div>
                <a href="/controller/NavigatorController/signUp" data-rel="dialog" data-transition="pop" style="color: black; font-size: large;"> Don't have a FAT2FIT account? create
                    yours now</a>
            </div>
            <div data-role="footer" data-position="fixed">
                <h3>FAT2FIT</h3>
            </div>
        </div>
    </div>
</form>
</body>
</html>
