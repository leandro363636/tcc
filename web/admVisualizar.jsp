<%-- 
    Document   : admVisualizar
    Created on : 13/04/2019, 15:05:16
    Author     : Gabriel
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="com.ufpr.tads.tcc.beans.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Visualizar Adm</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/clientesVisualizar.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.js"></script>
    </head>
    <body>
        <%@ page errorPage="erro.jsp" %>
        <c:if test="${empty sessionScope.usuario}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>
        <div class="container">
        <h1>Visualizar Adm</h1>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">Nome:</label><input class="form-control" type="text" name="nome" maxlength="100" value="${visualizaradm.nome}" disabled/><br/>
                </div>
                <div class="form-group col-md-9">
                    <label for="sobrenome">Sobrenome:</label><input class="form-control" type="text" name="sobrenome" value="${visualizaradm.sobrenome}" disabled/><br/>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-8">
                    <label for="email">E-mail:</label><input class="form-control" type="email" name="email" maxlength="100" value="${visualizaradm.email}" disabled/><br/>
                </div>
            </div>
            <a class="btn btn-outline-danger" href="AdminServlet">Cancelar</a>
        </div>
    </body>
</html>

