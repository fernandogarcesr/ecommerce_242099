<%-- 
    Document   : Index
    Created on : 24 mar 2026, 14:17:53
    Author     : Fernando Garces
--%>

<%-- Index.jsp — página principal de SportZone --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SportsZone – Artículos Deportivos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<div class="grid-container">

    <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
    <%@include file="/WEB-INF/fragmentos/header.jspf" %>

    <main class="content">
        <%-- banner de bienvenida --%>
        <div class="banner-bienvenida">
            <h2>Tu equipo.<br><span>Tu rendimiento.</span></h2>
            <p>Todo lo que necesitas para entrenar y competir en un solo lugar. Calidad deportiva garantizada.</p>
            <a href="${pageContext.request.contextPath}/cargarproducto" class="btn-deportivo-accion btn-naranja">
                Ver catalogo
            </a>
        </div>

        <%-- tarjeta del desarrollador --%>
        <div class="tarjeta-autor">
            <p>
                Fernando Garces Rodriguez
                <span class="id-badge">242099</span>
            </p>
        </div>
    </main>

    <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
</div>
</body>
</html>
