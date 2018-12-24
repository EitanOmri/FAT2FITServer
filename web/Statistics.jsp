<%--
  Created by IntelliJ IDEA.
  User: omris
  Date: 24/12/2018
  Time: 14:26
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
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <script type="text/javascript">

        window.onload = function() {

            var chart = new CanvasJS.Chart("chartContainer", {
                title: {
                    text: "burn cal chart- weekly"
                },
                axisX: {
                    title: "day"
                },
                axisY: {
                    title: "cal"
                },
                data: [{
                    type: "column",
                    yValueFormatString: "#,##0#cal ",
                    dataPoints: <%=request.getSession().getAttribute("dataPointsWeekly")%>
                }]
            });
            chart.render();
        }
    </script>
</head>


<body>
<div data-role="page" id="statistics">
    <div data-role="header" data-position="fixed">
        <a href="/controller/NavigatorController/home" data-role="button" data-icon="back" class="ui-btn-left">back to home</a>

        <h1>My statistics</h1>
    </div>
    <div data-role="content">
        <div data-role="fieldcontain">
            <div id="chartContainer" style="height: 300px; width: 100%;"></div>
        </div>
        <div data-role="footer" data-position="fixed">
            <h3>FAT2FIT</h3>
        </div>
    </div>
</div>
</body>
</html>