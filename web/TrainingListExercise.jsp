<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 23/12/2018
  Time: 09:41
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
<div data-role="page" id="TrainingListExercise">
    <div data-role="header" data-position="fixed" data-theme="c">
        <a href="/controller/TrainingController/workoutMenu" data-role="button" data-icon="back" class="ui-btn-left">back
            to training
            list</a>

        <h1><%=session.getAttribute("trainingListName")%></h1>
    </div>
    <div data-role="content">
        <table data-role="table" id="table-column-toggle"  style="color:black"> 
            <thead>   
            <tr>
                <th data-priority="1">Exercise</th>
                <th data-priority="2">Sets</th>
                <th data-priority="3">Reps</th>       
            </tr>     
            </thead>
            <tbody>     
                    <%=session.getAttribute("exerciseList")%>
            </tbody>
        </table>
     <form id="TrainingListExerciseForm" method="get" action="/controller/HistoryController/addTrainingList?id=<%=session.getAttribute("trainingListId")%>">
         <input type="submit" id="submit" value="Add" data-theme="b"/>
     </form>
    </div>


    <div data-role="footer" data-position="fixed">
        <h3>FAT2FIT</h3>
    </div>
</div>
</body>
</html>
