/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validarCPF(cpf) {  
    cpf = cpf.replace(/[^\d]+/g,'');    
    if(cpf == '') return false; 
    // Elimina CPFs invalidos conhecidos    
    if (cpf.length != 11 || 
        cpf == "00000000000" || 
        cpf == "11111111111" || 
        cpf == "22222222222" || 
        cpf == "33333333333" || 
        cpf == "44444444444" || 
        cpf == "55555555555" || 
        cpf == "66666666666" || 
        cpf == "77777777777" || 
        cpf == "88888888888" || 
        cpf == "99999999999")
            return false;       
    // Valida 1o digito 
    add = 0;    
    for (i=0; i < 9; i ++)       
        add += parseInt(cpf.charAt(i)) * (10 - i);  
        rev = 11 - (add % 11);  
        if (rev == 10 || rev == 11)     
            rev = 0;    
        if (rev != parseInt(cpf.charAt(9)))     
            return false;       
    // Valida 2o digito 
    add = 0;    
    for (i = 0; i < 10; i ++)        
        add += parseInt(cpf.charAt(i)) * (11 - i);  
    rev = 11 - (add % 11);  
    if (rev == 10 || rev == 11) 
        rev = 0;    
    if (rev != parseInt(cpf.charAt(10)))
        return false;       
    return true;   
}


$(function () {
    $("#inputcpf").on("keyup", function() {
        if(validarCPF($(this).val())){
            $("#divcpf").removeClass('invalid-feedback');
            $("#divcpf").addClass('valid-feedback');
            $("#divcpf").text("CFP Válido");
            $("#inputcpf").removeClass('is-invalid');
            $("#inputcpf").addClass('is-valid');
        } else {
            $("#divcpf").removeClass('valid-feedback');
            $("#divcpf").addClass('invalid-feedback');
            $("#divcpf").text("CFP Inválido");
            $("#inputcpf").removeClass('is-valid');
            $("#inputcpf").addClass('is-invalid');
        }
    });
    $("form").submit(function (event) {
        if($("#inputcpf").hasClass('is-invalid')){
            event.preventDefault();
        }
    });
    $.datepicker.regional['pt-BR'] = {
        closeText: 'Fechar',
        prevText: '&#x3c;Anterior',
        nextText: 'Pr&oacute;ximo&#x3e;',
        currentText: 'Hoje',
        monthNames: ['Janeiro', 'Fevereiro', 'Mar&ccedil;o', 'Abril', 'Maio', 'Junho',
            'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
        monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun',
            'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
        dayNames: ['Domingo', 'Segunda-feira', 'Ter&ccedil;a-feira', 'Quarta-feira', 'Quinta-feira', 'Sexta-feira', 'Sabado'],
        dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
        dayNamesMin: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
        weekHeader: 'Sm',
        dateFormat: 'dd/mm/yy',
        firstDay: 0,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ''};
    $.datepicker.setDefaults($.datepicker.regional['pt-BR']);
    $( "#datepicker" ).datepicker();
    
    $('.cpf').mask('000.000.000-00');
    
    $('.cep').mask('00000-000');
    
    $('.data').mask('00/00/0000');
});


$(document).ready(function () {
    $("#estado").change(function () {
        getCidades();
    });
});

function getCidades() {
    var estadoId = $("#estado").val();
    var url = "AJAXServlet";
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
