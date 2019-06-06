<%-- 
    Document   : clientesVisualizar
    Created on : 07/04/2018, 21:45:00
    Author     : mateus
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ufpr.tads.tcc.beans.Usuario"%>
<%@page import="com.ufpr.tads.tcc.beans.Evento"%>
<%@page import="com.ufpr.tads.tcc.beans.Lote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Evento Visualizar</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/clientesVisualizar.css">
        <!-- SCRIPTS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="js/popper/popper.min.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/eventosVisualizar.js"></script>
    </head>
    <body>
        <%@ page errorPage="erro.jsp" %>
        <c:if test="${empty sessionScope.usuario}">
            <jsp:forward page="index.jsp">
                <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
            </jsp:forward>
        </c:if>
        
        <div id="visualizaevento">

            <!-- NAVBAR TOPO -->
            <div class="row1">
                    <div class="container-fluid">
                            <div class="row align-items-center">
                                    <div class="col-12">
                                            <nav class="navbar navbar-expand-sm navbar-light bg-light">
                                                    <a href="EventoServlet" class="navbar-brand">fastTicket</a>
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
                                                            <div>
                                                                <ul class="navbar-nav ml-auto">
                                                                    <li class="nav-item"><a class="nav-link" href="LoginServlet?action=logout">Sair</a></li>
                                                                </ul>
                                                            </div>
                                                    </div>
                                            </nav>
                                    </div>
                            </div>
                    </div>
            </div>

            <div class="row2 mb-2">
                <div class="container">
                    <div class="row align-items-center">
                            <div class="col-12" id="divNome">
                                    <h1 class="center" id="nomeEvento"><c:out value="${visualizarevento.nome}"/></h1>
                            </div>

                            <div class="image">
                                <img src="<c:out value="${(!(empty visualizarevento.imagem) && visualizarevento.imagem != \"\" && visualizarevento.imagem != null) ? visualizarevento.imagem : \"img/130816-FM-Festa-Paradiso-089-840x560.jpg\"}"/>" alt="" width="100%">
                            </div>﻿

                    </div>
                </div>
            </div>


        <div class="row3">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-12 col-md-7 order-2 order-md-1">
                                <h2 class="center">Sobre o Evento</h2>
                                <div class="row align-items-center">
                                        <div class="col-12" id="divForm">
                                                <form id="formNome" class="formNome center" action="#">
                                                    <input class="inputNome center" value="<c:out value="${visualizarevento.descrição}"/>" disabled="true" outline="0">
                                                </form>
                                        </div>
                                </div>
                        </div>
                        <div class="col-12 col-md-4 offset-md-1 order-1 order-md-2">
                            <!--<div class="row align-items-center">
                                    <div class="col-12">
                                        <h2>Lotes</h2>
                                            <input type="radio" id="lote1" name="lote" value="lote1"
                                            checked>
                                            <label for="lote1"><span>Lote 1 </span><span>R$ 100,00</span></label>
                                    </div>
                                    <div class="col-12">
                                            <input type="radio" id="lote2" name="lote" value="lote2">
                                            <label for="lote2"><span>Lote 2 </span><span>R$ 100,00</span></label>
                                    </div>
                                    <div class="col-12">
                                            <input type="radio" id="lote3" name="lote" value="lote3">
                                            <label for="lote3"><span>Lote 3 </span><span>R$ 100,00</span></label>
                                    </div>
                            </div>-->
                            <div class="row align-items-center">
                                <c:if test="${!(empty lotes)}" >
                                    <form class="list-group" id="lotesList">
                                        <h2 class="center">Lotes</h2>
                                        <c:forEach items="${lotes}" var="lote">
                                            <div class="col-12">
                                                <!-- adicionar verificação de disponibilidade do lote -->
                                                <!--<input checked="checked" type="radio" id="<c:out value="${lote.nome}"/>" name="lote" value="<c:out value="${lote.nome}"/>" <c:out value="(1==2) ? \"\" :disabled"/>-->
                                                <fmt:setLocale value = "en_US"/>
                                                <fmt:parseNumber var="preço" type="number" value="${lote.preço}"/>
                                                <label for="<c:out value="${lote.nome}"/>"><span><c:out value="${lote.nome}"/> - </span><span><c:out value="${preço}"/></span></label>
                                            </div>
                                        </c:forEach>
                                        <!-- como mandar o id do lote pegando o radio button marcado? --> 
                                        <a class="btn btn-success" href="EventoServlet?action=carrinho&id=<c:out value="${visualizarevento.id}"/>">PAGINA DE COMPRA</a>
                                    </form>

                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
	</div>
        
        <div class="container">
            <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${visualizarevento.dataInicio}" var="dataInicio" />
            <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${visualizarevento.dataFim}" var="dataFim" />
            <h1>Mais informações</h1>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="sobrenome">Data Inicio:</label>
                    <input class="form-control" type="text" name="dataInicio" value="<c:out value="${dataInicio}"/>" disabled/><br/>
                </div>
                <div class="form-group col-md-3">
                    <label for="email">Data Fim:</label>
                    <input class="form-control" type="text" name="dataFim" value="<c:out value="${dataFim}"/>" disabled/><br/>
                </div>
                <div class="form-group col-md-12">
                    <label for="endereco">Endereço:</label>
                    <input class="form-control" type="text" name="endereco" value="<c:out value="${visualizarevento.endereco}"/>" disabled/><br/>
                </div>
                <div class="form-group col-md-1">
                    <label for="aprovado">Validado:</label>
                    <input class="form-control" type="text" name="aprovado" value="<c:out value="${(visualizarevento.aprovado == true) ? \"Sim\" : \"Não\"}"/>" disabled/><br/>
                </div>
            </div>
            <a class="btn btn-primary" href="EventoServlet">VOLTAR</a>
        </div>
        <br/>
        <br/>
                
    </body>
</html>
