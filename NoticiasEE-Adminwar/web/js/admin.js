/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {
    //iniciar la barra/ editor de tinymce
    tinymce.init({
        selector: '#mytextarea',
        theme: 'modern',
        plugins: 'autoresize wordcount',
        width: '100%',
        entity_encoding: "raw",
        encoding: "UTF-8",
        height: '300',
        autoresize_min_width: 375,
        autoresize_max_width: 900,
        toolbar: [
            'undo, redo, bold, italic, underline, strikethrough, alignleft, aligncenter, alignright, alignjustify', 'cut, copy, paste, bullist, numlist, outdent, indent, blockquote'
        ]
    });
    //otros
    $("#subNoticia").submit(function () {
        $("#sendNoticia").prop("disabled", true);
        var theEditor = tinymce.activeEditor;
        var wc = theEditor.plugins.wordcount.getCount();
        if (wc > 100) {
            addNoticia();
            $("#sendNoticia").prop("disabled", false);
            return false;
        } else {
            $("#sendNoticia").prop("disabled", false);
            //show alert "noticia ha de tener más de 100 palabras".
            return false;

        }
    });

});

//post 
function addNoticia() {

    var noticia = new FormData($("#subNoticia")[0]);
    $("body").addClass("loading");
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "AddNew",
        data: noticia,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 60000,
        success: function (rsp) {
            
            window.location.href='./noticias/'+rsp["id"]
        },
        error: function (e) {
            if (e["responseJSON"] === undefined) {
                alert("Ha ocurrido un error inesperado!");
                $("#sendNoticia").prop("disabled", false);
                $("body").removeClass("loading");
            } else {
                alert(e["responseJSON"]["error"]);
                $("#sendNoticia").prop("disabled", false);
                $("body").removeClass("loading");
            }
        }
    });
}