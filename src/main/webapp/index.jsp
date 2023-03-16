<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@page
import="alumnos.*" %> <%@page import="grupos.*" %> <%@page import="connection.*"
%> <%@page import="aplicacion.*" %>

<!DOCTYPE html>
<html>
    <!-- CSS Boostrap -->
    <link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous"
    />

    <!-- JS Bootstrap (Opcional) -->
    <script
        src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://kit.fontawesome.com/ad22caf6eb.js"
        crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="assets/css/style.css" />
    <title>Gestalum</title>
    <link rel="shortcut icon" href="assets/img/letra-g.png" type="image/x-icon">
    <body>
        <body>
            <% Navbar navbar=new Navbar(""); out.print(navbar); Footer
            footer=new Footer(); out.print(footer); %>
            <div class="container">
                <div class="row">
                    <div class="col text-center m-5 display-1">Gestalum</div>
                </div>
                <div class="row">
                    <div class="col text-center">
                        <h3>
                            Proyecto de gestión de base de datos MySQL realizado
                            con Java y JSP
                        </h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col text-center">
                        <img
                            src="assets/img/mysql.png"
                            class="logo m-4"
                            alt="Imagen MySQL"
                        />
                        <img
                            src="assets/img/java.png"
                            class="logo m-4"
                            alt="Imagen Java"
                        />
                        <img
                            src="assets/img/jsp.png"
                            class="logo m-4"
                            alt="Imagen JSP"
                        />
                    </div>
                </div>
            </div>
        </body>
    </body>
</html>
