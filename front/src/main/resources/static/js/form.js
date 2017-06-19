function encodeImageFileAsURL(element) {
	var file = element.files[0];
	var reader = new FileReader();
	reader.readAsDataURL(file);
	var formData = new FormData();
	reader.onloadend = function() {
		var ex = (reader.result).split("+").join("%2B");
		formData.append(element.name, ex);
		$.ajax({
			url: "https://localhost:8090/form_save",
			type: "POST",
			cache: false,
			contentType: false,
			processData: false,
			data: formData})
				.done(function(e){
				    alert('Poza a fost adaugata in baza de date!');
		});
	}
}

function sendForm(formId){
	$.post(
		$(formId).attr("action"),
		$(formId).serialize()
	);
}

$(document).ready(function () {

    var navListItems = $('ul.setup-panel li a'),
        allWells = $('.setup-content');

    allWells.hide();

    navListItems.click(function (e) {
        e.preventDefault();
        var $target = $($(this).attr('href')),
            $item = $(this).closest('li');

        if (!$item.hasClass('disabled')) {
            navListItems.closest('li').removeClass('active');
            $item.addClass('active');
            allWells.hide();
            $target.show();
        }
    });

    $('ul.setup-panel li.active a').trigger('click');

    // DEMO ONLY //
    $('#activate-step-2').on('click', function (e) {
        sendForm("#myform");
        $('ul.setup-panel li:eq(1)').removeClass('disabled');
        $('ul.setup-panel li a[href="#step-2"]').trigger('click');
    });

    $('#activate-step-3').on('click', function (e) {
        sendForm("#myform2");
        $('ul.setup-panel li:eq(2)').removeClass('disabled');
        $('ul.setup-panel li a[href="#step-3"]').trigger('click');
    });
    $('#activate-step-4').on('click', function (e) {
        sendForm("#myform3");
        $('ul.setup-panel li:eq(3)').removeClass('disabled');
        $('ul.setup-panel li a[href="#step-4"]').trigger('click');
    });
    $('#activate-step-5').on('click', function (e) {
        $('ul.setup-panel li:eq(4)').removeClass('disabled');
        $('ul.setup-panel li a[href="#step-5"]').trigger('click');
    });
    $('#activate-step-6').on('click', function (e) {
        sendForm("#myform5");
        $('ul.setup-panel li:eq(5)').removeClass('disabled');
        $('ul.setup-panel li a[href="#step-6"]').trigger('click');
    });
    $('#activate-step-7').on('click', function (e) {
        sendForm("#myform6");
        $('ul.setup-panel li:eq(6)').removeClass('disabled');
        $('ul.setup-panel li a[href="#step-7"]').trigger('click');
    });
    $('#activate-step-8').on('click', function (e) {
        $('ul.setup-panel li:eq(7)').removeClass('disabled');
        $('ul.setup-panel li a[href="#step-8"]').trigger('click');
    });
    $('#finish-button').on('click', function (e) {
        sendForm("#myform8");
    })
});

function onlyLetters(name){

    var x = document.getElementsByName(name)[0].value;
    var nameRegex = /(^\s*$)|(^[ a-zA-Z\-]+$)/;
    var idError = name.concat('-error');
    if (!nameRegex.test(x)){
        document.getElementById(idError).style.display = 'block';
		document.getElementById("activate-step-2").style.display = 'none';
		document.getElementById("activate-step-3").style.display = 'none';
		}
    else{
        document.getElementById(idError).style.display = 'none';
		document.getElementById("activate-step-2").style.display = 'inline-block';
		document.getElementById("activate-step-3").style.display = 'inline-block';
	}

}

function parentInitial(){
    var x = document.getElementsByName("parent-initial-alfa")[0].value;
    var nameRegex = /(^\s*$)|(([A-Z]\.)+$)/;
    if (!nameRegex.test(x)){
        document.getElementById("parent-initial-alfa-error").style.display = 'block';
		document.getElementById("activate-step-2").style.display = 'none';
		document.getElementById("activate-step-3").style.display = 'none';
	}
    else{
        document.getElementById("parent-initial-alfa-error").style.display = 'none';
		document.getElementById("activate-step-2").style.display = 'inline-block';
		document.getElementById("activate-step-3").style.display = 'inline-block';
	}
}

function CNP(){
    var x = document.getElementsByName("initial-name")[0].value;
    var nameRegex = /(^\s*$)|(\b[1-8]\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])(0[1-9]|[1-4]\d|5[0-2]|99)\d{4}\b)/;
    if (!nameRegex.test(x)){
        document.getElementById("initial-name-error").style.display = 'block';
		document.getElementById("activate-step-2").style.display = 'none';	
		document.getElementById("activate-step-3").style.display = 'none';	
	}
    else{
        document.getElementById("initial-name-error").style.display = 'none';
		document.getElementById("activate-step-2").style.display = 'inline-block';
		document.getElementById("activate-step-3").style.display = 'inline-block';
	}
}

function checkDate(name){

    var x = document.getElementsByName(name)[0].value;
    var nameRegex = /(^\s*$)|([0-3][0-9]\.[0-3][0-9]\.[0-9][0-9][0-9][0-9])/;
    var idError = name.concat('-error');
    if (!nameRegex.test(x)){
        document.getElementById(idError).style.display = 'block';
		document.getElementById("activate-step-2").style.display = 'none';
		document.getElementById("activate-step-3").style.display = 'none';		
	}
    else{
        document.getElementById(idError).style.display = 'none';
		document.getElementById("activate-step-2").style.display = 'inline-block';
		document.getElementById("activate-step-3").style.display = 'inline-block';
	}
}

function twoLetters(name){

    var x = document.getElementsByName(name)[0].value;
    var nameRegex = /(^\s*$)|([A-Z][A-Z])/;
    var idError = name.concat('-error');
    if (!nameRegex.test(x)){
        document.getElementBId(idError).style.display = 'block';
		document.getElementById("activate-step-2").style.display = 'none';	
		document.getElementById("activate-step-3").style.display = 'none';	
	}
    else{
        document.getElementById(idError).style.display = 'none';
		document.getElementById("activate-step-2").style.display = 'inline-block';
		document.getElementById("activate-step-3").style.display = 'inline-block';
	}
}

function checkNrCNP(name){

    var x = document.getElementsByName(name)[0].value;
    var nameRegex = /(^\s*$)|(^[0-9][0-9][0-9][0-9][0-9][0-9])/;
    var idError = name.concat('-error');
    if (!nameRegex.test(x)){
        document.getElementById(idError).style.display = 'block';
		document.getElementById("activate-step-2").style.display = 'none';
		document.getElementById("activate-step-3").style.display = 'none';		
	}
    else{
        document.getElementById(idError).style.display = 'none';
		document.getElementById("activate-step-2").style.display = 'inline-block';
		document.getElementById("activate-step-3").style.display = 'inline-block';
	}
}

function checkNumber(name){

    var x = document.getElementsByName(name)[0].value;
    var nameRegex = /(^\s*$)|(^[0-9]+$)/;
    var idError = name.concat('-error');
    if (!nameRegex.test(x)){
        document.getElementById(idError).style.display = 'block';
		document.getElementById("activate-step-2").style.display = 'none';
		document.getElementById("activate-step-3").style.display = 'none';		
	}
    else{
        document.getElementById(idError).style.display = 'none';
		document.getElementById("activate-step-2").style.display = 'inline-block';
		document.getElementById("activate-step-3").style.display = 'inline-block';
	}
}

function checkPhone(name){

    var x = document.getElementsByName(name)[0].value;
    var nameRegex = /(^\s*$)|(^07[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$)/;
    var idError = name.concat('-error');
    if (!nameRegex.test(x)){
        document.getElementById(idError).style.display = 'block';
		document.getElementById("activate-step-2").style.display = 'none';	
		document.getElementById("activate-step-3").style.display = 'none';	
	}
    else{
        document.getElementById(idError).style.display = 'none';
		document.getElementById("activate-step-2").style.display = 'inline-block';
		document.getElementById("activate-step-3").style.display = 'inline-block';
	}
}

function checkEmail(name){

    var x = document.getElementsByName(name)[0].value;
    var nameRegex = /(^\s*$)|((?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\]))/;
    var idError = name.concat('-error');
    if (!nameRegex.test(x)){
        document.getElementById(idError).style.display = 'block';
		document.getElementById("activate-step-2").style.display = 'none';	
		document.getElementById("activate-step-3").style.display = 'none';	
	}
    else{
        document.getElementById(idError).style.display = 'none';
		document.getElementById("activate-step-2").style.display = 'inline-block';
		document.getElementById("activate-step-3").style.display = 'inline-block';
	}
}


function checkGrade(name){

    var x = document.getElementsByName(name)[0].value;
    var nameRegex = /(^\s*$)|((?:\d*\.)?\d+)/;
    var idError = name.concat('-error');
    if (!nameRegex.test(x)){
        document.getElementById(idError).style.display = 'block';
		document.getElementById("activate-step-2").style.display = 'none';	
		document.getElementById("activate-step-3").style.display = 'none';	
	}
    else{
        document.getElementById(idError).style.display = 'none';
		document.getElementById("activate-step-2").style.display = 'inline-block';
		document.getElementById("activate-step-3").style.display = 'inline-block';
	}
}


function onlyLettersAndNumbers(name){

    var x = document.getElementsByName(name)[0].value;
    var nameRegex = /(^\s*$)|(^[a-zA-Z0-9_.-]*$)/;
    var idError = name.concat('-error');
    if (!nameRegex.test(x)){
        document.getElementById(idError).style.display = 'block';
		document.getElementById("activate-step-2").style.display = 'none';		
		document.getElementById("activate-step-3").style.display = 'none';
	}
    else{
        document.getElementById(idError).style.display = 'none';
		document.getElementById("activate-step-2").style.display = 'inline-block';
		document.getElementById("activate-step-3").style.display = 'inline-block';
	}
}