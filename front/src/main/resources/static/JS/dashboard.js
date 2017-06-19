/**
 * Created by cosmin on 6/12/2017.
 */
/**
 * Created by cosmin on 6/12/2017.
 */

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/view/announcements",
        async: false,
        contentType: "application/json",
        success: function(data,status) {
            $.each(data, function(index) {
                document.getElementById("annoncements").innerHTML += '<p><b id = "title-ad">'+ data[index].title +'-</b>'+ data[index].text+ '</p>';
            });
            document.getElementById("annoncements").innerHTML += '<div id = "load_more" ><a href="#" onclick="load_more('+data[data.length-1].id+')">Load More</a></div>';
        }
    });
});

function load_more(id){
    $("#load_more").remove();
    $(document).ready(function () {
        $.ajax({
            type: "GET",
            url: "/view/announcements/"+id,
            async: false,
            contentType: "application/json",
            success: function(data,status) {
                $.each(data, function(index) {
                    document.getElementById("annoncements").innerHTML += '<p><b id = "title-ad">'+ data[index].title +'-</b>'+ data[index].text+ '</p>';
                });
                document.getElementById("annoncements").innerHTML += '<div id = "load_more" ><a href="#" onclick="load_more('+data[data.length-1].id+')">Load More</a></div>';
            }
        });
    });

}