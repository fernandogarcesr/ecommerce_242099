<%-- 
    Document   : PagoConfirmado
    Created on : 24 mar 2026, 14:28:13
    Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pedido Confirnado - SportsZone</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    </head>
    <body> 
        <div class="grid-container">
            <%@include file="/WEB-INF/fragmentos/aside.jspf"%>
            <%@include file="/WEB-INF/fragmentos/header.jspf"%>

            <main class="content">
                <div class="top-contenedor">
                    <h1>¡Compra realizada con éxito!</h1>
                </div>

                <div class="confirmacion-box">
                    <h3>Detalles del pedido</h3>
                    <div class="grid-detalles">
                        <div class="detalle-etiqueta">N° de pedido</div>
                        <div class="detalle-valor">#${sessionScope.pedidoConfirmado.numeroPedido}</div>

                        <div class="detalle-etiqueta">Fecha</div>
                        <div class="detalle-valor">${sessionScope.pedidoConfirmado.fechaHoraFormateada}</div>

                        <div class="detalle-etiqueta">Destinatario</div>
                        <div class="detalle-valor">${sessionScope.usuarioActual.nombre}</div>

                        <div class="detalle-etiqueta">Dirección de entrega</div>
                        <div class="detalle-valor">${sessionScope.usuarioActual.direccion}</div>

                        <div class="detalle-etiqueta">Método de pago</div>
                        <div class="detalle-valor">
                            <c:choose>
                                <c:when test="${not empty sessionScope.pedidoConfirmado.metodoPago}">
                                    ${sessionScope.pedidoConfirmado.metodoPago.tipo}
                                </c:when>
                                <c:otherwise>No especificado</c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>

                <div class="confirmacion-box">
                    <h3>Resumen de productos</h3>
                    <table class="tabla-deportiva-global">
                        <thead>
                            <tr>
                                <th>Artículo</th>
                                <th style="text-align:center;">Cantidad</th>
                                <th style="text-align:right;">Subtotal</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${sessionScope.pedidoConfirmado.detallesPedido}">
                                <tr>
                                    <td>${item.producto.nombre}</td>
                                    <td style="text-align:center;">${item.cantidad}</td>
                                    <td style="text-align:right;color:var(--naranja);font-weight:800;">
                                        $${item.cantidad * item.producto.precio}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div style="text-align:right;font-weight:800;font-size:1.15rem;margin-top:1rem;color:var(--naranja);">
                        TOTAL PAGADO: $<fmt:formatNumber value="${sessionScope.pedidoConfirmado.total}"
                                          maxFractionDigits="2" minFractionDigits="2"/>
                    </div>
                </div>

                <div style="margin-top:1.4rem;">
                    <a href="${pageContext.request.contextPath}/Index.jsp"
                       class="btn-deportivo-accion btn-naranja">
                        Regresar al inicio
                    </a>
                </div>
            </main>
            <script src="${pageContext.request.contextPath}/js/nav.js"></script>
            <script src="${pageContext.request.contextPath}/js/pagoConfirmado.js"></script>
            <%@include file="/WEB-INF/fragmentos/footer.jspf"%>
        </div>
    </body>
</html>