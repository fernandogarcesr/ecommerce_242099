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
                                <c:forEach var="item" items="${sessionScope.carritoActual.items}">
                                    <tr>
                                        <td><strong>${item.nombreProducto}</strong></td>
                                        <td>$${item.precioUnitario}</td>
                                        <td>${item.cantidad}</td>
                                        <td style="color:var(--naranja); font-weight:800;">$${item.subtotal}</td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty sessionScope.carritoActual.items}">
                                    <tr>
                                        <td colspan="4" style="text-align:center;">Tu carrito está vacío.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>

                    <div class="pago-sidebar">

                        <div class="metodos-pago-box">
                            <h3>Método de Pago</h3>
                            <div class="logos-grid">
                                <div class="logo-tarjeta"><img src=""${pageContext.request.contextPath}/imgs/VISA-Logo.png" alt="Visa"></div>
                                <div class="logo-tarjeta" style="display:flex; align-items:center; justify-content:center; font-weight:700; font-size:0.9rem;">
                                    <span style="color:#EB001B;">Master</span><span style="color:#F79E1B;">card</span>
                                </div>
                                <div class="logo-tarjeta" style="display:flex; align-items:center; justify-content:center; font-weight:700; font-size:0.9rem; color:#003087;">PayPal    </div>
                            </div>
                        </div>

                        <div class="total-pago-box" style="margin-top:1rem;">
                            <h3>Total a Pagar</h3>
                            <div class="total-input" style="font-family:var(--fuente-titulo); font-size:2rem; color:var(--naranja); font-weight:800; margin:0.5rem 0;">
                                $${sessionScope.carritoActual.total != null ? sessionScope.carritoActual.total : '0.00'}
                            </div>
                            <form action="${pageContext.request.contextPath}/finalizarPedido" method="POST">
                                <button type="submit" class="btn-deportivo-accion btn-naranja" style="width:100%;">Confirmar Pago</button>
                            </form>
                        </div>

                    </div>
                </div>
            </main>

            <%@include file="/WEB-INF/fragmentos/footer.jspf"%>                    
        </div>

    </body>
</html>
