var postTo = "https://localhost:8090/preform_post";

$(document).ready(function () {
    $('#MyPreform').attr('action', postTo);

    function sendForm(formId){
        $.post(
            $(formId).attr("action"),
            $(formId).serialize()
        );
    }

    $('#submit').on('click', function (e) {
        sendForm("#MyPreform");
        window.location.replace("https://localhost:8090/form");
    });
});
