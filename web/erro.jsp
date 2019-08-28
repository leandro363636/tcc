<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Erro</title>
        <link rel="stylesheet" type="text/css" href="css/erro.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.js"></script>
    </head>
    <body>
        <%@ page isErrorPage="true" %>
        
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item"><a class="nav-link" href="EventoServlet">Página inicial</a></li>
                </ul>
            </div>
            <div class="navbar-collapse collapse w-100 order-2 dual-collapse2">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a class="nav-link" href="LoginServlet?action=logout">Sair</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <div class="alert alert-danger" role="alert">
                    <p>${exception.message}<br/>
                       ${pageContext.out.flush()}<br/>
                       ${exception.printStackTrace(pageContext.response.writer)}</p>
                </div>
            </div>
        </div>
        <!--<footer>
            <p class="small font-weight-light">Em caso de problemas contactar o administrador: <strong><c:out value="${configuracao.email}" /></strong></p> 
        </footer>-->
        </body>
</html>
