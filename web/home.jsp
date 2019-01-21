<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 20/12/2018
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FAT2FIT</title>
    <link rel="stylesheet" href="//code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="//code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>
<div data-role="page" id="Home" style="font-size: medium">
    <div data-role="header" data-position="fixed" data-theme="b" style="text-align: center">
        hello <%=session.getAttribute("userName")%>!
        <br/>
         your bmi:<%=request.getAttribute("bmi")%>
            <img src="<%=request.getContextPath()%>/IMG/<%=request.getAttribute("bmiColor")%>-light.png"
                 alt="<%=request.getAttribute("bmiColor")%>" height="25px" width="50px"/>
    </div>
    <div data-role="content" style="background-color: black">
        <ul data-role="listview" data-inset="true" data-theme="b" style="background-color:#a9a8a8;">
            <li><a href="/controller/StatisticsController/statistics" data-ajax="false">
                        <img src="<%=request.getContextPath()%>/IMG/statistics.png" alt="statistics"/>
                    <h2 style="font-size: 35px;color: white">Statistics</h2>
                    <p style="font-size: 15px;color: white">lets show my ability</p></a>
                    
            </li>
                
            <li><a href="/controller/TrainingController/workoutMenu">
                        <img src="<%=request.getContextPath()%>/IMG/workout.png" alt="workout list"/>
                    <h2 style="font-size: 35px;color: white">Workout</h2>
                    <p style="font-size: 15px;color: white">training list for you</p></a>
                    
            </li>
                
            <li><a href="/controller/MyDayController/myDayMenu">
                        <img src="<%=request.getContextPath()%>/IMG/myDay.png" alt="my day"/>
                    <h2 style="font-size: 35px;color: white">My day</h2>
                    <p style="font-size: 15px;color: white">build your own workout plan</p></a>
                    
            </li>
               
            <li><a href="/controller/StatisticsController/top3" data-rel="dialog" data-ajax="false">
                        <img src="<%=request.getContextPath()%>/IMG/top.png" alt="Top 3"/>
                    <h2 style="font-size: 35px;color: white">Top 3</h2>
                    <p style="font-size: 15px;color: white">the three best gymnasts of the week are...</p></a>
                    
            </li>

            <%=request.getAttribute("messageLink")%>
             
            <li><a href="/controller/HistoryController/myHistory">
                        <img src="<%=request.getContextPath()%>/IMG/MyHistory.png" alt=" MyHistory"/>
                    <h2 style="font-size: 35px;color: white">MyHistory</h2>
                    <p style="font-size: 15px;color: white">view your history</p></a>
                    
            </li>
            <li data-icon="gear"><a href="/controller/SettingsController/settings">
                        <img src=<%=request.getContextPath()%>/IMG/settings.png alt=" settings"/>
                    <h2 style="font-size: 35px;color: white">Settings</h2>
                    <p style="font-size: 15px;color: white">change your details</p></a>  
            </li>
            <%=request.getAttribute("adminLink")%>
        </ul>

    </div>
    <div data-role="footer" data-position="fixed" data-theme="b">
        <h3>FAT2FIT</h3>
    </div>
</div>
</body>
</html>
