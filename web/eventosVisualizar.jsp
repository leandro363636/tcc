<%-- 
    Document   : clientesVisualizar
    Created on : 07/04/2018, 21:45:00
    Author     : mateus
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ufpr.tads.tcc.beans.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Evento Visualizar</title>
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
            <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${visualizarevento.dataInicio}" var="dataInicio" />
            <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${visualizarevento.dataFim}" var="dataFim" />
            <h1>Visualizar Evento</h1>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">Nome:</label>
                    <input class="form-control" type="text" name="nome" maxlength="100" value="<c:out value="${visualizarevento.nome}"/>" disabled/><br/>
                </div>
                <div class="form-group col-md-9">
                    <label for="sobrenome">Data Inicio:</label>
                    <input class="form-control" type="text" name="dataInicio" value="<c:out value="${dataInicio}"/>" disabled/><br/>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-8">
                    <label for="email">Data Fim:</label>
                    <input class="form-control" type="text" name="dataFim" value="<c:out value="${dataFim}"/>" disabled/><br/>
                </div>
                <div class="form-group col-md-4">
                    <label for="endereco">Endereço:</label>
                    <input class="form-control" type="text" name="endereco" value="<c:out value="${visualizarevento.endereco}"/>" disabled/><br/>
                </div>
                <div class="form-group col-md-4">
                    <label for="desc">Descrição:</label>
                    <textarea name="desc" disabled><c:out value="${visualizarevento.desc}"/></textarea>
                </div>
                <div class="form-group col-md-4">
                    <label for="aprovado">Aprovado:</label>
                    <input class="form-control" type="text" name="aprovado" value="<c:out value="${(visualizarevento.aprovado == true) ? \"Sim\" : \"Não\"}"/>" disabled/><br/>
                </div>
            </div>
            <a class="btn btn-outline-danger" href="EventoServlet">Cancelar</a>
        </div>
    </body>
</html>
