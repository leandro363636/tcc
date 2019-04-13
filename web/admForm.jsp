<%-- 
    Document   : admForm
    Created on : 05/04/2019, 22:17:10
    Author     : Gabriel
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ufpr.tads.tcc.beans.Admin"%>
<%@page import="com.ufpr.tads.tcc.beans.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


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
              <jsp:useBean id="now" class="java.util.Date" />
            <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${alterarAdm.dataNascimento}" var="datanascimento" />
        <c:if test="${empty sessionScope.usuario}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>
        <div class="container">
            <h1><c:out value="${(!(empty param.form) || param.form == 'alterar') ? 'Alterar Admin' : 'Novo Admin'}"/></h1>
            <form action="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? 'AdminServlet?action=update' : 'AdminServlet?action=new'}"/>" method="POST">
                <c:if test="${(!(empty param.form) || param.form == 'alterar')}" >
                    <div class="form-row">
                        <input type="hidden" name="id" value="<c:out value="${alterarAdm.id}"/>">
                    </div>
                </c:if>
                <div class="form-row">
                   <div class="form-group col-md-9">
                        <label for="sobrenome">Email do administrador</label>
                        <input class="form-control" type="text" name="email" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarAdm.email : ''}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-9">
                        <label for="senha">Senha do administrador</label>
                        <input class="form-control" type="text" name="senha" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarAdm.senha : ''}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="nome">Nome do administrador:</label>
                        <input class="form-control" type="text" name="nome" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarAdm.nome : ''}"/>" required/><br/>
                    </div>
                   <div class="form-group col-md-4">
                        <label for="sobrenome">sobrenome:</label>
                        <input class="form-control" type="text" name="sobrenome" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarAdm.sobrenome : ''}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="rg">rg</label>
                        <input class="form-control" type="text" name="rg" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarAdm.rg : ''}"/>" required/><br/>
                    </div>
                     <div class="form-group col-md-4">
                        <label for="cpf">cpf</label>
                        <input class="form-control" type="text" name="cpf" maxlength="11" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarAdm.cpf : ''}"/>" required/><br/>
                    </div>
                    
                    
                    
                    
                    
                     <div class="form-group col-md-8">
                        <label for="data">Data </label>
                        <input class="form-control datetimepicker" type="date" name="data" value="<c:out value="${dataNascimento}"/>"/><br/>
                    </div>
                    
                    
                    
                     <div class="form-group col-md-4">
                        <label for="tipo">Tipo:</label>
                        <input class="form-control" type="text" name="tipo" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarAdm.tipo : ''}"/>" required/><br/>
                    </div>
                    
                    
                    <div class="form-group col-md-4">
                        <label for="endereco">endereco</label>
                        <input class="form-control" type="text" name="endereco" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? alterarAdm.endereco : ''}"/>" required/><br/>
                    </div>
                </div>
                    
                    
                    
                <input class="btn btn-outline-success" type="submit" value="<c:out value="${(!(empty param.form) || param.form == 'alterar') ? 'Alterar' : 'Salvar'}"/>"/>
                    <a class="btn btn-outline-danger" href="AdmServlet">Cancelar</a>
                    
                    <a type="submit" onclick="inserirAmin()">TEste</a>
            </form>
        </div>
    </body>  
</html>
