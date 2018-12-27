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
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

</head>
<body>
<div data-role="page" id="AddAdmin">
    <div data-role="header" data-position="fixed">
        <a href="/controller/NavigatorController/home" data-role="button" data-icon="back" class="ui-btn-left">back to home</a>

        <h1>Add Admin</h1>
    </div>
    <div data-role="content">
        <table data-role="table" id="table-column-toggle" class="ui-responsive table-stroke"
               style="font-size: xx-large">
            <thead>   
            <tr>
                <th data-priority="10">Username</th>
                <th data-priority="10">First name</th>         
                <th data-priority="3">Last name</th>  
                   <th data-priority="5">Make gym admin</th>       
            </tr>     
            </thead>     
            <tbody>
            <%=request.getSession().getAttribute("AddAdminHomeTable")%>
            </tbody>
               
        </table>


    </div>
    <div data-role="footer" data-position="fixed">
        <h3>FAT2FIT</h3>
    </div>
</div>


</body>
</html>
