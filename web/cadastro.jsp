<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ufpr.tads.tcc.beans.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/cadastro.css"/>
    <link rel="stylesheet" type="text/css" href="css/datepicker/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="css/datepicker/datepicker.min.css"/>

    <script type="text/javascript" src="js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.mask.js"></script>
    <script type="text/javascript" src="js/cadastro.js"></script>
    <script type="text/javascript" src="js/datepicker/datepicker.min.js"></script>
  <!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
  <!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
  <!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
  <!-- <script src="maskedinput-1.3.js" type="text/javascript"></script> -->
</head>
<body>



  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <header class="card-header">
            <a href="" class="float-right btn btn-outline-primary mt-1">Login</a>
            <h4 class="card-title mt-2">Cadastre-se</h4>
          </header>
          <article class="card-body">

            <form method="" action="">

              <div class="form-row">
                <div class="col form-group">
                  <label>Nome </label>   
                  <input type="text" name="nome" id="nome" class="form-control" placeholder="">
                </div> <!-- form-group end.// -->
                <div class="col form-group">
                  <label>Sobrenome </label>
                  <input type="text" name="sobrenome" id="sobrenome" class="form-control" placeholder=" ">
                </div> <!-- form-group end.// -->
              </div> <!-- form-row end.// -->

              <div class="form-row">
                <div class="col form-group">
                  <label>Email</label>
                  <input type="email" class="form-control" id="email" placeholder="">
                </div> <!-- form-group end.// -->
                <div class="form-group">
                  <label>Senha</label>
                  <input class="form-control" name="senha" id="senha" type="password">
                </div> <!-- form-group end.// -->
              </div>

              <div class="form-row">
                <div class="col form-group">
                  <label>RG </label>   
                  <input type="text" name="rg" id="rg" class="form-control" placeholder="">
                </div> <!-- form-group end.// -->
                <div class="col form-group">
                  <label>CPF </label>
                  <input type="text" name="cpf" id="cpf" class="form-control" placeholder=" ">
                </div> <!-- form-group end.// -->
                <div class="col form-group">
                  <label>Data de Nascimento </label>
                  <input type="text" name="dataNascimento" id="dataNascimento" class="form-control" placeholder=" ">
                </div> <!-- form-group end.// -->
              </div> <!-- form-row end.// -->

              <div class="form-row">
                <div class="col form-group">
                  <label>Rua </label>   
                  <input type="text" name="rua" id="rua" class="form-control" placeholder="">
                </div> <!-- form-group end.// -->
                <div class="col form-group">
                  <label>Número </label>
                  <input type="number" name="numero" id="numero" class="form-control" placeholder=" ">
                </div> <!-- form-group end.// -->
              </div> <!-- form-row end.// -->

              <div class="form-row">
                <div class="col form-group">
                  <label>CEP </label>
                  <input type="text" name="cep" id="cep" class="form-control" placeholder=" ">
                </div> <!-- form-group end.// -->
                <div class="col form-group">
                  <label>UF </label>
                  <select id="estado" name="uf" id="estado" class="custom-select form-control" required>
                    <c:forEach items="${estados}" var="estado">
                    <option value="<c:out value="${estado.id}"/>"><c:out value="${estado.sigla}"/></option>
                  </c:forEach>
                </select>
              </div> <!-- form-group end.// -->
              <div class="col form-group">
                <label>Cidade </label>
                <select id="cidade" class="form-control"></select>
              </div> <!-- form-group end.// -->

            </div> <!-- form-row.// --> 
            <div class="form-group">
              <button type="submit" class="btn btn-primary btn-block"> Cadastrar </button>
            </div> <!-- form-group// -->      

          </form>

        </article> <!-- card-body end .// -->
      </div> <!-- card.// -->
    </div> <!-- col.//-->

  </div> <!-- row.//-->


</div> 
<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="js/popper/popper.min.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>

</body>
</html>
