<%-- 
    Document   : portal
    Created on : 23/03/2018, 20:51:52
    Author     : mateus
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ufpr.tads.tcc.beans.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Portal</title>
        <link rel="stylesheet" type="text/css" href="css/portal.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
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

        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item"><a class="nav-link" href="UsuarioServlet">Cadastro de  Clientes</a></li>
                    <li class="nav-item"><a class="nav-link" href="AtendimentoServlet?action=formNew">Efetuar Atendimento</a></li>
                    <li class="nav-item"><a class="nav-link" href="AtendimentoServlet">Mostrar Atendimentos</a></li>
                </ul>
            </div>
            <div class="navbar-collapse collapse w-100 order-2 dual-collapse2">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><p class="nav-link active">Nome: <c:out value="${usuario.nome}"/></p></li>
                    <li class="nav-item"><a class="nav-link" href="LoginServlet?action=logout">Sair</a></li>
                </ul>
            </div>
        </nav>
        <footer>
            <p class="small font-weight-light">Em caso de problemas contactar o administrador: <strong><c:out value="${configuracao.email}" /></strong></p>  
        </footer>
        </body>
</html>
