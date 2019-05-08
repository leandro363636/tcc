<%-- 
    Document   : OrganizadorForm
    Created on : 20/04/2019, 12:21:41
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
        <title><c:out value="${(!(empty param.form) || param.form == 'alterar') ? 'Alterar Organizador' : 'Novo Organizador'}"/></title>
        <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="js/slick/slick.css"/>
	<link rel="stylesheet" type="text/css" href="js/slick/slick-theme.css"/>
        <link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.min.css">
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
            <h1><c:out value="${(!(empty param.form) || param.form == 'alterar') ? 'Alterar Organizador' : 'Novo Organizador'}"/></h1>
            <form action="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? 'OrganizadorServlet?action=update' : 'OrganizadorServlet?action=new'}"/>" method="POST">
                <c:if test="${(!(empty param.form) || param.form == 'alterar')}" >
                    <div class="form-row">
                        <input type="hidden" name="id" value="<c:out value="${alterarOrg.idOrganizador}"/>">
                    </div>
                </c:if>
                <div class="form-row">
                   <div class="form-group col-md-9">
                        <label for="cnpj">CNPJ</label>
                        <input class="form-control" type="text" name="cnpj" maxlength="14" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.cnpj : ''}"/>" required/><br/>
                    </div>
                   <div class="form-group col-md-9">
                        <label for="cnpj">RG</label>
                        <input class="form-control" type="text" name="rg" maxlength="15" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.rg : ''}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-9">
                        <label for="nomeDaOrganizacao">NOME DA ORGANIZAÇÃO</label>
                        <input class="form-control" type="text" name="nomeDaOrganizacao" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.nomeOrganizador : ''}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="nome">Nome do RESPONSAVEL</label>
                        <input class="form-control" type="text" name="nomeDoResponsavel" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.nomeResponsavel : ''}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="nome">Sobrenome do RESPONSAVEL</label>
                        <input class="form-control" type="text" name="sobrenome" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.sobrenome : ''}"/>" required/><br/>
                    </div>
                    
                     <div class="form-group col-md-4">
                        <label for="email">EMAIL</label>
                        <input class="form-control" type="text" name="email" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.email : ''}"/>" required/><br/>
                    </div>
                    
                    <div class="form-group col-md-4">
                        <label for="senha">SENHA</label>
                        <input class="form-control" type="text" name="senha" maxlength="100" <c:out value="${((empty param.form) || param.form != \"alterar\") ? \"required\" : \"\"}"/>/><br/>
                    </div>
         
                    <div class="form-group col-md-4">
                        <label for="rua">Rua:</label>
                        <input class="form-control" type="text" name="rua" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarOrg.endereco.rua : \"\"}"/>"/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="numero">Número:</label>
                        <input class="form-control" type="number" name="numero" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarOrg.endereco.numero : \"\"}"/>"/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="cep">CEP:</label>
                        <input class="form-control" type="text" name="cep" maxlength="8" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarOrg.endereco.cep : \"\"}"/>"/><br/>
                    </div>
                    <div class="form-group col-md-1">
                        <label for="uf">UF:</label>
                        <select id="estado" name="uf" class="custom-select" required>
                        <c:forEach items="${estados}" var="estado">
                            <option <c:out value="${(alterarOrg.endereco.cidade.estado.id == estado.id) ? \"selected\" : \"\" }"/> value="<c:out value="${estado.id}"/>"><c:out value="${estado.sigla}"/></option>
                        </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-9">  
                        <label for="cidade">Cidade:</label>
                        <select id="cidade" name="cidade" class="custom-select" required>
                            <c:if test="${(!(empty param.form) || param.form == \"alterar\")}" >
                            <option selected value="<c:out value="${alterarOrg.endereco.cidade.id}"/>"><c:out value="${alterarOrg.endereco.cidade.nome}"/></option>
                            </c:if>
                        </select>
                    </div>
                                        
                </div>
                    
                <input class="btn btn-outline-success" type="submit" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"Alterar\" : \"Salvar\"}"/>"/>
                <a class="btn btn-outline-danger" href="OrganizadorServlet">Cancelar</a>
        </form>
        <!-- SCRIPTS -->
        <script type="text/javascript" src="js/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="js/popper/popper.min.js"></script>
        <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
        <script src="js/jquery.datetimepicker.full.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/organizadores.js"></script>
    </body>
</html>
