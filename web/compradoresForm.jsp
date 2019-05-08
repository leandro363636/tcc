<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ufpr.tads.tcc.beans.Usuario"%>
<%@page import="com.ufpr.tads.tcc.beans.Comprador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title><c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"Alterar Usuário\" : \"Novo Usuário\"}"/></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/clientesAlterar.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/jquery.mask.js" type="text/javascript"></script>
        <script src="js/eventos.js" type="text/javascript"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </head>
    <body>
        <%@ page errorPage="erro.jsp" %>
        <fmt:formatDate pattern="dd/MM/yyyy" value="${alterarcomprador.dataNascimento}" var="dataNascimento" />
        <c:if test="${(!(empty param.form) || param.form == \"alterar\") && empty sessionScope.usuario}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>
        <div class="container">
            <h1><c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"Alterar Comprador\" : \"Novo Comprador\"}"/></h1>
            <form action="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"CompradorServlet?action=update\" : \"CompradorServlet?action=new\"}"/>" method="POST">
                <c:if test="${(!(empty param.form) || param.form == \"alterar\")}" >
                    <input type="hidden" name="id" value="<c:out value="${alterarcomprador.idComprador}"/>">
                </c:if>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="nome">Nome:</label>
                        <input class="form-control" type="text" name="nome" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcomprador.nome : \"\"}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-9">
                        <label for="sobrenome">Sobrenome:</label>
                        <input class="form-control" type="text" name="sobrenome" maxlength="150" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcomprador.sobrenome : \"\"}"/>" required/><br/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <label for="email">E-mail:</label>
                        <input class="form-control" type="email" name="email" maxlength="100" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcomprador.email : \"\"}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="senha">Senha:</label>
                        <input class="form-control" type="text" name="senha" <c:out value="${((empty param.form) || param.form != \"alterar\") ? \"required\" : \"\"}"/>/><br/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <label for="rg">RG:</label>
                        <input  class="form-control" type="text" name="rg" maxlength="9" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcomprador.rg : \"\"}"/>" id="rg" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="cpf">CPF:</label>
                        <input class="form-control" type="text" name="cpf"  maxlength="11" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcomprador.cpf : \"\"}"/>" id="cpf" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="cpf">Data de nascimento:</label>
                        <input class="form-control" type="text" name="dataNascimento" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? dataNascimento : \"\"}"/>" id="dataNascimento" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="rua">Rua:</label>
                        <input class="form-control" type="text" name="rua" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcomprador.endereco.rua : \"\"}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="numero">Número:</label>
                        <input class="form-control" type="number" name="numero" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcomprador.endereco.numero : \"\"}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="cep">CEP:</label>
                        <input class="form-control" type="text" name="cep" maxlength="8" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? alterarcomprador.endereco.cep : \"\"}"/>" required/><br/>
                    </div>
                    <div class="form-group col-md-1">
                        <label for="uf">UF:</label>
                        <select id="estado" name="uf" class="custom-select" required>
                        <c:forEach items="${estados}" var="estado">
                            <option <c:out value="${(alterarcomprador.endereco.cidade.estado.id == estado.id) ? \"selected\" : \"\" }"/> value="<c:out value="${estado.id}"/>"><c:out value="${estado.sigla}"/></option>
                        </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-9">  
                        <label for="cidade">Cidade:</label>
                        <select id="cidade" name="cidade" class="custom-select" required>
                            <c:if test="${(!(empty param.form) || param.form == \"alterar\")}" >
                            <option selected value="<c:out value="${alterarcomprador.endereco.cidade.id}"/>"><c:out value="${alterarcomprador.endereco.cidade.nome}"/></option>
                            </c:if>
                        </select>
                    </div>
                </div>
                <input class="btn btn-outline-success" type="submit" value="<c:out value="${(!(empty param.form) || param.form == \"alterar\") ? \"Alterar\" : \"Salvar\"}"/>"/>
                <a class="btn btn-outline-danger" href="CompradorServlet">Cancelar</a>
            </form>
        </div>
    </body>
</html>
