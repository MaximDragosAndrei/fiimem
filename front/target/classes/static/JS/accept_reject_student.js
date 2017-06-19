var accept_modal = document.getElementById('accept_modal');
var accept_student_btn = document.getElementById("accept_student");
var close_accept_span = document.getElementsByClassName("close_accept")[0];

accept_student_btn.onclick = function() {
    accept_modal.style.display = "block";
}

close_accept_span.onclick = function() {
    accept_modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == accept_modal) {
        accept_modal.style.display = "none";
    }
}

var reject_modal = document.getElementById('reject_modal');
var reject_student_btn = document.getElementById("reject_student");
var close_reject_span = document.getElementsByClassName("close_reject")[0];

reject_student_btn.onclick = function() {
    reject_modal.style.display = "block";
}

close_reject_span.onclick = function() {
    reject_modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == reject_modal) {
        reject_modal.style.display = "none";
    }
}

