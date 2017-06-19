$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/view/announcements/all",
        async: false,
        contentType: "application/json",
        success: function (data, status) {
            $.each(data, function (index) {
                document.getElementById("myol").innerHTML += '<li id="' + data[index].id + '" class="lista">' + data[index].title + '</li>';
            });
            initialize();
        }
    });
});
function start() {
    $(document).ready(function () {
        $.ajax({
            type: "GET",
            url: "/view/announcements/all",
            async: false,
            contentType: "application/json",
            success: function (data, status) {
                $.each(data, function (index) {
                    document.getElementById("myol").innerHTML += '<li id="' + data[index].id + '" class="lista">' + data[index].title + '</li>';
                });
                initialize();
            }
        });
    });
}
function initialize(){
    var myNodelist = document.getElementsByClassName("lista");
    var i;
    for (i = 0; i < myNodelist.length; i++) {
        var span = document.createElement("span");
        var txt = document.createTextNode("Edit");
        span.className = "edit";
        span.appendChild(txt);
        myNodelist[i].appendChild(span);
    }

    var edit = document.getElementsByClassName("edit");
    var i;
    for (i = 0; i < edit.length; i++) {
        edit[i].onclick = function() {
            alert($(edit[i]).parent().attr("id"));
        }
    }


    var myNodelist = document.getElementsByClassName("lista");
    var i;
    for (i = 0; i < myNodelist.length; i++) {
        var span = document.createElement("SPAN");
        var txt = document.createTextNode("\u00D7");
        span.className = "close";
        span.appendChild(txt);
        myNodelist[i].appendChild(span);
    }


    var close = document.getElementsByClassName("close");
    var i;
    for (i = 0; i < close.length; i++) {
        close[i].onclick = function() {
            var div = this.parentElement;
            div.style.display = "none";
            $.ajax({
                type: "POST",
                url: "/view/announcements/delete/"+$(this).parent().attr('id'),
                async: false,
                data: JSON.stringify( {
                }),
                contentType: "application/json",
                success: function() {

                },
                error: function (xhr, ajaxOptions, thrownError) {
                    if (xhr.status == 401 || xhr.status == 404) {
                        $("#errorLogin").show();
                        alert($("#errorLogin").text(xhr.responseText));
                    }
                }
            });

        }
    }
}

function newElement() {
    var li = document.createElement("li");
    var inputValue = document.getElementById("myInput").value;
    var t = document.createTextNode(inputValue);
    li.appendChild(t);
    if (inputValue === '') {
        alert("You must write something!");
    } else {
        document.getElementById("myol").appendChild(li);
    }
    document.getElementById("myInput").value = "";

    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u00D7");
    span.className = "close";
    span.appendChild(txt);
    li.appendChild(span);

    for (i = 0; i < close.length; i++) {
        close[i].onclick = function() {
            var div = this.parentElement;
            div.style.display = "none";
        }
    }
}

$( "#add_ad" ).click(function() {
    $(document).ready(function () {
        $.ajax({
            type: "POST",
            url: "/view/announcements/add",
            async: false,
            data: JSON.stringify( {
                title: $("#title_add").val(),
                text: $("#news_text").val()
            }),
            contentType: "application/json",
            success: function() {
                start();
                $("#news_modal").hide();
            },
            error: function (xhr, ajaxOptions, thrownError) {
                if (xhr.status == 401 || xhr.status == 404) {
                    $("#errorLogin").show();
                    $("#errorLogin").text(xhr.responseText);
                }
            }
        });
    });
});