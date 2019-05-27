<%-- 
    Document   : carrinho
    Created on : 27/05/2019, 16:02:34
    Author     : leand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/carrinho.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <!-- SCRIPTS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/jquery.mask.js" type="text/javascript"></script>
        <script src="js/clientesForm.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    </head>

    <body>
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
          <div class="container">
              <div class="row">
               <div class="col-md-12">
              <table>
               <thead>
                   <tr>
                   <th>Produto</th>
                   <th>Valor Unitario</th>
                   <th>Quantidade</th>
                   <th>Subtotal</th>
                   <th>Excluir</th>
                   </tr>
               </thead>
               <tbody>

                   <tr>
                   <td>teste</td>
                   <td>teste</td>
                   <td><input value="1" type="number" size="2" title="Qtd" class="input-text qty" data-validate="{required:true,'validate-greater-than-zero':true}" data-role="cart-item-qty"></td>
                   <td>teste</td>
                   <td><a href="#" title="Excluir" class="action action-delete">
                       <i class="fa fa-times" aria-hidden="true"></i>
                   </a></td>
                   </tr>
                   </tbody>
               </table>
           </div>
       </div>

            <div class = "col-md-5">
            <table>
               <thead>
                   <tr>
                  <th scope="row">Subtotal</th>
                  <td class="amount">
                   <span>R$533,98</span>
                   </td>
                   </tr>
                   </thead>
               </table>
            </div>

               <div class="row">
               <div class="col-md-2">
               <button class="btn btn-success" type="button" title="Finalizar Pedido" >
               <strong>Finalizar</strong>

               </button>
               </div>
              <div class="col-md-2" >
              <button style="left:50%;" type="button" class="btn btn-danger">Cancela</button>
              </div>
               </div>
           </div>


   </body>
</html>



