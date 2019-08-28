<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ufpr.tads.tcc.beans.Usuario"%>
<%@page import="com.ufpr.tads.tcc.beans.Comprador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Visualizar Comprador</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/clientesVisualizar.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.js"></script>
    </head>
    <body>
    <fmt:formatDate pattern="dd/MM/yyyy" value="${visualizarcomprador.dataNascimento}" var="dataNascimento" />
    <%@ page errorPage="erro.jsp" %>
    <c:if test="${empty sessionScope.usuario}">
        <jsp:forward page="index.jsp">
            <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        </jsp:forward>
    </c:if>
    <div class="container">
        <h1>Visualizar Comprador</h1>
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="nome">Nome:</label><input class="form-control" type="text" name="nome" maxlength="100" value="${visualizarcomprador.nome}" disabled/><br/>
            </div>
            <div class="form-group col-md-9">
                <label for="sobrenome">Sobrenome:</label><input class="form-control" type="text" name="sobrenome" value="${visualizarcomprador.sobrenome}" disabled/><br/>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-8">
                <label for="email">E-mail:</label><input class="form-control" type="email" name="email" maxlength="100" value="${visualizarcomprador.email}" disabled/><br/>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-8">
                <label for="rg">RG:</label>
                <input  class="form-control" type="text" name="rg" maxlength="9" value="<c:out value="${visualizarcomprador.rg}"/>" id="rg" disabled/><br/>
            </div>
            <div class="form-group col-md-4">
                <label for="cpf">CPF:</label>
                <input class="form-control" type="text" name="cpf"  maxlength="11" value="<c:out value="${visualizarcomprador.cpf}"/>" id="cpf" disabled/><br/>
            </div>
            <div class="form-group col-md-4">
                <label for="cpf">Data de nascimento:</label>
                <input class="form-control" type="text" name="dataNascimento" value="<c:out value="${dataNascimento}"/>" id="dataNascimento" disabled/><br/>
            </div>
            <div class="form-group col-md-4">
                <label for="rua">Rua:</label>
                <input class="form-control" type="text" name="rua" value="<c:out value="${visualizarcomprador.endereco.rua}"/>" disabled/><br/>
            </div>
            <div class="form-group col-md-4">
                <label for="numero">Número:</label>
                <input class="form-control" type="number" name="numero" value="<c:out value="${visualizarcomprador.endereco.numero}"/>" disabled/><br/>
            </div>
            <div class="form-group col-md-4">
                <label for="cep">CEP:</label>
                <input class="form-control" type="text" name="cep" maxlength="8" value="<c:out value="${visualizarcomprador.endereco.cep}"/>" disabled/><br/>
            </div>
            <div class="form-group col-md-1">
                <label for="uf">UF:</label>
                <select id="estado" name="uf" class="custom-select" disabled>
                    <option><c:out value="${visualizarcomprador.endereco.cidade.estado.sigla}"/></option>
                </select>
            </div>
            <div class="form-group col-md-9">  
                <label for="cidade">Cidade:</label>
                <select id="cidade" name="cidade" class="custom-select" disabled>
                    <option><c:out value="${visualizarcomprador.endereco.cidade.nome}"/></option>
                </select>
            </div>
        </div>

        <a class="btn btn-outline-danger" href="CompradorServlet">Cancelar</a>
    </div>
</body>
</html>
