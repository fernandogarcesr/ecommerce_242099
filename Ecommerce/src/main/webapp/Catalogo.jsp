<%-- 
Document   : Catalogo
Created on : 24 mar 2026, 14:26:46
Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Catálogo Deportivo - SportsZone</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    </head>
    <body data-ctx="${pageContext.request.contextPath}">
        <div class="grid-container">
            <%@include file="/WEB-INF/fragmentos/aside.jspf"%>
            <%@include file="/WEB-INF/fragmentos/header.jspf"%>

            <main class="content">
                <div class="grid-catalogo">

                    <%@include file="/WEB-INF/fragmentos/aside-filtro.jspf"%>

                    <div class="catalogo-productos-seccion">
                        <div class="top-contenedor">
                            <h1>Catálogo de productos</h1>
                        </div>

                        <c:if test="${not empty requestScope.mensajeStock}">
                            <div style="background:#FFEBEE; border-left:4px solid #E53935; color:#B71C1C;
                                 padding:12px 18px; border-radius:4px; margin-bottom:1rem;
                                 font-weight:600; font-size:0.9rem;">
                                ⚠ ${requestScope.mensajeStock}
                            </div>
                        </c:if>

                        <table class="tabla-deportiva-global">
                            <thead>
                                <tr>
                                    <th style="width:50%;">Artículo deportivo</th>
                                    <th style="width:20%;">Precio</th>
                                    <th style="width:30%;text-align:center;">Operaciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${not empty requestScope.listaProductos}">
                                        <c:forEach var="p" items="${requestScope.listaProductos}">
                                            <tr>
                                                <td>
                                                    <strong>${p.nombre}</strong><br>
                                                    <span style="font-size:.82rem;color:var(--gris-texto);">
                                                        ${p.descripcion}
                                                    </span>
                                                </td>
                                                <td style="font-weight:700;color:var(--naranja);">$${p.precio}</td>
                                                <td>
                                                    <div style="display:flex;gap:5px;justify-content:center;flex-wrap:wrap;">
                                                        <a href="${pageContext.request.contextPath}/cargarproducto?id=${p.id}"
                                                           class="btn-deportivo-accion btn-sm btn-negro">Detalles</a>
                                                        <a href="${pageContext.request.contextPath}/agregarCarrito?id=${p.id}"
                                                           class="btn-deportivo-accion btn-sm btn-naranja">Añadir</a>
                                                        <a href="${pageContext.request.contextPath}/cargarproducto?id=${p.id}&vista=resenia"
                                                           class="btn-deportivo-accion btn-sm btn-outline">Reseña</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td colspan="3" style="text-align:center;padding:2rem;
                                                color:var(--gris-texto);font-weight:700;">
                                                No hay productos disponibles con esos filtros.
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </main>

            <script src="${pageContext.request.contextPath}/js/app.js"></script>
            <%@include file="/WEB-INF/fragmentos/footer.jspf"%>
        </div>
    </body>
</html>