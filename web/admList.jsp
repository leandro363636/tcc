<%-- 
    Document   : admList
    Created on : 13/04/2019, 12:39:05
    Author     : Gabriel
--%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
              <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta charset="UTF-8">
            <title>Administradores Listar</title>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
            <link rel="stylesheet" type="text/css" href="css/clienteListar.css">
            <link rel="stylesheet" type="text/css" href="css/ionicons.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.js"></script>
            <script src="js/clientesListar.js"></script>
            <script src="https://apis.google.com/js/platform.js" async defer></script>

        </head>
        <body>
         <%@ page errorPage="erro.jsp" %>
            <c:if test="${empty sessionScope.usuario}">
                <jsp:forward page="index.jsp">
                    <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
                </jsp:forward>
            </c:if>
            <c:if test="${sessionScope.usuario.tipo.equals(\"c\") || sessionScope.usuario.tipo.equals(\"o\")}">
                <jsp:forward page="EventoServlet?action=list">
                    <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
                </jsp:forward>
            </c:if>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
                <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item"><a class="nav-link" href="CompradorServlet">Cadastro de Usuários</a></li>
                        <li class="nav-item"><a class="nav-link" href="EventoServlet">Cadastro de Eventos</a></li>
                         <li class="nav-item"><a class="nav-link" href="AdministradorServlet">Cadastro de Admin</a></li>
                         <li class="nav-item"><a class="nav-link" href="OrganizadorServlet">Cadastro de Organizador</a></li>                         
                    </ul>
                </div>
                <div class="navbar-collapse collapse w-100 order-2 dual-collapse2">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item"><p class="nav-link active">Nome: <c:out value="${nome}"/></p></li>
                        <li class="nav-item" onclick="checkLoginState()"><a class="nav-link" href="LoginServlet?action=logout"  >Sair</a></li>
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

            <a class="btn btn-outline-success" href="AdministradorServlet?action=formNew">Novo</a>
            <table class="table table-striped"><tr><th>Nome</th><th>E-mail</th><th>Visualizar</th><th>Alterar</th><th>Remover</th></tr>

            <c:forEach items="${admin}" var="admin">
                <tr>
                    <td><c:out value="${admin.nome}"/></td>
                    <td><c:out value="${admin.email}"/></td>
                    <td><a href="AdministradorServlet?action=show&id=<c:out value="${admin.idAdministrador}"/>">Visualizar</a></td>
                    <td><a href="AdministradorServlet?action=formUpdate&id=<c:out value="${admin.idAdministrador}"/>">Alterar</a></td>
                    <td><a href="AdministradorServlet?action=<c:out value="${admin.ativo == true ? \"suspend\" : \"active\" }"/>&id=<c:out value="${admin.idAdministrador}"/>"><c:out value="${admin.ativo == true ? \"Suspender\" : \"Ativar\" }"/></a></td>
                </tr>
            </c:forEach>
            </table>
        </body>
    </html>
