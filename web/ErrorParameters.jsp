<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 01/01/2019
  Time: 11:18
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
<div data-role="page" id="Home" style="font-size: x-large">
    <div data-role="header" data-position="fixed">
        <h1>Error page</h1>
    </div>
    <div data-role="content" style="size: 60px ">
        We detect bad parameters. You may ba able to try again !
        <a href="/controller/NavigatorController/home" data-role="button">back home
        </a>
        <img src="<%=request.getContextPath()%>/IMG/error.jpg" alt="error" style="height: 1500px; width: 1000px">
    </div>
</div>
<div data-role="footer" data-position="fixed" data-theme="b">
    <a href="https://www.freepik.com/free-photos-vectors/business">Business image created by Kstudio - Freepik.com</a>
    <h3>FAT2FIT</h3>
</div>
</body>
</html>