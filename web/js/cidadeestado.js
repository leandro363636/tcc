$(document).ready(function () {

	$.getJSON('cidadeestado.json', function (data) {
		var items = [];
		var options = '<option value="">Escolha um estado</option>';	
		$.each(data, function (key, val) {
			options += '<option value="' + val.nome + '">' + val.nome + '</option>';
		});					
		$("#cEstado").html(options);				

		$("#cEstado").change(function () {				

			var options_cidades = '';
			var str = "";					

			$("#cEstado option:selected").each(function () {
				str += $(this).text();
			});

			$.each(data, function (key, val) {
				if(val.nome == str) {							
					$.each(val.cidades, function (key_city, val_city) {
						options_cidades += '<option value="' + val_city + '">' + val_city + '</option>';
					});							
				}
			});
			$("#cCidade").html(options_cidades);

		}).change();		

	});

});