<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="google-signin-scope" content="profile email">
  <meta name="google-signin-client_id" content="795100341957-80vb246senf2so6sk02b9oabcfc3bdps.apps.googleusercontent.com">
  <script src="https://apis.google.com/js/platform.js" async defer></script>

  <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">
  <!-- <link rel="stylesheet" type="text/css" href="css/login.css"/> -->
  <link rel="stylesheet" type="text/css" href="css/datepicker/jquery-ui.min.css">
  <link rel="stylesheet" type="text/css" href="css/datepicker/datepicker.min.css"/>

  <script type="text/javascript" src="js/jquery/jquery.min.js"></script>
  <script type="text/javascript" src="js/jquery/jquery.mask.js"></script>
  <script type="text/javascript" src="js/login.js"></script>


  <script>
    window.onload = function () {
      signOut();
    };
  </script>

</head>
<body>
  <div id="fb-root"></div>
  <script async defer crossorigin="anonymous" src="https://connect.facebook.net/pt_BR/sdk.js#xfbml=1&version=v3.2&appId=306218993286443&autoLogAppEvents=1"></script>
  <script>
   function statusChangeCallback(response) {

     if (response.status === 'connected') {

       FB.api("/me?fields=id,name,email,permissions,first_name,last_name", function (response) {

         document.getElementById('nome_social').value = response.first_name;
         document.getElementById('sobrenome_social').value = response.last_name;
         document.getElementById('email_social').value = response.email;
         document.getElementById('loginSocial').click();
       });
     } else {

     }
   }

   window.fbAsyncInit = function () {
     FB.init({
       appId: '{306218993286443}',
       cookie: true,
       xfbml: true,
       version: '{3.2}'
     });

     FB.AppEvents.logPageView();

   };

   (function (d, s, id) {
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {
       return;
     }
     js = d.createElement(s);
     js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));


   function checkLoginState() {
     FB.getLoginStatus(function (response) {

     }, {scope: 'email,user_likes'});


     FB.getLoginStatus(function (response) {
       statusChangeCallback(response);
     });


   }

   function logout() {
     FB.logout(
       );

   }
   ;

   function onSignIn(response) {
    signOut();
    if (response.getAuthResponse().id_token !== null)
      // Conseguindo as informações do seu usuário:
    var perfil = response.getBasicProfile();
      // Conseguindo o Nome do Usuário
      var nome = perfil.getGivenName();
      var sobrenome =  perfil.getFamilyName();
      // Conseguindo o E-mail do Usuário
      var email = perfil.getEmail();

      document.getElementById('nome_social').value = nome;
      document.getElementById('sobrenome_social').value = sobrenome;
      document.getElementById('email_social').value = email;
      document.getElementById('loginSocial').click();
      // Recebendo o TOKEN que você usará nas demais requisições à API:
    }

    function signOut() {
      var auth2 = gapi.auth2.getAuthInstance();
      auth2.signOut();
    };

  </script>

  <%@ page errorPage="erro.jsp" %>
  <div class="container mt-5">
   <!--  <div class="row justify-content-center">
      <c:if test="${!(empty param.msg)}" >
        <c:set var="mensagem" value="${param.msg}" />
      </c:if>
      <c:if test="${!(empty requestScope.msg)}">
        <c:set var="mensagem" value="${requestScope.msg}" />
      </c:if>
      <c:if test="${!(empty mensagem)}" >
        <div class="row">
          <div class="col-sm-4 alert alert-danger" role="alert">
            <p><c:out value="${mensagem}" /></p>
          </div>
        </div>
      </c:if>
    </div> -->

    <div class="row justify-content-center">
      <div class="col-md-4">
        <div class="card">
          <header class="card-header">
            <a href="" class="float-right btn btn-outline-primary mt-1">Cadastro</a>
            <h4 class="card-title mt-2">Login</h4>
          </header>

          <article class="card-body">

            <form method="" action="">

              <div class="form-group">
                <label>Email </label>
                <input type="email" class="form-control" id="email" placeholder="">
              </div> <!-- form-group end.// -->

              <div class="form-group">
                <label>Senha </label>
                <input class="form-control" name="senha" id="senha" type="password">
              </div> <!-- form-group end.// -->

              <div class="form-group">
                <a href="#" class="center">Esqueci a senha</a>
              </div>

              <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block"> Login </button>
              </div> <!-- form-group// -->   

            </form>

            <hr>

            <div class="form-group facebook" id="facebook"></div>
            <fb:login-button class="fb-login-button" data-width="10px" data-size="medium" data-button-type="login_with" scope="public_profile,email, "  data-auto-logout-link="false" data-use-continue-as="true" onlogin="checkLoginState();"></fb:login-button>
            <div class="g-signin2" data-width="180px" data-height="30px" data-longtitle="true" data-onsuccess="onSignIn"> </div>
            <form action="LoginServlet?action=social" method="POST" class="form2">
              <input style="visibility: hidden;" id="nome_social"  name="nome" />
              <input style="visibility: hidden;" id="sobrenome_social"  name="sobrenome" />
              <input style="visibility: hidden;" id="id_social"  name="id"  />
              <input style="visibility: hidden;" id="email_social"  name="email"  />
              <button type="submit" style="visibility: hidden;" id="loginSocial" name="loginSocial" >fce</button>
            </form>

          </article>
        </div>
      </div>
    </div>
  </div>


  <script type="text/javascript" src="js/jquery/jquery.min.js"></script>
  <script type="text/javascript" src="js/popper/popper.min.js"></script>
  <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
</body>
</html>