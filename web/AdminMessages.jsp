<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 30/12/2018
  Time: 10:30
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

    <style>
        .input-text
        {
            width: 500px;
            height:150px;
        }
    </style>
</head>
<body>
<div data-role="page" id="adminMessage">
    <div data-role="header" data-position="fixed">
        <a href="/controller/AdminController/home" data-role="button" data-icon="back" class="ui-btn-left">back to home</a>

        <h1>My messages</h1>
    </div>

    <div data-role="content">
        <form id="messageFromAdmin" method="get" action="/controller/MessageController/addMessageFromAdmin">
            <div data-role="fieldcontain">
                <label style="font-size: medium">send message to users:
                    <textarea  rows="15" name="content" style="width: 600px; height: 00px" form="messageFromAdmin"></textarea>

                    <input type="submit" name="" id="send" value="send" data-inline="true"/>
                </label>
            </div>
        </form>
        <label style="font-size: large">message from users</label>
        <div  id="textToTheAdmin" data-role="fieldcontain">
            <table data-role="table" id="table-column-toggle" class="ui-responsive table-stroke"
                   style="font-size: medium">
                <thead>   
                <tr>
                    <th data-priority="1">Date</th>
                    <th data-priority="2">Username</th>
                    <th data-priority="3">Content</th> 
                    <th data-priority="4"></th>         
                </tr>     
                </thead>     
                <tbody>
                <%=request.getAttribute("messageToAdminTable")%>
                </tbody>
                   
            </table>
        </div>
        <label style="font-size: large">my messages to the users</label>
        <div  id="historyFromTheAdmin" data-role="fieldcontain">
            <table data-role="table" id="historyFromTheAdminTable" class="ui-responsive table-stroke"
                   style="font-size: medium">
                <thead>   
                <tr>
                    <th data-priority="1">Date</th>
                    <th data-priority="2">Content</th> 
                    <th data-priority="3"></th>         
                </tr>     
                </thead>     
                <tbody>
                <%=request.getAttribute("historyMessageFromAdminTable")%>
                </tbody>
                   
            </table>
        </div>
    </div>
    <div data-role="footer" data-position="fixed">
        <h3>FAT2FIT</h3>
    </div>

</div>
</body>
</html>
