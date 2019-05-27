  $(function() {
  	$('.datepicker').datepicker({
  		showAnim: 'slideDown',
  		dateFormat: 'd/mm/y',
    	// setDate: new Date()
    });  
  	$('.datepicker').datepicker('setDate', new Date());
  });



  $('.timepicker').timepicker({
  	timeFormat: 'HH:mm',
  	dropdown: false,
  	defaultTime: 'now',
  });

  $(function() {
  	var prntDiv = $('#lotes');
  	var i = $('#lotes p').length;

  	$('#addLote').on('click', function() {
  		i++;
  		$('<div class="col-12" id="lote' + i + '"><label for="lote' + i + '"><span>' + i + 'º lote </span><span>R$ <input type="text" value="0,00"></span></label></div>').appendTo(prntDiv);
  		return false;
  	});

  	$('#delLote').on('click', function () {
  		$('#lote' + i).remove();
  		i--;
  		return false;
  	})

  });