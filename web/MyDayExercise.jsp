<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 23/12/2018
  Time: 13:50
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
<div data-role="page" id="myDayExercisePage">
    <div data-role="header" data-position="fixed">
        <a href="/controller/MyDayController/myDayMenu" data-role="button" data-icon="back" class="ui-btn-left">back to my day</a>
        <h1><%=session.getAttribute("categoryName")%></h1>
    </div>
    <div data-role="content">
        <form id="myDayExerciseForm" method="get" action="/controller/HistoryController/addMyDay">
            <table data-role="table" id="table-column-toggle"  class="ui-responsive table-stroke">
                     <thead>
                   <tr>      
                         <th data-priority="10">Exercise</th>
                         <th data-priority="3">Sets</th>       
                         <th data-priority="5">Reps</th>       
                       </tr>
                 </thead>
                     <tbody>
                    <%=session.getAttribute("categoriesForm")%>
                 </tbody>
                   </table>

            <input type="submit" id="submit" value="Add"/>
        </form>
    </div>
    <div data-role="footer" data-position="fixed">
        <h3>FAT2FIT</h3>
    </div>
</div>
</body>
</html>
