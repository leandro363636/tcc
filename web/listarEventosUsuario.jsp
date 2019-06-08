<%-- 
    Document   : eventosListar
    Created on : 07/04/2018, 15:55:25
    Author     : tiago
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ufpr.tads.tcc.beans.Evento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/eventosListar.css"/>
	<link rel="stylesheet" type="text/css" href="js/slick/slick.css"/>
	<link rel="stylesheet" type="text/css" href="js/slick/slick-theme.css"/>
        <link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.min.css">

	<title>Pesquisar Evento | fastTicket</title>
    </head>
    <body>
        <%@ page errorPage="erro.jsp" %>

        <div id="pesquisaevento">
            
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

		<!-- TOPO -->
 
                <!-- PESQUISA -->

                <hr class="pb-3">
                
                
        <c:choose>
            <c:when test="${eventos.size() > 0}">
                <!-- LISTA DE EVENTOS -->
                <div class="row3">
                        <div class="container">
                                <div class="row align-items-center">
                                        <div class="col-12">
                                                <h2 class="center mb-4"><c:out value="${total}"/> eventos encontrados</h2>
                                        </div>
                                        <c:forEach items="${eventos}" var="evento">
                                            <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${evento.dataInicio}" var="dataInicio" />
                                            <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${evento.dataFim}" var="dataFim" />
                                            <div class="col-12 col-md-4 mb-4">
                                                    <a href="EventoServlet?action=show&id=<c:out value="${evento.id}"/>">
                                                            <span class="nome-evento offset-1"><c:out value="${evento.nome}"/></span>
                                                            <img src="<c:out value="${(!(empty evento.imagem) && evento.imagem != \"\" && evento.imagem != null) ? evento.imagem : \"img/130816-FM-Festa-Paradiso-089-840x560.jpg\"}"/>" class="eventos-lista" alt="">
                                                    </a>
                                                    <div class="row align-items-center">
                                                            <div class="offset-1 col-5">
                                                                    <i class="fas fa-calendar-day"></i> <span class="data-evento"><c:out value="${dataInicio}"/></span>
                                                            </div>
                                                            <div class="col-5">
                                                                    <i class="fas fa-calendar-day"></i> <span class="horario-evento"><c:out value="${dataFim}"/></span>
                                                            </div>
                                                    </div>
                                                    <div class="row align-items-center">
                                                            <div class="offset-1 col-11">
                                                                    <i class="fas fa-map-marker-alt"></i> <span class="local-evento"><c:out value="${evento.endereco.rua}"/>, <c:out value="${evento.endereco.numero}"/> - <c:out value="${evento.endereco.cep}"/></span>
                                                            </div>
                                                    </div>
                                            </div>
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
                                                                <a class="page-link" href="EventosListarUsuarioServlet?action=list&pagina=<c:out value="${pagina - 1}"/>" aria-label="Previous">
                                                                                <span aria-hidden="true">&laquo;</span>
                                                                        </a>
                                                                </li>
                                                                <c:forEach begin="1" end="${numeroPaginas}" var="i">
                                                                    <c:choose>
                                                                        <c:when test="${pagina eq i}">
                                                                            <li class="page-item active"><a class="page-link" href="#"><c:out value="${i}"/></a></li>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                        <li class="page-item"><a class="page-link" href="EventosListarUsuarioServlet?action=list&pagina=<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
                                                                <li class="page-item <c:out value="${(pagina == numeroPaginas) ? \"disabled\" : \"\"}"/>">
                                                                        <a class="page-link" href="EventosListarUsuarioServlet?action=list&pagina=<c:out value="${pagina + 1}"/>" aria-label="Previous">
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
        <script type="text/javascript" src="js/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="js/popper/popper.min.js"></script>
        <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/slick/slick.min.js"></script>
        <script type="text/javascript" src="js/eventosListar.js"></script>
        <script type="text/javascript" src="js/eventos.js"></script>
        <script src="js/jquery.datetimepicker.full.js" type="text/javascript"></script>

    <script>
        /*jslint browser:true*/
        /*global jQuery, document*/
            
        $(document).ready(function () {
            $.datetimepicker.setLocale('pt');
            $('.datetimepicker').datetimepicker({
                format:'d/m/Y H:m'
            });
        });
    </script>
        
    </body>
</html>
