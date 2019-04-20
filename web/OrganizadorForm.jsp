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
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title><c:out value="${(!(empty param.form) || param.form == 'alterar') ? 'Alterar Evento' : 'Novo Evento'}"/></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/clientesAlterar.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/clientesAlterar.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/jquery.mask.js" type="text/javascript"></script>
        <script src="js/datepicker.js" type="text/javascript"></script>
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
                        <input class="form-control" type="text" name="cnpj" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.cnpj : ''}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-9">
                        <label for="nomeDaOrganizacao">NOME DA ORGANIZAÇÃO</label>
                        <input class="form-control" type="text" name="nomeDaOrganizacao" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.nomeDaOrganizacao : ''}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="nome">Nome do RESPONSAVEL</label>
                        <input class="form-control" type="text" name="nomeDoResponsavel" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.nomeDoResponsavel : ''}"/>" required/><br/>
                    </div>
          
                    <div class="form-group col-md-4">
                        <label for="rg">rg</label>
                        <input class="form-control" type="text" name="rgDoResponsavel" maxlength="11" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.rgDoResponsavel : ''}"/>" required/><br/>
                    </div>
                    
                     <div class="form-group col-md-4">
                        <label for="email">EMAIL</label>
                        <input class="form-control" type="text" name="email" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.email : ''}"/>" required/><br/>
                    </div>
                    
                    <div class="form-group col-md-4">
                        <label for="senha">SENHA</label>
                        <input class="form-control" type="text" name="senha" maxlength="20" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.senha : ''}"/>" required/><br/>
                    </div>
         
                    
                    <div class="form-group col-md-4">
                        <label for="endereco">endereco</label>
                        <input class="form-control" type="text" name="endereco" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.endereco : ''}"/>" required/><br/>
                    </div>
                    
                     <div class="form-group col-md-4">
                        <label for="tipo">Tipo:</label>
                        <input class="form-control" type="int" name="tipo" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarOrg.tipo : ''}"/>" required/><br/>
                    </div>
                    
                </div>
                    
                  <input class="btn btn-outline-success" type="submit" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"Alterar\" : \"Salvar\"}"/>"/>
                <a class="btn btn-outline-danger" href="OrganizadorServlet">Cancelar</a>
        </form>
    </body>
</html>
