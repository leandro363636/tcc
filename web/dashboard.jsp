<%-- 
    Document   : dashboard
    Created on : 30/05/2019, 20:18:53
    Author     : mateus
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

        <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/compradorListar.css"/>
        <link rel="stylesheet" type="text/css" href="css/datepicker/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="css/datepicker/datepicker.min.css"/>

        <script type="text/javascript" src="js/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.mask.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <title>Dashboard | fastTicket</title>
    </head>
    <body>
        <%@ page errorPage="erro.jsp" %>
        <c:if test="${empty sessionScope.usuario}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>

        <c:if test="${sessionScope.usuario.tipo.equals(\"c\")}">
            <jsp:forward page="EventoServlet?action=list">
                <jsp:param name="msg" value="Usuário não autorizado a acessar esta modalidade do sistema." />
            </jsp:forward>
        </c:if>
        <h1>Total de Vendas: <c:out value="${total}"/></h1>
        <h2>Média de Vendas: <c:out value="${media}"/></h2>
        <div id="chart_div"></div>
    </body>
    
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Eventos');
        data.addColumn('number', 'Vendas');
        
        data.addRows([
          <c:forEach items="${totalEventos}" var="evento">
            ['<c:out value="${evento.nome}"/>', <c:out value="${evento.total}"/>],
          </c:forEach>
        ]);

        // Set chart options
        var options = {'title':'Vendas/Eventos',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
</html>
