<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 24/12/2018
  Time: 09:45
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

    <style>
        .input-text
        {
            width: 500px;
            height:150px;
        }
    </style>
</head>
<body>
<div data-role="page" id="message">
    <div data-role="header" data-position="fixed">
        <a href="/controller/NavigatorController/home" data-role="button" data-icon="back" class="ui-btn-left">back to home</a>

        <h1>My messages</h1>
    </div>


    <div data-role="content">
        <form id="messageToAdmin" method="get" action="/controller/MessageController/addMessageToAdmin">
            <div data-role="fieldcontain">
                <label>send message to director of the gym:
                    <textarea  rows="15" name="content" style="width: 600px; height: 00px" form="messageToAdmin"></textarea>

                    <input type="submit" name="" id="send" value="send" data-inline="true"/>
                </label>
            </div>
        </form>
        <label>message from director of the gym</label>
        <div  id="textFromTheAdmin" data-role="fieldcontain">
            <table data-role="table" id="table-column-toggle" class="ui-responsive table-stroke"
                   style="font-size: xx-large">
                <thead>   
                <tr>
                    <th data-priority="10">Date</th>
                    <th data-priority="10">Content</th>         
                    </tr>     
                </thead>     
                <tbody>
                <%=request.getAttribute("messageFromAdminTable")%>
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