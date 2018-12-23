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

<div data-role="page" id="settings">
    <div data-role="header" data-position="fixed">
        <a href="Home.html" data-role="button" data-icon="back" class="ui-btn-left">back to home</a>
        <h1>Settings</h1>
    </div>
    <div data-role="content">
        <form name="changeWeight" method="get" action="serverSide">
            <div data-role="fieldcontain">
                <label for="Weight">change weight:
                    <input type="number" name="Weight" id="Weight" value=""/>
                    <input type="submit" name="submitweight" id="submitweight" value="Change" data-inline="true"/></label>
            </div>
        </form>

        <form name="changeHeight" method="get" action="serverSide">
            <div data-role="fieldcontain">
                <label for="Height">change height:
                    <input type="number" name="Height" id="Height" value=""/>
                    <input type="submit" name="submitheight" id="submitheight" value="Change" data-inline="true"/></label>
            </div>

        </form>
        <div data-role="fieldcontain">
            <form name="logout" method="post" action="http://localhost:8080/controller/UserController/logout">
                <input type="submit" name="submit" id="submitlogout" value="Log Out"/>
            </form>
        </div>
    </div>
    <div data-role="footer" data-position="fixed">

        <h3>FAT2FIT</h3>
    </div>
</div>
</div>

</body>
</html>
