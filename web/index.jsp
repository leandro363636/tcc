<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Fast Ticket - Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="img/fastticketdark.ico">
        <!--<link rel="icon" href="img/fastticket.ico">-->
        <!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>-->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" crossorigin="anonymous">-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <meta charset="UTF-8">
        <meta name="google-signin-client_id" content="795100341957-80vb246senf2so6sk02b9oabcfc3bdps.apps.googleusercontent.com">
        <meta name="google-signin-scope" content="profile email">
        <!--<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
        <!--<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        
        <script>
            window.onload = function () {
                signOut();
            };

            
            function remember() {
                if ($('#bookmark').hasClass('far fa-bookmark')) {
                    $('#bookmark').removeClass('far fa-bookmark');
                    $('#bookmark').addClass('fas fa-bookmark');
                    $('#bookmark').css('text-shadow','0 0 15px green');
                    $('#bookmark').css('color','#C6EFCE');
                    $('#lembrar').css('text-shadow','0 0 15px green');
                    $.notify("Hello World");
                    $('input[name=remeber]').attr('checked', true);
                } else {
                    $('#bookmark').removeClass('fas fa-bookmark');
                    $('#bookmark').addClass('far fa-bookmark');
                    $('#bookmark').css('text-shadow','none');
                    $('#bookmark').css('color','whitesmoke');
                    $('#lembrar').css('text-shadow','none');
                    $('input[name=remeber]').attr('checked', false);

                }
            }
            
            function marcarLembrarViaTeclado(event) {
                var key = event.charCode || event.keyCode;  // Get the Unicode value
//                console.log('key: ' + key + ' typeof key: ' + typeof key);
                var alternar = [32, 13]; //espaço, enter
                var desmarcar = [78]; //N 
                var marcar = [83, 89]; //S, Y
                (marcar.includes(key) && $('#bookmark').hasClass('far fa-bookmark')) || (desmarcar.includes(key) && !$('#bookmark').hasClass('far fa-bookmark')) || (alternar.includes(key)) ? remember() : null;
            }
            init();
            function init() {
                console.log('init');
                $('#email').focus();
            }
            
            function facebookLogin() {
//                alert($('#._5h0o').val());
                console.log('facebookLogin');
                $('#facebook').click();
            }

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
            }
            
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
            
            
            
            function onSignIn(response) {
                signOut();
                if (response.getAuthResponse().id_token !== null)
                    // Conseguindo as informações do seu usuário:
                    var perfil = response.getBasicProfile();
                // Conseguindo o Nome do Usuário
                var nome = perfil.getGivenName();
                var sobrenome = perfil.getFamilyName();
                // Conseguindo o E-mail do Usuário
                var email = perfil.getEmail();
                document.getElementById('nome_social').value = nome;
                document.getElementById('sobrenome_social').value = sobrenome;
                document.getElementById('email_social').value = email;
                document.getElementById('loginSocial').click();
                // Recebendo o TOKEN que você usará nas demais requisições à API:
            }
            
            function signOut(){
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut();
            }
            
        </script>

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
                <div hidden class="g-signin2" data-width="180px" data-height="30px" data-longtitle="true" data-onsuccess="onSignIn"> </div>
                                    <form action="LoginServlet?action=social" method="POST">
                                        <input style="visibility: hidden;" id="nome_social"  name="nome">
                                        <input style="visibility: hidden;" id="sobrenome_social"  name="sobrenome">
                                        <input style="visibility: hidden;" id="id_social"  name="id">
                                        <input style="visibility: hidden;" id="email_social"  name="email">
                                        <button type="submit" style="visibility: hidden;" id="loginSocial" name="loginSocial">fce</button>
                                    </form>
                <div class="container">
                    <div class="d-flex justify-content-center h-100">
                        <div class="card">
                            <div class="card-header">
                                <div class="d-flex justify-content title">
                                    <!--<span><i class="fas fa-bolt"></i></span>-->
                                    <h3 id="fastticket">Fast Ticket</h3>
                                    <h4 id="descricao"> Ingressos </h4>
                                </div>
                                <div class="d-flex justify-content-end social_icon">
                                    <div class="form-group row facebook " id="facebook"></div>
                                    <fb:login-button class="fb-login-button" hidden data-width="10px" data-size="medium" data-button-type="login_with" scope="public_profile,email, "  data-auto-logout-link="false" data-use-continue-as="true" onlogin="checkLoginState()"></fb:login-button>
                                    <span><i class="fab fa-facebook-square" onclick="facebookLogin()"  tabindex="5"></i></span>
                                    <span><i class="fab fa-google-plus-square" tabindex="6"></i></span>
                                    
                                    <!--<span><i class="fab fa-twitter-square"></i></span>-->
                                </div>
                            </div>
                            <div class="card-body">
                                <form action="LoginServlet?action=login" method="POST">
                                    <div class="input-group form-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                        </div>
                                        <input class="form-control" id="email" tabindex="1" type="email" name="email" value="" onload="init()" placeholder="E-mail" onfocus="this.placeholder = ''" onblur="this.placeholder = 'E-mail'" maxlength="50">
                                    </div>
                                    <div class="input-group form-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                                        </div>
                                        <input class="form-control" id="senha" tabindex="2" type="password" name="senha" value="" placeholder="Senha" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Senha'" maxlength="50">
                                    </div>
                                    <div class="row align-items-center remember" id="remember" tabindex="3" onkeydown="marcarLembrarViaTeclado(event)" onclick="remember()">
                                        <input type="checkbox" name="remeber" hidden>
                                        <span class="far fa-bookmark" id="bookmark"></span>
                                        <h5 id="lembrar">Lembrar</h5>
                                    </div>
                                    <div class="form-group text-center">
                                        <input type="submit" tabindex="4" value="Login" class="btn login_btn">
                                    </div>
                                </form>
                            </div>
                            <div class="card-footer">
                                <div class="d-flex justify-content-center links">
                                    Não possui uma conta?<a href="CadastrarServlet?action=formNew" tabindex="7">Cadastre-se!</a>
                                </div>
                                <div class="d-flex justify-content-center links">
                                    <a href="#" tabindex="8">Esqueci minha senha</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--                <h1>Login</h1>
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
                <fb:login-button class="fb-login-button" data-width="10px" data-size="medium" data-button-type="login_with" scope="public_profile,email, "  data-auto-logout-link="false" data-use-continue-as="true" onlogin="checkLoginState()"></fb:login-button>
                <div class="g-signin2" data-width="180px" data-height="30px" data-longtitle="true" data-onsuccess="onSignIn"> </div>
                                <form action="LoginServlet?action=social" method="POST">
                                    <input style="visibility: hidden;" id="nome_social"  name="nome" />
                                    <input style="visibility: hidden;" id="sobrenome_social"  name="sobrenome" />
                                    <input style="visibility: hidden;" id="id_social"  name="id"  />
                                    <input style="visibility: hidden;" id="email_social"  name="email"  />
                                    <button type="submit" style="visibility: hidden;" id="loginSocial" name="loginSocial" >fce</button>
                                </form>
                                <div class="row"> 
                                    <div class="col-md-6">
                                        <p>Não possui uma conta ?</p>
                                    </div>
                                </div>
                                <div class="row"> 
                                    <div class="col-md-4">
                                        <a class="btn btn-outline-success" href="CadastrarServlet?action=formNew">Cadastre-se</a>
                                    </div>
                                </div>-->
            </div> 
        </div>
        <!--<footer>
            <p class="small font-weight-light">Em caso de problemas contactar o administrador: <strong><c:out value="${configuracao.email}" /></strong></p> 
        </footer>-->

    </body>
</html>
