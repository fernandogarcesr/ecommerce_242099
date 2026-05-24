<%-- 
    Document   : Carrito
    Created on : 24 mar 2026, 14:21:25
    Author     : Fernando Garces
--%>
<%--
    Descripcion: Vista del carrito de compras del usuario.
    Se accede via /CargarCarrito que inyecta el carritoActual en request y sesion.
    El boton Quitar llama a /quitarCarrito?id=X&carritoId=Y.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Carrito de Compras - SportsZone</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    </head>
    <body data-ctx="${pageContext.request.contextPath}">
        <div class="grid-container">
            <%@include file="/WEB-INF/fragmentos/aside.jspf"%>
            <%@include file="/WEB-INF/fragmentos/header.jspf"%>

            <main class="content">
                <div class="top-contenedor">
                    <a href="${pageContext.request.contextPath}/cargarproducto" class="btn-regresar">← Catálogo</a>
                    <h1>Carrito de compras</h1>
                </div>

                <c:if test="${not empty requestScope.error}">
                    <div class="alerta-error">${requestScope.error}</div>
                </c:if>

                <div style="display:grid; grid-template-columns:1fr 300px; gap:1.8rem; align-items:flex-start;">

                    <table class="tabla-deportiva-global">
                        <thead>
                            <tr>
                                <th>Artículo</th>
                                <th style="text-align:center;">Precio unit.</th>
                                <th style="text-align:center;">Cant.</th>
                                <th style="text-align:right;">Subtotal</th>
                                <th style="text-align:center;">Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty requestScope.carritoActual.detallesCarrito}">
                                    <c:forEach var="item" items="${requestScope.carritoActual.detallesCarrito}">
                                        <tr>
                                            <td><strong>${item.producto.nombre}</strong></td>
                                            <td class="precio-unitario" data-precio="${item.producto.precio}">$${item.producto.precio}</td>
                                            <td style="text-align:center;">${item.cantidadProductos}</td>
                                            <td class="subtotal-celda" style="color:var(--naranja); font-weight:800;">$${item.cantidadProductos * item.producto.precio}</td>
                                            <td style="text-align:center;">
                                                <a href="${pageContext.request.contextPath}/quitarCarrito?id=${item.producto.id}&carritoId=${requestScope.carritoActual.id}"
                                                   class="btn-deportivo-accion btn-sm btn-rojo"
                                                   onclick="return confirm('¿Quitar este artículo?')">
                                                    Quitar
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="5" style="text-align:center;padding:2rem;
                                            color:var(--gris-texto);font-weight:700;">
                                            Tu carrito está vacío.
                                            <a href="${pageContext.request.contextPath}/cargarproducto"
                                               style="color:var(--naranja);">Ver catálogo</a>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>

                    <div class="resumen-orden-box">
                        <h3>Resumen de orden</h3>
                        <div class="total-fila">
                            <span>Total:</span>
                            <span id="total-carrito" style="color:var(--naranja);">$${requestScope.carritoActual.total}</span>
                        </div>
                        <a href="${pageContext.request.contextPath}/ProcesoPago.jsp"
                           class="btn-deportivo-accion btn-naranja"
                           style="width:100%;display:block;text-align:center;">
                            Proceder al checkout
                        </a>
                    </div>

                </div>
            </main>
            <script src="${pageContext.request.contextPath}/js/app.js"></script>
            <%@include file="/WEB-INF/fragmentos/footer.jspf"%>
        </div>
    </body>
</body>
</html>