<%@page import="com.noticiasee.entities.Noticia"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%

    //if (request.getAttribute("Noticias") != null) {
    Noticia noticia = (Noticia) request.getAttribute("Noticia");

%>


<html>
    <head>
        <title>NoticiasNET: <%=noticia.getTitle()%></title>
        <!-- Icono favorito -->
        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
        <link rel="icon" href="img/favicon.ico" type="image/x-icon">
        <!-- Descripcion de la pagina-->
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
        <link rel='stylesheet' media='screen and (min-width: 961px)' href='../css/desc.css'>
        <link rel='stylesheet' media='screen and (max-width: 960px)' href='../css/mob.css'>
        <!--OpenGraph meta's-->
        <meta property="og:title" content="<%=noticia.getTitle()%>">
        <meta property="og:description" content="<%=noticia.getShortDesc()%>"> 
        <meta property="og:url" content="localhost:8080/NoticiasEE-war/noticias/<%=noticia.getId()%>">
        <meta property="og:image" content="localhost:8080/NoticiasEE-war/<%=noticia.getImgLink()%>">
        <!-- rs stuff-->
        <link rel="alternate" type="application/rss+xml" title="NoticiasNET" href="rss/rss.xml">
    </head>
    <body>

        <div id="divHead" class="jumbotron text-center"><a href="../"><h1>NoticiasNET</h1></a>
            <br>
            <div id="myCarousel" class="carousel slide carousel-fade" data-ride="carousel">
                <!-- Carousel de noticias hemos de implementar leer del JSON tmb -->
                <div id="itemsCarro" class="carousel-inner">
                    <div class="item active">
                        <h3>Lifestyle: </h3>
                        <p>Nuevo termo Contigo a la venta!</p>
                    </div>
                    <div class="item">
                        <h3>Deportes: </h3>
                        <p>Ronaldo gana La Liga Santander!</p>
                    </div>
                    <div class="item">     
                        <h3>Informática: </h3>
                        <p>Logitech, tu mejor aliado para trabajar!</p>
                    </div>
                </div>
            </div>
        </div>
        <div id="divPublicidadMov"><img src="../img/mads.jpg" alt="publicidad" id="imgPublicidadMov"></div>
        <div id="divPublicidadWeb"><img src="../img/ads.jpg" alt="publicidad" id="imgPublicidad"></div>
        <div id="aire">
            <h3><%=noticia.getTitle()%></h3>
            <br/>
            <img src="../GetImg/imgnoticias/<%= noticia.getId()%>.jpg" alt="<%=noticia.getId()%>" id="imgNoticia" class="img-responsive" style="width: 80%;margin:auto;">
            <br/>
            <div id="news" class="container">
                <%=noticia.getBody()%>
            </div>            
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