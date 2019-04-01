<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ufpr.tads.tcc.beans.Usuario"%>
<%@page import="com.ufpr.tads.tcc.beans.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title><c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"Alterar Evento\" : \"Novo Evento\"}"/></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/clientesAlterar.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/jquery.mask.js" type="text/javascript"></script>
        <script src="js/eventos.js" type="text/javascript"></script>
        <script src="js/jquery.datetimepicker.full.js" type="text/javascript"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.min.css"
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
            <jsp:useBean id="now" class="java.util.Date" />
            <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${alterarevento.dataInicio}" var="dataInicio" />
            <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${alterarevento.dataFim}" var="dataFim" />
            <h1><c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"Alterar Evento\" : \"Novo Evento\"}"/></h1>
            <form action="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"EventoServlet?action=update\" : \"EventoServlet?action=new\"}"/>" method="POST" enctype="multipart/form-data">
                <c:if test="${(!(empty param.form) || param.form == \"alterar\")}" >
                    <div class="form-row">
                        <input type="hidden" name="id" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarevento.id : \"\"}"/>">
                    </div>
                </c:if>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="nome">Nome:</label>
                        <input class="form-control" type="text" name="nome" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarevento.nome : \"\"}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-9">
                        <label for="sobrenome">Data Inicio:</label>
                        <input class="form-control datetimepicker" type="text" name="dataInicio" value="<c:out value="${dataInicio}"/>"/><br/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <label for="email">Data Fim:</label>
                        <input class="form-control datetimepicker" type="text" name="dataFim" value="<c:out value="${dataFim}"/>"/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="endereco">Endereço:</label>
                        <input class="form-control" type="text" name="endereco" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarevento.endereco : \"\"}"/>"/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="desc">Descrição:</label>
                        <textarea name="desc"><c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarevento.descrição : \"\"}"/></textarea>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="img">Banner:</label>
                        <input class="form-control" type="file" name="img"/><br/>
                        <c:if test="${((!(empty param.form) || param.form == \"alterar\")) && (alterarevento.imagem != \"\" && alterarevento.imagem != null) }" >
                            <img src="${alterarevento.imagem}">
                        </c:if>
                    </div>
                </div>
                <input class="btn btn-outline-success" type="submit" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"Alterar\" : \"Salvar\"}"/>"/>
                <a class="btn btn-outline-danger" href="EventoServlet">Cancelar</a>
            </form>
        </div>
    </body>
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/jquery.datetimepicker.full.js" type="text/javascript"></script>

    <script>
        /*jslint browser:true*/
        /*global jQuery, document*/
            
        jQuery(document).ready(function () {
            jQuery.datetimepicker.setLocale('pt');
            jQuery('.datetimepicker').datetimepicker({
                format:'d/m/Y H:m'
            });
        });
    </script>
</html>
