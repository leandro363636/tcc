<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.js"></script>
        <meta charset="UTF-8">
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="795100341957-80vb246senf2so6sk02b9oabcfc3bdps.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>

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
             } ;
             
             
             
        </script>
        <style type="text/css">
            .user {
                padding: 50px 20px;
                background: #FFFFFF;
                border-radius: 2px;
                box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
                width: 330px;
                display: block;
                margin: 0 auto;
            }

            #user-photo {
                width: 100px;
                height: 100px;
                border-radius: 50%;
                display: block;
                margin: 0 auto 10px auto;
            }



            #user-name {
                color: #0066AA;
                font-weight: bold;
            }

            #user-email {
                display: block;
                margin: 0 auto;
                text-align: center;
                color: #0066AA;
            }

            .g-signin2,
            .g-signin2 .abcRioButton {
                display: block;
                margin: 20px auto 0 auto;
                text-align: center;
                font-weight: bold;
            }

        </style>
        <%@ page errorPage="erro.jsp" %>
        <div class="container">
            <div class="principal">

                <c:if test="${!(empty param.msg)}" >
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

                <h1>Login</h1>
                <form action="LoginServlet?action=login" method="POST">
                    <div class="form-group row">
                        <div class="col-sm-5"></div>
                        <input class="form-control col-sm-2 login" id="email" type="email" name="email" value="" placeholder="E-mail" onfocus="this.placeholder = ''"
                               onblur="this.placeholder = 'Login'" maxlength="50"><br/>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-5"></div>
                        <input class="form-control col-sm-2 senha" id="senha" type="password" name="senha" value="" placeholder="Senha" onfocus="this.placeholder = ''"
                               onblur="this.placeholder = 'Senha'" maxlength="50"/>
                    </div>
                    <a href="RecuperarSenhaServlet?action=formNew">Esqueceu sua senha?</a><br/>
                    <button type="submit" class="btn btn-outline-secondary">Login</button>

                </form>
                <div class="form-group row facebook " id="facebook">
                </div>
                <fb:login-button class="fb-login-button" data-width="10px" data-size="medium" data-button-type="login_with" scope="public_profile,email, "  data-auto-logout-link="false" data-use-continue-as="true" onlogin="checkLoginState();"></fb:login-button>
                <div class="g-signin2" data-width="180px" data-height="30px" data-longtitle="true" data-onsuccess="onSignIn"> </div>
                <form action="LoginServlet?action=social" method="POST">
                    <input style="visibility: hidden;" id="nome_social"  name="nome" />
                    <input style="visibility: hidden;" id="sobrenome_social"  name="sobrenome" />
                    <input style="visibility: hidden;" id="id_social"  name="id"  />
                    <input style="visibility: hidden;" id="email_social"  name="email"  />
                    <button type="submit" style="visibility: hidden;" id="loginSocial" name="loginSocial" >fce</button>
                </form>

      
<div >Não possui uma conta ?</div> <a class="btn btn-outline-success" href="CadastrarServlet?action=formNew">cadatrar-se</a>


        </div>

        <!--<footer>
            <p class="small font-weight-light">Em caso de problemas contactar o administrador: <strong><c:out value="${configuracao.email}" /></strong></p> 
        </footer>-->

    </body>
</html>