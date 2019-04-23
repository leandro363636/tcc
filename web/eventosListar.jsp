<%-- 
    Document   : clientesListar
    Created on : 07/04/2018, 15:55:25
    Author     : mateus
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
        <!--<%@ page errorPage="erro.jsp" %>
        <c:if test="${empty sessionScope.usuario}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>-->
        
        <div id="pesquisaevento">
            
            <!-- NAVBAR TOPO -->
		<div class="row1">
			<div class="container-fluid">
				<div class="row align-items-center">
					<div class="col-12">
						<nav class="navbar navbar-expand-sm navbar-light bg-light">
							<a href="#" class="navbar-brand">fastTicket</a>
							<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTopo" aria-controls="navbarSupportedContent" aria-expanded="false">
								<span class="navbar-toggler-icon"></span>
							</button>
							<div class="collapse navbar-collapse" id="navbarTopo">
								<ul class="navbar-nav mr-auto">
									<li class="nav-item">
										<a class="nav-link right" href="EventoServlet?action=formNew">Criar Evento</a>
									</li>
									<li class="nav-item">
										<a class="nav-link" href="EventoServlet?action=listSelf">Meus Eventos</a>
									</li>
									<li class="nav-item">
										<a class="nav-link" href="#">Meus Ingressos</a>
									</li>
								</ul>
								<span class="nav-item dropdown">
									<span class="nav-link dropdown-toggle" href="#" id="navbarDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" v-pre>
										Bem vindo, <c:out value="${usuario.nome}"/> <span class="caret"></span>
									</span>
									<div class="dropdown-menu dropdown-menu-top" aria-labelledby="navbarDropdown">
										<button class="dropdown-item" href="UsuarioServlet?action=formUpdate&id=<c:out value="${usuario.id}"/>">Minha Conta</button>
										<button class="dropdown-item" href="#">Meus Ingressos</button>
										<button class="dropdown-item" href="index.jsp">Login</button>
										<button class="dropdown-item" href="UsuarioServlet?action=formNew">Criar Conta</button>
										<div class="dropdown-divider"></div>
										<button class="dropdown-item" href="LoginServlet?action=logout">Logout</button>
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
                <div class="row2 mb-2">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-12">
						<h1 class="center">Uma pausa para a diversão</h1>
					</div>
				</div>
			</div>
		</div>
                
                <!-- PESQUISA -->
		<div class="row3">
			<div class="container">
				<form action="EventoServlet?action=list" method="post">
					<div class="row align-items-center">
						<div class="col-12">
							<div class="input-group grupo-1 mb-5">
                                                                <input type="text" id="nomeEvento" class="form-control" placeholder="Pesquisar por evento" name="nomeEvento">
								<select id="estado" name="estado" class="custom-select">
                                                                <c:forEach items="${estados}" var="estado">
                                                                    <option value="<c:out value="${estado.id}"/>"><c:out value="${estado.sigla}"/></option>
                                                                </c:forEach>
                                                                </select>
                                                                <select id="cidade" name="cidade" class="custom-select">
                                                                    <option value="">Selecione um estado</option>
                                                                </select>
                                                                <!--<input type="text" id="estado" class="form-control" placeholder="Pesquisar por cidade" name="cidadeEvento">
								<input type="text" id="cidadeEvento" class="form-control" placeholder="Pesquisar por cidade" name="cidadeEvento">-->
							</div>
							<div class="input-group grupo-2 mb-5">
								<input type="text" id="dataEvento" class="form-control datetimepicker" name="dataEvento">
								<div class="input-group-append">
									<a href="#">
										<input type="submit" class="btn btn-danger bt-pesquisar" value="Pesquisar">
									</a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
                </div>
                
                <!-- SLIDER DE BANNERS DE EVENTOS -->
                <div class="rowslider pb-3">
                        <div class="container">
                                <div class="slider">
                                        <c:forEach items="${carrousel}" var="item">
                                            <div class="slide">
                                                    <div class="row">
                                                            <div class="col-12 col-md-8 offset-md-2 align-items-center">
                                                                    <a href="EventoServlet?action=show&id=<c:out value="${item.id}"/>">
                                                                            <img src="<c:out value="${(!(empty item.imagem) && item.imagem != \"\" && item.imagem != null) ? item.imagem : \"img/130816-FM-Festa-Paradiso-089-840x560.jpg\"}"/>" alt="">
                                                                    </a>
                                                            </div>
                                                    </div>
                                            </div>
                                        </c:forEach>
                                </div>
                        </div>
                </div>

                <hr class="pb-3">
                
                
        <c:choose>
            <c:when test="${eventos.size() gt 0}">
                <!-- LISTA DE EVENTOS -->
                <div class="row3">
                        <div class="container">
                                <div class="row align-items-center">
                                        <div class="col-12">
                                                <h2 class="center mb-4"><c:out value="${eventos.size()}"/> eventos encontrados</h2>
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
                                                                <a class="page-link" href="EventoServlet?action=list&pagina=<c:out value="${pagina - 1}"/>" aria-label="Previous">
                                                                                <span aria-hidden="true">&laquo;</span>
                                                                        </a>
                                                                </li>
                                                                <c:forEach begin="1" end="${numeroPaginas}" var="i">
                                                                    <c:choose>
                                                                        <c:when test="${pagina eq i}">
                                                                            <li class="page-item active"><a class="page-link" href="#"><c:out value="${i}"/></a></li>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                        <li class="page-item"><a class="page-link" href="EventoServlet?action=list&pagina=<c:out value="${i}"/>"><c:out value="${i}"/></a></li>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
                                                                <li class="page-item <c:out value="${(pagina == numeroPaginas) ? \"disabled\" : \"\"}"/>">
                                                                        <a class="page-link" href="EventoServlet?action=list&pagina=<c:out value="${pagina + 1}"/>" aria-label="Previous">
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
