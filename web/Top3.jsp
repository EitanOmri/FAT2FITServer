<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 24/12/2018
  Time: 10:17
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

<div data-role="page" id="Top">
    <div data-role="header" data-position="fixed">
        <a href="/controller/NavigatorController/home" data-role="button" data-icon="back" class="ui-btn-left">back to home</a>

        <h1>Top 3- Weekly</h1>
    </div>
    <div data-role="content">
        <table data-role="table" id="table-column-toggle"  class="ui-responsive table-stroke">
                 <thead>
               <tr>
                     <th data-priority="10">Rank</th>
                   
                     <th data-priority="3">Username</th>
                   
                     <th data-priority="5">Calories</th>
                   </tr>
             </thead>
                 <tbody>
                 <%=request.getSession().getAttribute("topNTable")%>

             </tbody>
               </table>
    </div>
    <div data-role="footer" data-position="fixed">
        <h3>FAT2FIT</h3>
    </div>
</div>
</body>
</html>
