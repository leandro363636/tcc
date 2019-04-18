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
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Eventos Listar</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/clienteListar.css">
        <link rel="stylesheet" type="text/css" href="css/ionicons.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.js"></script>
        <script src="js/clientesListar.js"></script>-->
        <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/eventosListar.css"/>
	<link rel="stylesheet" type="text/css" href="js/slick/slick.css"/>
	<link rel="stylesheet" type="text/css" href="js/slick/slick-theme.css"/>

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
								<input type="text" id="cidadeEvento" class="form-control" placeholder="Pesquisar por cidade" name="cidadeEvento">
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
        </div>
                    
                    <!--<c:if test="${!(empty param.msg)}" >
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
                    
        <a class="btn btn-outline-success" href="EventoServlet?action=formNew">Novo</a>
        <table class="table table-striped"><tr><th>Nome</th><th>Descrição</th><th>Visualizar</th><th>Alterar</th><th>Remover</th></tr>

        <c:forEach items="${eventos}" var="evento">
            <tr>
                <td><c:out value="${evento.nome}"/></td>
                <td><c:out value="${evento.descrição}"/></td>
                <td><a href="EventoServlet?action=show&id=<c:out value="${evento.id}"/>"><i class="ion-person"></i></a></td>
                <td><a href="EventoServlet?action=formUpdate&id=<c:out value="${evento.id}"/>"><i class="ion-edit"></i></a></td>
                <td><a href="EventoServlet?action=remove&id=<c:out value="${evento.id}"/>"><i class="ion-trash-a"></i></a></td>
            </tr>
        </c:forEach>

        </table>-->
        
        <!-- SCRIPTS -->
        <script type="text/javascript" src="js/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="js/popper/popper.min.js"></script>
        <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/slick/slick.min.js"></script>
        <script type="text/javascript" src="js/eventosListar.js"></script>
        <script type="text/javascript" src="js/jquery/jquery.datetimepicker.full.min.js"></script>
    </body>
</html>
