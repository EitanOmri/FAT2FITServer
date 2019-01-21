<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 27/12/2018
  Time: 09:11
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
<div data-role="page" id="AddAdmin">
    <div data-role="header" data-position="fixed">
        <a href="/controller/AdminController/home" data-role="button" data-icon="back" class="ui-btn-left">back to home</a>

        <h1>Add Admin</h1>
    </div>
    <div data-role="content">
        <%--showing in table all users without admins, with link to make one of them to be an admin--%>
        <table data-role="table" id="table-column-toggle" class="ui-responsive table-stroke"
               style="font-size: large">
            <thead>   
            <tr>
                <th data-priority="1">Username</th>
                <th data-priority="2">First name</th>         
                <th data-priority="3">Last name</th>  
                   <th data-priority="4">Make gym admin</th>       
            </tr>     
            </thead>     
            <tbody>
            <%=request.getAttribute("AddAdminHomeTable")%>
            </tbody>
               
        </table>


    </div>
    <div data-role="footer" data-position="fixed">
        <h3>FAT2FIT</h3>
    </div>
</div>


</body>
</html>
