<%-- AdminCatalogo.jsp — inventario de productos para el admin --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Catálogo Admin – SportsZone</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    </head>
    <body>
        <div class="grid-container">
            <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
            <%@include file="/WEB-INF/fragmentos/header.jspf" %>
            <main class="content">
                <a href="${pageContext.request.contextPath}/AdminPrincipal.jsp" class="btn-volver">← Panel admin</a>
                <div class="tabla-header-row">
                    <div class="top-contenedor" style="margin-bottom:0;">
                        <h1>Catálogo de productos</h1>
                    </div>
                    <a href="${pageContext.request.contextPath}/AdminCrearProducto.jsp" class="btn-deportivo-accion btn-naranja">
                        + Agregar producto
                    </a>
                </div>
                <table class="tabla-deportiva-global" style="margin-top:1rem;">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Precio</th>
                            <th>Stock</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty requestScope.listaProductos}">
                                <c:forEach var="p" items="${requestScope.listaProductos}">
                                    <tr>
                                        <td style="font-weight:800;">${p.nombre}</td>
                                        <td style="color:var(--naranja);font-weight:800;">$${p.precio}</td>
                                        <td>${p.stock}</td>
                                        <td>
                                            <div class="acciones-tabla">
                                                <a href="${pageContext.request.contextPath}/EditarProducto?id=${p.id}"
                                                   class="btn-deportivo-accion btn-sm btn-azul">Editar</a>
                                                <form action="${pageContext.request.contextPath}/borrarproducto" method="post" style="display:inline;"
                                                      onsubmit="return confirm('¿Eliminar este producto?')">
                                                    <input type="hidden" name="idProducto" value="${p.id}">
                                                    <button type="submit" class="btn-deportivo-accion btn-sm btn-rojo">Eliminar</button>
                                                </form>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr><td colspan="4" style="text-align:center;padding:2rem;color:var(--gris-texto);font-weight:700;">No hay productos registrados.</td></tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </main>
            <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
        </div>
    </body>
</html>