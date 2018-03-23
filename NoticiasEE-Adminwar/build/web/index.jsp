<%@page import="com.noticiasee.entities.Noticia"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%

    List<Noticia> noticias = (List<Noticia>) request.getAttribute("Noticias");
    if (noticias == null) {
        RequestDispatcher goBack = request.getRequestDispatcher("/LastNews"); //o sustituir por raiz
        goBack.forward(request, response);
    }

%>

<html>
    <head>
        <title>NoticiasNET de gente que se entero tres días después</title>
        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
        <link rel="icon" href="img/favicon.ico" type="image/x-icon">
        <meta name="description" content="Página web de noticias que es gestionada por personas que no sabían muy bien de que iba esto del periodismo y mantener a las personas informadas.">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--Boostrap-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- GoogleFonts -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat+Subrayada" rel="stylesheet">
        <!--my css-->
        <link rel='stylesheet' media='screen and (min-width: 961px)' href='css/desc.css'>
        <link rel='stylesheet' media='screen and (max-width: 960px)' href='css/mob.css'>
        <meta property="og:title" content="NoticiasNET en tiempo real">
        <meta property="og:description" content="Noticias que es gestionada por personas que no sabían muy bien de que iba esto del periodismo">
        <meta property="og:url" content="https://rawgit.com/pacho21/noticiasweb/master/index.html">
        <meta property="og:image" content="https://rawgit.com/pacho21/noticiasweb/master/img/bhead.jpg">
        <!-- rs stuff-->
        <link rel="alternate" type="application/rss+xml" title="NoticiasNET" href="rss/rss.xml">
        <script src="js/news.js"></script>
    </head>
    <body>

        <div id="divHead" class="jumbotron text-center"><h1>NoticiasNET</h1>
            <br>
            <div id="myCarousel" class="carousel slide carousel-fade" data-ride="carousel" data-spy="affix" data-offset-top="330">
                <!-- Carousel de noticias hemos de implementar leer del JSON tmb -->
                <div id="itemsCarro" class="carousel-inner">
                    <%  int a = 1;
                        for (Noticia n : noticias) {%>
                    <% if (a == 1) {%>
                    <div class="item active">                        
                        <a href="/NoticiasEE-war/noticias/<%=n.getId()%>"><h3><%=n.getTitle()%>:</h3></a>
                        <p><%=n.getShortDesc().substring(0, 50) + "..."%></p>
                    </div>
                    <%} else {%>
                    <div class="item">                        
                        <h3><%=n.getTitle()%>:</h3>
                        <p><%=n.getShortDesc().substring(0, 50) + "..."%></p>
                    </div>
                    <%}
                        a++;%>
                    <%}%>
                </div>
            </div>
        </div>
        <div id="divPublicidadMov"><img src="img/mads.jpg" alt="publicidad" id="imgPublicidadMov"></div>
        <div id="divPublicidadWeb"><img src="img/ads.jpg" alt="publicidad" id="imgPublicidad"></div>
        <div id="aire">
            <%for (Noticia n : noticias) {%>
            <a href="/NoticiasEE-war/noticias/<%=n.getId()%>"><h3><%=n.getTitle()%></h3></a>
            <div id="nImg">
                <img src="./GetImg/imgnoticias/<%= n.getId()%>.jpg" alt="<%= n.getId()%>" id="imgNoticia" class="img-thumbnail">
            </div>
            <br/>
            <div id="news" class="container">
                <p><%= n.getShortDesc()%></p><footer id="date" class="small"><%=n.getPosted()%> autor: <%=n.getAuthor()%></footer>
                <button id="seguirLeyendo" type="button" class="btn btn-default" onclick="window.location.href = '/NoticiasEE-war/noticias/<%=n.getId()%>'">Seguir leyendo</button>
            </div>
            <hr>
            <%}%>
        </div>
        <div id="noMas" class="alert alert-danger"><p>No hay más noticias!</p></div>
        <div id="loading" class="alert alert-info"><p>Cargando más noticias!</p></div>
        <div id="cargar"><button id="mas" type="button" class="btn btn-default">Cargar Más</button></div>
        <div id="mybutton">
            <button id="publicarNoticia" class="feedback">Insertar Noticia</button>
        </div>
        <footer class="footer">
            <div class="container">
                <ul class="footer-links"> 
                    <li>Facebook <span class="glyphicon glyphicon-share"></span></li> 
                    <li>Location <span class="glyphicon glyphicon-map-marker"></span>
                    <li>About</li> 
                </ul>
                <p>Designed by <br/> Plamen Valentinov <span class="glyphicon glyphicon-pawn"></span></p> 
            </div>
        </footer>
    </body>
</html>