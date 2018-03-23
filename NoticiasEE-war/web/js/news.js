var cargadas = 3;
var hayMas=true;


$(document).ready(function () {

    //esto hace que al cargar la pagina nos posicionemos arriba del todo.
    $('html, body').animate({scrollTop: 0}, 100);
    $("#publicarNoticia").click(function () {
        window.location.href='./adminPanel.html'
    });
    //si hacemos click en el botón con id "mas" hara que carguemos más noticias.
    $("#mas").click(function () {
        $("#loading").fadeIn("slow");
        getMoreNews();
    });

    // Cuando el usuario hace scroll:
    /*var win = $(window);
     win.scroll(function () {
     // Si hemos alcanzado la pagina de abajo.
     if ($(document).height() - win.height() == win.scrollTop()) {
     //call ajax
     
     }
     });*/
});

$(window).scroll(function () {
    if ($(window).scrollTop() + $(window).height() >= $(document).height() && hayMas) {
        $("#loading").fadeIn("slow");
        getMoreNews();
    }
});

function getMoreNews() {


    $.ajax({
        async: false,
        method: "POST",
        url: "GetMoreNews",
        data: {cargadas: cargadas},
        success: function (rsp) {
            if (rsp["mess"] === "No hay más noticias") {
                $("#loading").fadeOut("fast");
                $("#noMas").fadeIn(2500);
                $("#noMas").fadeOut(2500);
                hayMas=false;
            } else {
                cargarNoticias(rsp);
            }

        },
        error: function (e) {
            alert("Ha ocurrido un problema");
        }

    });
}

//metodo que si lo ejecutamos cargara más noticias si no hemos alcanzado el limite de noticias que tenemos en los ficherosJSON.
function cargarNoticias(json) {
    $.each(json, function (i, noticia) {
        //añadir noticia a la pagina <a href="/NoticiasEE-war/noticias/<%=n.getId()%>"><h3><%=n.getTitle()%>:</h3></a>
        $("#aire").append("<a href='/NoticiasEE-war/noticias/"+noticia.id+"'> <h3>" + noticia.title + "</h3></a>" +
                " <div id='nImg'>" +
                " <img src='./GetImg/imgnoticias/" + noticia.id + ".jpg' alt='" + noticia.id + "' id='imgNoticia' class='img-thumbnail'>" +
                "</div> <br/> <div id='news' class='container'>" +
                "<p>" + noticia.shortDesc + "</p> <footer id='date' class='small'>" + noticia.posted + "</footer>" +
                " <button id='seguirLeyendo' type='button' class='btn btn-default'>Seguir leyendo</button> </div> <hr>");
        cargadas++;

    });

}

