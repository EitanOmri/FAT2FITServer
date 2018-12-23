<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 23/12/2018
  Time: 17:47
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
<form name="login" method="post" action="/controller/UserController/addUser">
    <div data-role="page" id="SignUp">
        <div data-role="header" data-position="fixed">
            <h1 style="color: black">Create your account</h1>
        </div>
        <div data-role="content" style="background: #d3d3d3">
            <div data-role="fieldcontain">
                <label for="UserName" style="color: black; font-size: large">user name:</label>
                <input type="text" name="UserName" id="UserName" value=""/>
            </div>
            <div data-role="fieldcontain">
                <label for="firstName" style="color: black; font-size: large"> first name:</label>
                <input type="text" name="firstName" id="firstName" value=""/>
            </div>
            <div data-role="fieldcontain">
                <label for="lastName" style="color: black; font-size: large">last name:</label>
                <input type="text" name="lastName" id="lastName" value=""/>
            </div>
            <div data-role="fieldcontain">
                <label for="email" style="color: black; font-size: large">e-mail:</label>
                <input type="email" name="email" id="email" value=""/>
            </div>
            <div data-role="fieldcontain">
                <label for="password" style="color: black; font-size: large">password:</label>
                <input type="password" name="password" id="password" value="" autocomplete="off"/>
            </div>
            <div data-role="fieldcontain">
                <label for="password" style="color: black; font-size: large"> confirm password:</label>
                <input type="password" name="confirmPassword" id="confirmPassword" value="" autocomplete="off"/>
            </div>
            <div data-role="fieldcontain" style="color: black; font-size: large">
                <label for="birthday">birthday:</label>
                <input type="date" name="birthday" id="birthday" value=""/>
            </div>
            <div data-role="fieldcontain">
                <label for="height" style="color: black; font-size: large">height:</label>
                <input type="number" name="height" id="height" value=""/>
            </div>
            <div data-role="fieldcontain">
                <label for="weight" style="color: black; font-size: large">weight:</label>
                <input type="number" name="weight" id="weight" value=""/>
            </div>
            <fieldset data-role="controlgroup" >
                <legend style="color: black; font-size: large">By Registering, you agree that you've read and accepted our <a href="Check.html" data-rel="dialog" data-transition="pop">User Agreement,</a>
                    you're at least 18 years old, and you consent to our Privacy Notice and receiving marketing communications from us.</legend>
                <label for="checkbox-2">I agree</label>
                <input type="checkbox" name="checkbox-2" id="checkbox-2">
            </fieldset>
            <div data-role="fieldcontain">
                <input type="submit" name="submit" value="Sign Up"/>
            </div>

            <div data-role="footer" data-position="fixed">
                <h3>FAT2FIT</h3>
            </div>
        </div>
    </div>
</form>
</body>
</html>