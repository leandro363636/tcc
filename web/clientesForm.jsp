<%-- 
    Document   : clientesAlterar
    Created on : 07/04/2018, 18:55:04
    Author     : mateus
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ufpr.tads.web2.beans.Cliente"%>
<%@page import="com.ufpr.tads.web2.beans.LoginBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Clientes Alterar</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/clientesAlterar.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/jquery.mask.js" type="text/javascript"></script>
        <script src="js/clientesForm.js" type="text/javascript"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </head>
    <body>
        <%@ page errorPage="erro.jsp" %>
        <c:if test="${empty sessionScope.usuario}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>
        <div class="container">
            <fmt:formatDate pattern="dd/MM/yyyy" value="${alterarcliente.data}" var="data" />
            <h1><c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"Alterar Cliente\" : \"Novo Cliente\"}"/></h1>
            <form action="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"ClientesServlet?action=update\" : \"ClientesServlet?action=new\"}"/>" method="POST">
                <c:if test="${(!(empty param.form) || param.form == \"alterar\")}" >
                    <input type="hidden" name="id" value="<c:out value="${alterarcliente.id}"/>">
                </c:if>
                <div class="form-row">
                    <div class="form-group col-md-10">
                        <label for="nome">Nome:</label>
                        <input class="form-control" type="text" name="nome" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcliente.nome : \"\"}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="cpf">CPF:</label>
                        <input id="inputcpf" class="form-control cpf" type="text" name="cpf" maxlength="11" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcliente.cpf : \"\"}"/>" required/><div id="divcpf"></div><br/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-10">
                        <label for="email">E-mail:</label>
                        <input class="form-control" type="email" name="email" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcliente.email : \"\"}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="data">Data Nascimento:</label>
                        <input class="form-control data" id="datepicker" type="text" name="data" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? data : \"\"}"/>" required/><br/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <label for="cep">CEP:</label>
                        <input class="form-control cep" type="text" name="cep" maxlength="8" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcliente.cep : \"\"}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-10">
                        <label for="rua">Rua:</label>
                        <input class="form-control" type="text" name="rua" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcliente.rua : \"\"}"/>" required/><br/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <label for="numero">Número:</label>
                        <input class="form-control" type="number" name="numero" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcliente.numero : \"\"}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-1">
                        <!--<label for="uf">UF:</label><input class="form-control" type="text" name="uf" maxlength="2" value=""/><br/>-->
                        <label for="uf">UF:</label>
                        <select id="estado" name="uf" class="custom-select" required>
                        <c:forEach items="${estados}" var="estado">
                            <option <c:out value="${(alterarcliente.cidade.estado.id == estado.id) ? \"selected\" : \"\" }"/> value="<c:out value="${estado.id}"/>"><c:out value="${estado.sigla}"/></option>
                        </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-9">  
                        <label for="cidade">Cidade:</label>
                        <select id="cidade" name="cidade" class="custom-select" required>
                            <c:if test="${(!(empty param.form) || param.form == \"alterar\")}" >
                            <option selected value="<c:out value="${alterarcliente.cidade.id}"/>"><c:out value="${alterarcliente.cidade.nome}"/></option>
                            </c:if>
                        </select>
                    </div>
                </div>
                <input class="btn btn-outline-success" type="submit" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"Alterar\" : \"Salvar\"}"/>"/>
                <a class="btn btn-outline-danger" href="ClientesServlet">Cancelar</a>
            </form>
        </div>
    </body>
</html>
