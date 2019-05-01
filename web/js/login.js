window.onload = function () {
	signOut();
};

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