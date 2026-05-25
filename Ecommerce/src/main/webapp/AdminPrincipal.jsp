<%-- 
    Document   : AdminPrincipal
    Created on : 24 mar 2026, 14:26:04
    Author     : Fernando Garces
--%>

<%-- AdminPrincipal.jsp — panel central del administrador --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel Admin – SportsZone</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<div class="grid-container">

    <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
    <%@include file="/WEB-INF/fragmentos/header.jspf" %>

    <main class="content">
        <div class="top-contenedor">
            <h1>Panel de administración</h1>
        </div>

        <div class="grid-admin-cards">

            <a href="${pageContext.request.contextPath}/administrar-usuarios" class="admin-card-link">
                <div class="icono-card">&#9786;</div>
                <div>
                    <div>Usuarios</div>
                    <div style="font-size:.75rem; font-weight:600; color:var(--gris-texto); text-transform:none; margin-top:2px;">
                        Activar, desactivar y eliminar cuentas
                    </div>
                </div>
            </a>

            <a href="${pageContext.request.contextPath}/cargarproducto?vista=adminProducto" class="admin-card-link">
                <div class="icono-card">&#9632;</div>
                <div>
                    <div>Productos</div>
                    <div style="font-size:.75rem; font-weight:600; color:var(--gris-texto); text-transform:none; margin-top:2px;">
                        Crear, editar y eliminar artículos
                    </div>
                </div>
            </a>

            <a href="${pageContext.request.contextPath}/cargarpedidos" class="admin-card-link">
                <div class="icono-card">&#9776;</div>
                <div>
                    <div>Pedidos</div>
                    <div style="font-size:.75rem; font-weight:600; color:var(--gris-texto); text-transform:none; margin-top:2px;">
                        Actualizar estado de cada orden
                    </div>
                </div>
            </a>

            <a href="${pageContext.request.contextPath}/AdministrarResenias" class="admin-card-link">
                <div class="icono-card">&#9998;</div>
                <div>
                    <div>Reseñas</div>
                    <div style="font-size:.75rem; font-weight:600; color:var(--gris-texto); text-transform:none; margin-top:2px;">
                        Moderar y eliminar comentarios
                    </div>
                </div>
            </a>

        </div>
    </main>
    <script src="${pageContext.request.contextPath}/js/nav.js"></script>
    <script src="${pageContext.request.contextPath}/js/adminPrincipal.js"></script>
    <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
</div>
</body>
</html>