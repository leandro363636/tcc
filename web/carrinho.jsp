<%-- 
    Document   : carrinho
    Created on : 27/05/2019, 16:02:34
    Author     : leand
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
        <title>Carrinho</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/carrinho.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <!-- SCRIPTS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
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
        <div class="container">
            <div class="row">
               <div class="col-md-12">
                    <table>
                    <thead>
                        <tr>
                            <th>Produto</th>
                            <th>Valor Unitario</th>
                            <!--<th>Quantidade</th>
                            <th>Subtotal</th>
                            <th>Excluir</th>-->
                        </tr>
                    </thead>
                    <c:if test="${!(empty lotes)}" >    
                    <c:forEach items="${lotes}" var="lote">
                    <tbody>
                         <tr>
                             <td><c:out value="${lote.nome}"/></td>
                             <td><c:out value="${lote.preço}"/></td>
                             <!--<td><input id="qtde" min="0" max="${lote.quantidade}" value="1" type="number" size="2" title="Qtd" class="input-text qty" data-role="cart-item-qty"></td>-->
                             <!--<td><c:out value="${(lote.preço * Qtd)}"/></td>-->
<!--                             <td>
                                 <a href="#" title="Excluir" class="action action-delete">
                                     <i class="fa fa-times" aria-hidden="true"></i>
                                 </a>
                             </td>-->
                         </tr>
                    </tbody>
                    </c:forEach>
                    </c:if>
                    <c:if test="${(empty lotes)}" >    
                        <tbody>
                            <tr>
                                <td><c:out value="${sessionScope.idevento}"/> </td>
                                <td>x</td>
                                <td>x</td>
                                <td>x</td>
                                <td>
                                    <a href="#" title="Excluir" class="action action-delete">
                                        <i class="fa fa-times" aria-hidden="true"></i>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td>x</td>
                                <td>x</td>
                                <td>x</td>
                                <td>x</td>
                                <td>
                                    <a href="#" title="Excluir" class="action action-delete">
                                        <i class="fa fa-times" aria-hidden="true"></i>
                                    </a>
                                </td>
                            </tr>
                        </tbody>
                    </c:if>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class = "col-md-5">
                    <table>
                        <c:set var="price" value="0" scope="request"/>
                        <c:forEach items="${lotes}" var="lote" >
                            <c:set var="price" value="${lote.preço + price}" scope="request"/>
                        </c:forEach>
                        <thead>
                            <tr>
                                <th scope="row">Total</th>
                                <td class="amount">
                                    <span><c:out value="${price}" /> </span>
                                </td>
                            </tr>
                        </thead>
                    </table>
                </div>    
            </div>
            

            <div class="row">
                <div class="col-md-10" ></div>
                <div class="col-md-1" >
                    <button href="EventoServlet?action=list" style="left:50%;" type="button" class="btn btn-danger">Cancela</button>
                </div>
                <div class="col-md-1">
                    <button href="EventoServlet?action=list" class="btn btn-success" type="button" title="Finalizar Pedido" >
                        <strong>Finalizar</strong>
                    </button>
                </div>
            </div>
        </div>
    </body>
</html>



