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
    <body>
        <div class="grid-container">
            <%@include file="/WEB-INF/fragmentos/aside.jspf" %> 
            <%@include file="/WEB-INF/fragmentos/header.jspf" %>

            <main class="content">
                <div class="grid-catalogo">

                    <%@include file="/WEB-INF/fragmentos/aside-filtro.jspf" %>

                    <div class="catalogo-productos-seccion">
                        <div class="top-contenedor">
                            <h1>Catálogo de Productos</h1>
                        </div>

                        <table class="tabla-deportiva-global">
                            <thead>
                                <tr>
                                    <th style="width: 50%;">Artículo Deportivo</th>
                                    <th style="width: 20%;">Precio</th>
                                    <th style="width: 25%; text-align: center;">Operaciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${not empty requestScope.listaProductos}">
                                        <c:forEach var="p" items="${requestScope.listaProductos}">
                                            <tr>

                                                <td>
                                                    <strong>${p.nombre}</strong><br>
                                                    <span style="font-size: 0.8rem; color: #64748b;">${p.descripcion}</span>
                                                </td>
                                                <td style="font-weight: 700; color: var(--naranja);">$${p.precio}</td>
                                                <td>
                                                    <div style="display: flex; gap: 5px; justify-content: center; flex-wrap:wrap;">
                                                        <a href="${pageContext.request.contextPath}/cargarproducto?id=${p.id}" class="btn-deportivo-accion btn-sm">Detalles</a>
                                                        <a href="${pageContext.request.contextPath}/Carrito.jsp" class="btn-deportivo-accion btn-sm btn-naranja">Añadir</a>
                                                        <a href="${pageContext.request.contextPath}/CrearResenia.jsp?id=${p.id}" class="btn-deportivo-accion btn-sm btn-outline">Reseña</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td colspan="3" style="text-align: center; padding: 2rem; color: var(--gris-texto); font-weight: 700;">
                                                No hay productos disponibles en el catalogo.
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>

                </div>
            </main>

            <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
        </div>
    </body>
</html>