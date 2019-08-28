/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    console.log('a');
    $("#estado").change(function () {
        getCidades();
    });
});

function getCidades() {
    var estadoId = $("#estado").val();
    var url = "CidadeServlet";
    $.ajax({
        url: url, // URL da sua Servlet
        data: {
            estadoId: estadoId
        }, // Parâmetro passado para a Servlet
        dataType: 'json',
        success: function (data) {
            // Se sucesso, limpa e preenche a combo de cidade
            // alert(JSON.stringify(data));
            $("#cidade").empty();
            $.each(data, function (i, obj) {
                $("#cidade").append('<option value=' + obj.id + '>' + obj.nome + '</option>');
            });
        },
        error: function (request, textStatus, errorThrown) {
            alert(request.status + ', Error: ' + request.statusText);
            // Erro
        }
    });
}
