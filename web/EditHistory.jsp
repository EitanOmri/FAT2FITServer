<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 23/12/2018
  Time: 11:42
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
<div data-role="page" id="absEx">

    <div data-role="header" data-position="fixed">
        <a href="MyHistory.html" data-role="button" data-icon="back" class="ui-btn-left">back to my history</a>
        <h1>Edit</h1>
    </div>
    <div data-role="content" style="height: 1000px;">
        <form id="editHistory" method="get" action="/controller/HistoryController/updateAction">
            <table data-role="table" id="table-column-toggle"  class="ui-responsive table-stroke" style="font-size: xx-large">
                     <thead>
                   <tr>
                         <th data-priority="3">Sets</th>       
                         <th data-priority="5">Reps</th>       
                       </tr>
                 </thead>
                     <tbody>
                   <tr>
                         <td><input type="number" id="sets"  name ="sets" value="<%= session.getAttribute("setsEditOrView")%>"/></td>
                        <td><input type="number" id="reps" name ="reps" value="<%= session.getAttribute("repsEditOrView")%>"/></td>
                       </tr>
                  
                 </tbody>
                   </table>

            <input type="submit" id="submitEdit" value="Edit"/>

        </form>
        <form id="delete" method="get" action="/controller/HistoryController/deleteAction">
            <input type="submit" id="submitDelete" value="Delete"/>
        </form>

    </div>
</div>

</body>
</html>

