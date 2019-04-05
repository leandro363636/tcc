/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
jQuery(document).ready(function () {
    'use strict';
    jQuery.datetimepicker.setLocale('pt');
    jQuery('#filter-date, #search-from-date, #search-to-date').datetimepicker();
});
$(document).ready(function () {
    $(document).on('click', '#adicionar-lote', function() {
        inserirLote();
    });
    $(document).on('click', '#alterar-lote', function() {
        editarLote();
    });
    listarLote();
});

function inserirLote() {
    var nomeLote = $('input[name="nomeLote"]').val();
    var qtdLote = $('input[name="qtdLote"]').val();
    var precoLote = $('input[name="preçoLote"]').val();
    var idEvento = $('input[name="id"]').val();
    var acao = "adicionar"
    var url = "LoteServlet";
    $.ajax({
        url: url, // URL da sua Servlet
        data: {
            nomeLote: nomeLote,
            qtdLote: qtdLote,
            precoLote: precoLote,
            idEvento: idEvento,
            acao: acao 
        }, // Parâmetro passado para a Servlet
        dataType: 'json',
        success: function (data) {
            // Se sucesso, limpa e preenche a combo de cidade
            console.log(data);
            listarLote();
        },
        error: function (request, textStatus, errorThrown) {
            alert(request.status + ', Error: ' + errorThrown);
            // Erro
        }
    });
}

function listarLote() {
    var idEvento = $('input[name="id"]').val();
    var acao = "listar"
    var url = "LoteServlet";
    $.ajax({
        url: url, // URL da sua Servlet
        data: {
            idEvento: idEvento,
            acao: acao 
        }, // Parâmetro passado para a Servlet
        dataType: 'json',
        success: function (data) {
            // Se sucesso, limpa e preenche a combo de cidade
            console.log(JSON.stringify(data));
            $("#lotesList").empty();
            if (data.length > 0) {
                $("#lotesList").prepend('<h3>Lotes:</h3>');
                $.each(data, function (i, obj) {
                    $("#lotesList").append('<li class="list-group-item">' + obj.nome +'<button data-id="' + obj.id +'" class="deletar-lote float-right btn btn-danger" type="button"><i class="ion-trash-a"></i></button><button data-quantidade="' + obj.quantidade +'" data-preco="' + obj.preço +'" data-nome="' + obj.nome +'" data-id="' + obj.id +'" class="editar-lote float-right btn btn-info" type="button"><i class="ion-edit"></i></button></li>');
                });
                $(".deletar-lote").click(function () {
                    var idLote = $(this).data("id");
                    deletarLote(idLote);
                });
                $(".editar-lote").click(function () {
                    var idLote = $(this).data("id");
                    $("#nomeLoteForm").prepend('<input id="idLote" type="hidden" value="' + idLote + '">');
                    $('input[name="nomeLote"]').val($(this).data("nome"));
                    $('input[name="qtdLote"]').val($(this).data("quantidade"));
                    $('input[name="preçoLote"]').val($(this).data("preco"));
                     $('#adicionar-lote').text('Alterar Lote');
                     $('#adicionar-lote').attr('id','alterar-lote');
                });
                
            }
        },
        error: function (request, textStatus, errorThrown) {
            alert(request.status + ', Error: ' + errorThrown);
            // Erro
        }
    });
}

function deletarLote(idLote) {
    var acao = "deletar"
    var url = "LoteServlet";
    $.ajax({
        url: url, // URL da sua Servlet
        data: {
            idLote: idLote,
            acao: acao 
        }, // Parâmetro passado para a Servlet
        dataType: 'json',
        success: function (data) {
            // Se sucesso, limpa e preenche a combo de cidade
            console.log(data);
            listarLote();
        },
        error: function (request, textStatus, errorThrown) {
            alert(request.status + ', Error: ' + errorThrown);
            // Erro
        }
    });
}

function editarLote() {
    var nomeLote = $('input[name="nomeLote"]').val();
    var qtdLote = $('input[name="qtdLote"]').val();
    var precoLote = $('input[name="preçoLote"]').val();
    var idEvento = $('input[name="id"]').val();
    var idLote = $('#idLote').val();
    var acao = "editar";
    var url = "LoteServlet";
    $.ajax({
        url: url, // URL da sua Servlet
        data: {
            nomeLote: nomeLote,
            qtdLote: qtdLote,
            precoLote: precoLote,
            idEvento: idEvento,
            idLote: idLote,
            acao: acao 
        }, // Parâmetro passado para a Servlet
        dataType: 'json',
        success: function (data) {
            // Se sucesso, limpa e preenche a combo de cidade
            console.log(data);
            $('#alterar-lote').text('Adicionar Lote');
            $('#alterar-lote').attr('id','adicionar-lote');
            listarLote();
        },
        error: function (request, textStatus, errorThrown) {
            alert(request.status + ', Error: ' + errorThrown);
            // Erro
        }
    });
}