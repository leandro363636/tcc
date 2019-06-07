<%-- 
    Document   : deletarSuspenderComprador
    Created on : 11/05/2019, 23:54:18
    Author     : Tiago
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

        <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/compradorListar.css"/>
        <link rel="stylesheet" type="text/css" href="css/datepicker/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="css/datepicker/datepicker.min.css"/>

        <script type="text/javascript" src="js/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.mask.js"></script>
        <script type="text/javascript" src="js/compradorListar.js"></script>

        <title>Lista de Compradores | fastTicket</title>
    </head>
    <body>

        <div id="listacompradores">

            <!-- NAVBAR TOPO -->
            <div class="row1">
                <div class="container-fluid">
                    <div class="row align-items-center">
                        <div class="col-12">
                            <nav class="navbar navbar-expand-sm navbar-light bg-light">
                                <a href="EventoServlet?action=list" class="navbar-brand">fastTicket</a>
                                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTopo" aria-controls="navbarSupportedContent" aria-expanded="false">
                                    <span class="navbar-toggler-icon"></span>
                                </button>
                                <div class="collapse navbar-collapse" id="navbarTopo">
                                    <ul class="navbar-nav mr-auto">
                                        <c:if test="${sessionScope.usuario.tipo.equals(\"a\") || sessionScope.usuario.tipo.equals(\"o\")}">
                                            <li class="nav-item">
                                                <a class="nav-link right" href="EventoServlet?action=formNew">Criar Evento</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${sessionScope.usuario.tipo.equals(\"a\") || sessionScope.usuario.tipo.equals(\"o\")}">
                                            <li class="nav-item">
                                                <a class="nav-link" href="EventoServlet?action=listSelf">Meus Eventos</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${sessionScope.usuario.tipo.equals(\"a\")}">
                                            <li class="nav-item">
                                                <a class="nav-link" href="DeletarSuspenderCompradorServlet?action=list">Compradores</a>
                                            </li>
                                        </c:if>   
                                        <c:if test="${sessionScope.usuario.tipo.equals(\"a\")}">
                                            <li class="nav-item">
                                                <a class="nav-link" href="DeletarSuspenderOrganizadorServlet?action=list">Organizadores</a>
                                            </li>
                                        </c:if>   
                                                            <c:if test="${sessionScope.usuario.tipo.equals(\"a\")}">
                                            <li class="nav-item">
                                                <a class="nav-link" href="DeletarSuspenderEventoServlet?action=list">Eventos</a>
                                            </li>
                                        </c:if> 
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Meus Ingressos</a>
                                        </li>
                                    </ul>
                                    <span class="nav-item dropdown">
                                        <span class="nav-link dropdown-toggle" href="#" id="navbarDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" v-pre>
                                            Bem vindo, <c:out value="${nome}"/> <span class="caret"></span>
                                        </span>
                                        <div class="dropdown-menu dropdown-menu-top" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="UsuarioServlet?action=formUpdate&id=<c:out value="${usuario.id}"/>">Minha Conta</a>
                                            <a class="dropdown-item" href="#">Meus Ingressos</a>
                                            <a class="dropdown-item" href="index.jsp">Login</a>
                                            <a class="dropdown-item" href="CadastrarServlet?action=formNew">Criar Conta</a>
                                            <a class="dropdown-divider"></a>
                                            <a class="dropdown-item" href="LoginServlet?action=logout">Logout</a>
                                            <form id="form-logout" action="" method="" style="display: none;"></form>
                                        </div>
                                    </span>
                                </div>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
  <c:choose>
            <c:when test="${compradores.size() gt 0}">
            <div class="row2">
                <div class="container">
                    <div class="row align-items-center my-5">
                        <div class="col-12 col-md-5">
                            <p class="center">COMPRADOR</p>
                        </div>
                        <div class="col-12 col-md-2">
                            <p class="center">STATUS</p>
                        </div>
                        <div class="col-12 col-md-5">
                            <p class="center">AÇÃO</p>
                        </div>
                    </div>
                    <c:forEach items="${compradores}" var="comprador">
                        <div class="row align-items-center">
                            <div class="col-12 col-md-5">
                                <p class="center"><c:out value="${comprador.nome}"/></p>
                            </div>

                            <c:if test="${comprador.ativo}">
                                <div class="col-12 col-md-2">
                                    <p class="center">ATIVO</p>
                                </div>
                                <div class="col-12 col-md-5">
                                    <p class="center">
                                        <a href="DeletarSuspenderCompradorServlet?action=desactive&id=<c:out value="${comprador.idComprador}"/>"><i class="fas fa-toggle-on" id="toggle1"></i></a>
                                        <a href="DeletarSuspenderCompradorServlet?action=delete&id=<c:out value="${comprador.idComprador}"/>"><i class="fas fa-trash-alt"></i></a>
                                        <a href="#"><i class="fas fa-info-circle"></i></a>
                                    </p>

                                </div>

                            </c:if>
                            <c:if test="${!comprador.ativo}">
                                <div class="col-12 col-md-2">
                                    <p class="center">INATIVO</p>
                                </div>
                                <div class="col-12 col-md-5">
                                    <p class="center">
                                        <a href="DeletarSuspenderCompradorServlet?action=active&id=<c:out value="${comprador.idComprador}"/>"><i class="fas fa-toggle-off" id="toggle1"></i></a>
                                        <a href="DeletarSuspenderCompradorServlet?action=delete&id=<c:out value="${comprador.idComprador}"/>"><i class="fas fa-trash-alt"></i></a>
                                        <a href="#"><i class="fas fa-info-circle"></i></a>
                                    </p>
                                </div>

                            </c:if> 

                        </div>

                        <hr>  
                    </c:forEach>

                </div>
            </div>

        </div>
        <!-- PAGINAÇÃO -->
        <div class="row4">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-12">
                        <nav aria-label="Navegação pelos eventos">
                            <ul class="pagination">
                                <li class="page-item <c:out value="${(pagina == 1) ? \"disabled\" : \"\"}"/>">
                                    <a class="page-link" href="DeletarSuspenderCompradorServlet?action=list&pagina=<c:out value="${pagina - 1}"/>" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <c:forEach begin="1" end="${numeroPaginas}" var="i">
                                    <c:choose>
                                        <c:when test="${pagina eq i}">
                                            <li class="page-item active"><a class="page-link" href="#"><c:out value="${i}"/></a></li>
                                            </c:when>
                                            <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="DeletarSuspenderCompradorServlet?action=list&pagina=<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                <li class="page-item <c:out value="${(pagina == numeroPaginas) ? \"disabled\" : \"\"}"/>">
                                    <a class="page-link" href="DeletarSuspenderCompradorServlet?action=list&pagina=<c:out value="${pagina + 1}"/>" aria-label="Previous">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
            </c:when>
            <c:otherwise>
  
                <!-- NÃO ENCONTRADO -->
                <div class="row3">
                    <div class="col-md-6 offset-md-3">
                        <div class="card">
                            <div class="card-body">
                                Nenhum evento encontrado.
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>


  </div>




        <!-- SCRIPTS -->
        <script type="text/javascript" src="js/popper/popper.min.js"></script>
        <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
        <!-- <script type="text/javascript" src="js/slick/slick.min.js"></script> -->
    </body>
</html>