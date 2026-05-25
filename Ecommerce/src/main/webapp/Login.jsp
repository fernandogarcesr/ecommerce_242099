<%-- 
    Document   : Login
    Created on : 24 mar 2026, 14:19:34
    Author     : Fernando Garces
--%>

<%-- Login.jsp — inicio de sesion de clientes y admin --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar sesión – SportsZone</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<div class="grid-container">

    <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
    <%@include file="/WEB-INF/fragmentos/header.jspf" %>

    <main class="content form-wrapper-centro">
        <div class="caja-autenticacion">

            <h1>Acceso <span class="marca-naranja">Deportivo</span></h1>
            <p class="subtitulo-form">Ingresa a tu cuenta de SportZone</p>

            <%-- mensaje de error del servlet --%>
            <% if (request.getAttribute("mensaje") != null) { %>
                <div class="alerta-error"><%= request.getAttribute("mensaje") %></div>
            <% } %>

            <%-- mensaje de exito si viene desde Register --%>
            <% if (request.getAttribute("mensajeExito") != null) { %>
                <div class="alerta-exito"><%= request.getAttribute("mensajeExito") %></div>
            <% } %>

            <form action="${pageContext.request.contextPath}/login" method="POST">

                <div class="input-bloque">
                    <label for="correo">Correo electrónico</label>
                    <input type="email" id="correo" name="correo"
                           class="input-campo" placeholder="usuario@correo.com" required>
                </div>

                <div class="input-bloque">
                    <label for="contrasenia">Contraseña</label>
                    <input type="password" id="contrasenia" name="contrasenia"
                           class="input-campo" placeholder="••••••••" required>
                </div>

                <button type="submit" class="btn-deportivo-accion btn-naranja"
                        style="width:100%; padding:.85rem; margin-top:.5rem;">
                    Entrar
                </button>
            </form>

            <p class="form-link">
                ¿No tienes cuenta?
                <a href="${pageContext.request.contextPath}/Register.jsp">Regístrate aqui</a>
            </p>
            <p class="form-link" style="margin-top:.5rem;">
                <a href="${pageContext.request.contextPath}/Index.jsp"
                   style="color:var(--gris-texto);">← Volver al inicio</a>
            </p>
        </div>
    </main>
    <script src="${pageContext.request.contextPath}/js/nav.js"></script>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
    <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
</div>
</body>
</html>
