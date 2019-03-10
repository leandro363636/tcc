<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.js"></script>
    </head>
    <body>
        <%@ page errorPage="erro.jsp" %>
        <div class="container">
            <div class="principal">
                
            <c:if test="${!(empty param.msg)}" >
                <c:set var="mensagem" value="${param.msg}" />
            </c:if>
            <c:if test="${!(empty requestScope.msg)}">
                <c:set var="mensagem" value="${requestScope.msg}" />
            </c:if>
            <c:if test="${!(empty mensagem)}" >
                <div class="row">
                    <div class="col-sm-4"></div>
                        <div class="col-sm-4 alert alert-danger" role="alert">
                            <p><c:out value="${mensagem}" /></p>
                        </div>
                </div>
            </c:if>

                <h1>Login</h1>
                <form action="LoginServlet?action=login" method="POST">
                    <div class="form-group row">
                        <div class="col-sm-5"></div>
                            <input class="form-control col-sm-2 login" id="email" type="mail" name="email" value="" placeholder="E-mail" onfocus="this.placeholder = ''"
onblur="this.placeholder = 'Login'" maxlength="50"><br/>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-5"></div>
                            <input class="form-control col-sm-2 senha" id="senha" type="password" name="senha" value="" placeholder="Senha" onfocus="this.placeholder = ''"
onblur="this.placeholder = 'Senha'" maxlength="50"/><br><br>
                    </div>
                    <button type="submit" class="btn btn-outline-secondary">Login</button>
                </form>
            </div>
        </div>
        
        <!--<footer>
            <p class="small font-weight-light">Em caso de problemas contactar o administrador: <strong><c:out value="${configuracao.email}" /></strong></p> 
        </footer>-->
        
    </body>
</html>