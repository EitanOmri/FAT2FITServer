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
    <link rel="stylesheet" href="//code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="//code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <script type="text/javascript">
        window.onload = function() {
            // function for canvasJs library
            var chart = new CanvasJS.Chart("chart", {
                theme: "dark1", // "light1", "light2", "dark2"
                animationEnabled: true,
                title: {
                    text: "Top 3 users in the gym"
                },
                axisY: {
                    suffix: "Cal"
                },
                axisX: {
                    title: "users"
                },
                data: [{
                    type: "column",
                    yValueFormatString: "#,##0\"\"",
                    dataPoints:  <%=request.getAttribute("topNTable")%>
                }]
            });
            chart.render();

        }
    </script>
</head>
<body>

<div data-role="page" id="Top">
    <div data-role="header" data-position="fixed">
        <a href="/controller/NavigatorController/home" data-role="button" data-icon="back" class="ui-btn-left">back to home</a>

        <h1>Top 3- Weekly</h1>
    </div>
    <div data-role="content">
        <%--showing the chart--%>
        <div id="chart" style="height: 370px; width: 100%;"></div>
      </div>
    <div data-role="footer" data-position="fixed">
        <h3>FAT2FIT</h3>
    </div>
</div>
</body>
</html>
