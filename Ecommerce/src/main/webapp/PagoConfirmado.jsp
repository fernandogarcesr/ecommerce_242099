<%-- 
    Document   : PagoConfirmado
    Created on : 24 mar 2026, 14:28:13
    Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Confirmación de Compra</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    </head>
    <body>
        <div class="grid-container">
            <%@include  file="/WEB-INF/fragmentos/aside.jspf"%>
            <%@include  file="/WEB-INF/fragmentos/header.jspf"%>

            <main class="content">

                <div class = "top-contenedor">
                    <a href="${pageContext.request.contextPath}/CargarCarrito" class="btn-regresar">
                        <img src="${pageContext.request.contextPath}/imgs/back.png" alt="Regresar">
                    </a>
                    <h1>Compra Realizada Con exito!</h1>
                </div>

                <div class="caja-form" style="max-width:750px;">
                    <h3 class="titulo-caja">Detalles del Pedido</h3>
                    <hr class="linea-caja">

                    <div class="grid-detalles">
                        <div class="detalle-etiqueta">N° de Pedido:</div>
                        <div class="detalle-valor">#${sessionScope.pedidoConfirmado.id}</div>

                        <div class="detalle-etiqueta">Fecha de Compra:</div>
                        <div class="detalle-valor">${sessionScope.pedidoConfirmado.fecha}</div>

                        <div class="detalle-etiqueta">Dirección de Entrega:</div>
                        <div class="detalle-valor">${sessionScope.usuarioActual.direccion}</div>

                        <div class="detalle-etiqueta">Destinatario:</div>
                        <div class="detalle-valor">${sessionScope.usuarioActual.nombre}</div>

                        <div class="detalle-etiqueta">Método de Pago:</div>
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

                <div class="caja-form" style="max-width:750px; margin-top:1rem;">
                    <h3 class="titulo-caja">Resúmen de Productos</h3>
                    <hr class="linea-caja">
                    <table class="tabla-deportiva-global">
                        <thead>
                            <tr>
                                <th>Artículo</th>
                                <th>Cantidad</th>
                                <th>Subtotal</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${sessionScope.pedidoConfirmado.detallesPedido}">
                                <tr>
                                    <td>${item.producto.nombre}</td>
                                    <td>${item.cantidad}</td>
                                    <td>$${item.cantidad * item.producto.precio}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div style="text-align:right; font-weight:800; font-size:1.1rem; margin-top:1rem; color:var(--naranja);">
                        TOTAL PAGADO: $${sessionScope.pedidoConfirmado.total}
                    </div>

                </div>

                <div style="margin-top:1.5rem;">
                    <a href="${pageContext.request.contextPath}/Index.jsp" class="btn-deportivo-accion btn-naranja">
                        Regresar al Inicio
                    </a>
                </div>

            </main>
            <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
        </div>

    </body>
</html>