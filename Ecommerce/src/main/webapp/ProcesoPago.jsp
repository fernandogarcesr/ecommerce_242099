<%-- 
    Document   : ProcesoPago
    Created on : 24 mar 2026, 14:29:17
    Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>Proceso de Pago - SportsZone</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    </head>
    <body>
        <div class="grid-container">
            <%@include  file="/WEB-INF/fragmentos/aside.jspf"%>
            <%@include file="/WEB-INF/fragmentos/header.jspf"%>

            <main class="content">
                <div class="top-contenedor">
                    <h1>Confirmación de Compra</h1>
                </div>

                <div class="envio-info-container">
                    <div class="datos-envio">
                        <p>Enviar a <strong>${sessionScope.usuarioActual.nombre}</strong></p>
                        <a href="#" class="enlace-direccion">${sessionScope.usuarioActual.direccion}</a>
                    </div>
                    <div class="fecha-llegada">
                        <p>Entregada estimada: <strong>Proximos 3-5 dias habiles</strong></p>
                    </div>
                </div>

                <div class="grid-carrito" style="margin-top: 1.5rem;">

                    <div class="pago-productos-box" style="background:#fff; padding:1.5rem; border-radius:8px; border:1px solid var(--gris-borde);">
                        <h3>Resumen de Productos</h3>
                        <br>
                        <table class="tabla-deportiva-global">
                            <thead>
                                <tr>
                                    <th>Artículo</th>
                                    <th>Precio</th>
                                    <th>Cantidad</th>
                                    <th>Subtotal</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${sessionScope.carritoActual.detallesCarrito}">
                                    <tr>
                                        <td><strong>${item.producto.nombre}</strong></td>
                                        <td>$${item.producto.precio}</td>
                                        <td>${item.cantidadProductos}</td>
                                        <td style="color:var(--naranja); font-weight:800;">$${item.cantidadProductos * item.producto.precio}</td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty sessionScope.carritoActual.detallesCarrito}">
                                    <tr>
                                        <td colspan="4" style="text-align:center;">Tu carrito está vacío.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>

                    <div class="pago-sidebar">

                        <form action="${pageContext.request.contextPath}/finalizarPedido" method="POST">
                            <div class="metodos-pago-box">
                                <h3>Método de Pago</h3>
                                <div style="display:flex; flex-direction:column; gap:10px; margin-top:0.8rem;">
                                    <label style="display:flex; align-items:center; gap:10px; cursor:pointer; font-weight:600; color:var(--negro);">
                                        <input type="radio" name="tipoPago" value="TARJETA" checked>
                                        <img src="${pageContext.request.contextPath}/imgs/VISA-Logo.png" alt="Visa" style="height:24px; object-fit:contain;">
                                        <span style="color:var(--negro);">Tarjeta de crédito/débito</span>
                                    </label>
                                    <label style="display:flex; align-items:center; gap:10px; cursor:pointer; font-weight:600; color:var(--negro);">
                                        <input type="radio" name="tipoPago" value="TRANSFERENCIA">
                                        <span style="font-size:1.1rem;">🏦</span>
                                        <span style="color:var(--negro);">Transferencia bancaria</span>
                                    </label>
                                    <label style="display:flex; align-items:center; gap:10px; cursor:pointer; font-weight:600; color:var(--negro);">
                                        <input type="radio" name="tipoPago" value="PAYPAL">
                                        <img src="https://www.paypalobjects.com/webstatic/mktg/Logo/pp-logo-100px.png"
                                             alt="PayPal" style="height:24px; object-fit:contain;">
                                        <span style="color:var(--negro);">PayPal</span>
                                    </label>

                                </div>
                            </div>
                            <div class="total-pago-box" style="margin-top:1rem;">
                                <h3>Total a Pagar</h3>
                                <div class="total-input" style="font-family:var(--fuente-titulo); font-size:2rem; color:var(--naranja); font-weight:800; margin:0.5rem 0;">
                                    $${sessionScope.carritoActual.total != null ? sessionScope.carritoActual.total : '0.00'}
                                </div>
                                <button type="submit" class="btn-deportivo-accion btn-naranja" style="width:100%;">Confirmar Pago</button>
                            </div>
                        </form>

                    </div>


                </div>
            </main>
            <%@include file="/WEB-INF/fragmentos/footer.jspf"%> 
        </div>

    </body>
</html>
