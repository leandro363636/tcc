<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ufpr.tads.tcc.beans.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Criar conta</title>
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
        <script src="maskedinput-1.3.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <h1>Criar conta</h1>
            <form action="CadastrarServlet?action=new" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="nome">Nome:</label>
                        <input class="form-control" type="text" name="nome" maxlength="100" value="" required/><br/>
                    </div>
                    <div class="form-group col-md-9">
                        <label for="sobrenome">Sobrenome:</label>
                        <input class="form-control" type="text" name="sobrenome" maxlength="150" value="" required/><br/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <label for="email">E-mail:</label>
                        <input class="form-control" type="email" name="email" maxlength="100" value="" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="senha">Senha:</label>
                        <input class="form-control" type="password" name="senha" value=""/><br/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <label for="rg">RG:</label>
                        <input  class="form-control" type="text" name="rg" maxlength="9" value="" id="rg" required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="cpf">CPF:</label>
                        <input class="form-control" type="text" name="cpf"  maxlength="11" value="" id="cpf"required/><br/>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="cpf">Data de nascimento:</label>
                        <input class="form-control" type="text" name="dataNascimento" value="" id="dataNascimento"required/><br/>
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
                            <option value="<c:out value="${estado.id}"/>"><c:out value="${estado.sigla}"/></option>
                        </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-9">  
                        <label for="cidade">Cidade:</label>
                        <select id="cidade" name="cidade" class="custom-select" required>
                        </select>
                    </div>
                </div>
                <input class="btn btn-outline-success" type="submit" value="Cadastrar"/>
                <a class="btn btn-outline-danger" href="index.jsp">Cancelar</a>
            </form>
        </div>
        <script type="text/javascript">

    jQuery(document).ready(function($) {
            $('#rg').mask('99.999.999-9');    
            $('#cpf').mask('999.999.999-99');    
    }); 

</script>
    </body>
</html>
