<%-- 
    Document   : clientesListar
    Created on : 07/04/2018, 15:55:25
    Author     : mateus
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ufpr.tads.tcc.beans.Evento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Eventos Listar</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/clienteListar.css">
        <link rel="stylesheet" type="text/css" href="css/ionicons.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.js"></script>
        <script src="js/clientesListar.js"></script>

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
                    <li class="nav-item"><a class="nav-link" href="ClientesServlet">Cadastro de Usuários</a></li>
                    <li class="nav-item"><a class="nav-link" href="EventoServlet">Cadastro de Eventos</a></li>
                </ul>
            </div>
            <div class="navbar-collapse collapse w-100 order-2 dual-collapse2">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><p class="nav-link active">Nome: <c:out value="${usuario.nome}"/></p></li>
                    <li class="nav-item"><a class="nav-link" href="LoginServlet?action=logout">Sair</a></li>
                </ul>
            </div>
        </nav>
                    
                    <c:if test="${!(empty param.msg)}" >
                        <c:set var="mensagem" value="${param.msg}" />
                    </c:if>
                    <c:if test="${!(empty requestScope.msg)}">
                        <c:set var="mensagem" value="${requestScope.msg}" />
                    </c:if>
                    <c:if test="${!(empty mensagem)}" >
                        <div class="row">
                            <div class="col-sm-4"></div>
                            <div class="col-sm-4 alert alert-danger" role="alert">
                                <p><c:out value="${mensagem}" /></p>
                            </div>
                        </div>
                    </c:if>
                    
        <a class="btn btn-outline-success" href="EventoServlet?action=formNew">Novo</a>
        <table class="table table-striped"><tr><th>Nome</th><th>Descrição</th><th>Visualizar</th><th>Alterar</th><th>Remover</th></tr>

        <c:forEach items="${eventos}" var="evento">
            <tr>
                <td><c:out value="${evento.nome}"/></td>
                <td><c:out value="${evento.descrição}"/></td>
                <td><a href="EventoServlet?action=show&id=<c:out value="${evento.id}"/>"><i class="ion-person"></i></a></td>
                <td><a href="EventoServlet?action=formUpdate&id=<c:out value="${evento.id}"/>"><i class="ion-edit"></i></a></td>
                <td><a href="EventoServlet?action=remove&id=<c:out value="${evento.id}"/>"><i class="ion-trash-a"></i></a></td>
            </tr>
        </c:forEach>

        </table>
    </body>
</html>
