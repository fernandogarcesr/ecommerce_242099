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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Carrito de Compras - SportsZone</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    </head>
    <body>
        <div class="grid-container">
            <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
            <%@include file="/WEB-INF/fragmentos/header.jspf" %>

            <main class="content">
                <div class="top-contenedor">
                    <a href="${pageContext.request.contextPath}/cargarproducto" class="btn-regresar">← Catálogo</a>
                    <h1>Carrito de Compras</h1>
                </div>

                <c:if test="${not empty requestScope.error}">
                    <div class="alerta-error">${requestScope.error}</div>
                </c:if>

                <div style="display: grid; grid-template-columns: 1fr 320px; gap: 2rem; align-items: start;">

                    <table class="tabla-deportiva-global" style="margin-top: 0;">
                        <thead>
                            <tr>
                                <th>Artículo</th>
                                <th>Precio unit.</th>
                                <th>Cantidad</th>
                                <th>Subtotal</th>
                                <th style="text-align: center;">Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty requestScope.carritoActual.detallesCarrito}">
                                    <c:forEach var="item" items="${requestScope.carritoActual.detallesCarrito}">
                                        <tr>
                                            <td><strong>${item.producto.nombre}</strong></td>
                                            <td>$${item.producto.precio}</td>
                                            <td>${item.cantidadProductos}</td>
                                            <td style="font-weight:700;color:#ff5200;">
                                                $${item.cantidadProductos * item.producto.precio}
                                            </td>
                                            <td style="text-align:center;">
                                                <%-- Boton quitar pasa el id del producto y del carrito --%>
                                                <a href="${pageContext.request.contextPath}/quitarCarrito?id=${item.producto.id}&carritoId=${requestScope.carritoActual.id}"
                                                   class="btn-deportivo-accion"
                                                   style="background:#ef4444;padding:0.4rem 0.8rem;"
                                                   onclick="return confirm('¿Quitar este artículo?')">Quitar</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="5" style="text-align:center;padding:2rem;color:#64748b;font-weight:700;">
                                            Tu carrito está vacío.
                                            <a href="${pageContext.request.contextPath}/cargarproducto" style="color:#ff5200;">Ver catálogo</a>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose> 
                        </tbody>
                    </table>

                    <%-- Resumen y total --%>
                    <div style="background: #ffffff; padding: 2rem; border-radius: 8px; border: 1px solid #e2e8f0;">
                        <h3 style="font-size: 1.1rem; text-transform: uppercase; margin-bottom: 1rem; border-bottom: 1px solid #e2e8f0; padding-bottom: 0.5rem;">Resumen de Orden</h3>
                        <div style="display: flex; justify-content: space-between; margin-bottom: 1.5rem; font-size: 1.2rem; font-weight: 800;">
                            <span>Total:</span>
                            <span style="color: #ff5200;" id="totalCarrito">$${not empty requestScope.carritoActual.total ? requestScope.carritoActual.total : '0.00'}</span>
                        </div>
                        <%-- Checkout va a ProcesoPago.jsp que lee el carritoActual de sesion --%>
                        <a href="${pageContext.request.contextPath}/ProcesoPago.jsp" class="btn-deportivo-accion" style="width: 100%; display:block;text-align:center;">Proceder al Checkout</a>
                    </div>

                </div>
            </main>

            <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
        </div>
    </body>
</html>