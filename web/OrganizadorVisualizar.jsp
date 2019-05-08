<%-- 
    Document   : OrganizadorVisualizar
    Created on : 20/04/2019, 12:22:05
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ufpr.tads.tcc.beans.Organizador"%>
<%@page import="com.ufpr.tads.tcc.beans.Usuario"%>
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
            <c:if test="${sessionScope.usuario.tipo.equals(\"c\")}">
                <jsp:forward page="EventoServlet?action=list">
                    <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
                </jsp:forward>
            </c:if>
        <div class="container">
        <h1>Visualizar Organizador</h1>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">RG</label><input class="form-control" type="text" name="cnpj" maxlength="100" value="${visualizarorganizador.rg}" disabled/><br/>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">CNPJ</label><input class="form-control" type="text" name="cnpj" maxlength="100" value="${visualizarorganizador.cnpj}" disabled/><br/>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">Nome da Organização:</label><input class="form-control" type="text" name="nome" maxlength="100" value="${visualizarorganizador.nomeOrganizador}" disabled/><br/>
                </div>
                <div class="form-group col-md-9">
                    <label for="sobrenome">Nome do Responsavel:</label><input class="form-control" type="text" name="sobrenome" value="${visualizarorganizador.nomeResponsavel}" disabled/><br/>
                </div>
                <div class="form-group col-md-9">
                    <label for="sobrenome">Sobrenome do Responsavel:</label><input class="form-control" type="text" name="sobrenome" value="${visualizarorganizador.sobrenome}" disabled/><br/>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-8">
                    <label for="email">E-mail:</label><input class="form-control" type="email" name="email" maxlength="100" value="${visualizarorganizador.email}" disabled/><br/>
                </div>
            </div>
            <div class="form-group col-md-4">
                <label for="rua">Rua:</label>
                <input class="form-control" type="text" name="rua" value="<c:out value="${ visualizarorganizador.endereco.rua }"/>" disabled/><br/>
            </div>
            <div class="form-group col-md-4">
                <label for="numero">Número:</label>
                <input class="form-control" type="number" name="numero" value="<c:out value="${ visualizarorganizador.endereco.numero }"/>" disabled/><br/>
            </div>
            <div class="form-group col-md-4">
                <label for="cep">CEP:</label>
                <input class="form-control" type="text" name="cep" maxlength="8" value="<c:out value="${ visualizarorganizador.endereco.cep }"/>" disabled/><br/>
            </div>
            <div class="form-group col-md-1">
                <label for="uf">UF:</label>
                <select id="estado" name="uf" class="custom-select" disabled>
                    <option><c:out value="${visualizarorganizador.endereco.cidade.estado.sigla}"/></option>
                </select>
            </div>
            <div class="form-group col-md-9">  
                <label for="cidade">Cidade:</label>
                <select id="cidade" name="cidade" class="custom-select" disabled>
                    <option><c:out value="${visualizarorganizador.endereco.cidade.nome}"/></option>
                </select>
            </div>
            <a class="btn btn-outline-danger" href="OrganizadorServlet">Cancelar</a>
        </div>
</html>
