<%-- 
    Document   : Register
    Created on : 24 mar 2026, 14:20:01
    Author     : Fernando Garces
--%>

<%-- Register.jsp — registro de nuevos clientes --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Crear cuenta – SporstZone</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    </head>
    <body>
        <div class="grid-container">

            <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
            <%@include file="/WEB-INF/fragmentos/header.jspf" %>

            <main class="content form-wrapper-centro">
                <div class="caja-autenticacion" style="max-width:520px;">

                    <h1>Crear <span class="marca-naranja">cuenta</span></h1>
                    <p class="subtitulo-form">Unete a SportsZone y empieza a equiparte</p>

                    <%-- error del servidor --%>
                    <% if (request.getAttribute("error") != null) { %>
                    <div class="alerta-error"><%= request.getAttribute("error") %></div>
                    <% } %>

                    <%--
                        Los nombres deben coincidir con RegistraUsuario.java:
                    --%>
                    <form action="${pageContext.request.contextPath}/registraUsuario" method="POST">

                        <div class="input-bloque">
                            <label for="txt_nombre">Nombre completo</label>
                            <input type="text" id="txt_nombre" name="txt_nombre"
                                   class="input-campo" placeholder="Juan Pérez" required>
                        </div>

                        <div class="input-bloque">
                            <label for="txt_correo">Correo electrónico</label>
                            <input type="email" id="txt_correo" name="txt_correo"
                                   class="input-campo" placeholder="usuario@correo.com" required>
                        </div>

                        <div class="input-bloque">
                            <label for="txt_password">Contraseña</label>
                            <input type="password" id="txt_password" name="txt_password"
                                   class="input-campo" placeholder="Mínimo 6 caracteres" required>
                        </div>

                        <div class="input-bloque">
                            <label for="txt_password_confirm">Confirmar contraseña</label>
                            <input type="password" id="txt_password_confirm" name="txt_password_confirm"
                                   class="input-campo" placeholder="Repite tu contraseña" required>
                        </div>

                        <div class="input-bloque">
                            <label for="txt_telefono">Teléfono (10 dígitos)</label>
                            <input type="tel" id="txt_telefono" name="txt_telefono"
                                   class="input-campo" placeholder="6441234567" 
                                   pattern="\d{10}" required>
                        </div>

                        <div class="input-bloque">
                            <label for="txt_direccion">Dirección de envío</label>
                            <input type="text" id="txt_direccion" name="txt_direccion"
                                   class="input-campo" placeholder="Calle, número, ciudad" required>
                        </div>

                        <button type="submit" class="btn-deportivo-accion btn-naranja"
                                style="width:100%; padding:.85rem; margin-top:.5rem;">
                            Crear cuenta
                        </button>
                    </form>

                    <p class="form-link">
                        ¿Ya tienes cuenta?
                        <a href="${pageContext.request.contextPath}/Login.jsp">Inicia sesión</a>
                    </p>
                </div>
            </main>
            <script src="${pageContext.request.contextPath}/js/nav.js"></script>
            <script src="${pageContext.request.contextPath}/js/register.js"></script>
            <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
        </div>
    </body>
</html>
