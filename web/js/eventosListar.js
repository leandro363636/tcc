$(document).ready(function() {
	$('.slider').slick({
		dots: true,
		autoplay: true,
		arrows: false
	});

	// event.preventDefault();
    document.getElementById('form-logout').addEventListener("click", function() {
    	event.preventDefault();
    	document.getElementById('form-logout').submit();
    })
});

