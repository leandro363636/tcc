<%-- 
    Document   : atendimento
    Created on : 05/05/2018, 17:22:42
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
        <title>Atendimento</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/clientesAlterar.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/jquery.mask.js" type="text/javascript"></script>
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/atendimento.js" type="text/javascript"></script>
        <script src="js/jquery.datetimepicker.full.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.min.css">
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
        <jsp:useBean id="now" class="java.util.Date" />
        <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${now}" var="data" />
            <h1>Atendimento</h1>
            <form action="AtendimentoServlet?action=new" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-2">
                        <label for="nome">Data Atendimento:</label>
                        <input class="form-control" id="datetimepicker"  type="text" name="data" value="<c:out value="${data}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="idproduto">Produto:</label>
                        <select name="idproduto" class="custom-select" required>
                            <c:forEach items="${produtos}" var="produto">
                                <option value="<c:out value="${produto.id}"/>"><c:out value="${produto.nome}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="idtipoatendimento">Tipo de Atendimento:</label>
                        <select name="idtipoatendimento" class="custom-select" required>
                            <c:forEach items="${tiposatendimento}" var="tipoatendimento">
                                <option value="<c:out value="${tipoatendimento.id}"/>"><c:out value="${tipoatendimento.nome}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="idcliente">Cliente:</label>
                        <select name="idcliente" class="custom-select" required>
                            <c:forEach items="${clientes}" var="cliente">
                                <option value="<c:out value="${cliente.id}"/>"><c:out value="${cliente.nome}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="descricao">Descrição:</label>
                        <textarea class="form-control" name="descricao" maxlength="255" row="3" required></textarea><br/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="resposta" value="S"/>
                        <label for="resposta" class="form-check-label">Atendimento Resolvido:</label><br/>
                    </div>
                </div>
                <input class="btn btn-outline-success" type="submit" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"Alterar\" : \"Salvar\"}"/>"/>
                <a class="btn btn-outline-danger" href="AtendimentoServlet">Cancelar</a>
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
            jQuery('#datetimepicker').datetimepicker({
                format:'d/m/Y H:m'
            });
        });
    </script>
</html>
