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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Proceso de Pago - SportsZone</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    </head>
    <body>
        <div class="grid-container">
            <%@include  file="/WEB-INF/fragmentos/aside.jspf"%>
            <%@include file="/WEB-INF/fragmentos/header.jspf"%>

            <main class="content">
                <div class="top-contenedor">
                    <a href="${pageContext.request.contextPath}/CargarCarrito" class="btn-regresar">← Carrito</a>
                    <h1>Confirmación de Compra</h1>
                </div>

                <c:if test="${not empty requestScope.error}">
                    <div class="alerta-error">${requestScope.error}</div>
                </c:if>

                <div class="envio-info-container">
                    <div class="datos-envio">
                        <p>Enviar a <strong>${sessionScope.usuarioActual.nombre}</strong></p>
                        <a href="${pageContext.request.contextPath}/PerfilUsuarios.jsp">${sessionScope.usuarioActual.direccion}</a>
                    </div>
                    <div class="fecha-llegada">
                        <p>Entregada estimada: <strong>Proximos 3-5 dias habiles</strong></p>
                    </div>
                </div>

                <%-- Todo el checkout en un solo form --%>
                <form id="form-pago" action="${pageContext.request.contextPath}/finalizarPedido" method="POST">
                    <div style="display:grid; grid-template-columns:1fr 300px; gap:1.8rem; align-items:flex-start;">

                        <div class="pago-productos-box">
                            <h3>Artículos en tu pedido</h3>
                            <table class="tabla-deportiva-global" style="margin-top:.8rem;">
                                <thead>
                                    <tr>
                                        <th>Artículo</th>
                                        <th style="text-align:center;">Cant.</th>
                                        <th style="text-align:right;">Subtotal</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${sessionScope.carritoActual.detallesCarrito}">
                                        <tr>
                                            <td><strong>${item.producto.nombre}</strong></td>
                                            <td style="text-align:center;">${item.cantidadProductos}</td>
                                            <td style="text-align:right;color:var(--naranja);font-weight:800;">
                                                $${item.cantidadProductos * item.producto.precio}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty sessionScope.carritoActual.detallesCarrito}">
                                        <tr>
                                            <td colspan="3" style="text-align:center;color:var(--gris-texto);">
                                                Tu carrito está vacío.
                                                <a href="${pageContext.request.contextPath}/cargarproducto" style="color:var(--naranja);">Ver catálogo</a>
                                            </td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>

                        <div class="pago-sidebar">
                            <div class="metodos-pago-box">
                                <h3>Método de pago</h3>
                                <label class="metodo-pago-opcion">
                                    <input type="radio" name="metodoPago" value="TARJETA" checked>
                                    <img src="${pageContext.request.contextPath}/imgs/VISA-Logo.png" alt="Tarjeta">
                                    Tarjeta (Visa / MC)
                                </label>
                                <label class="metodo-pago-opcion">
                                    <input type="radio" name="metodoPago" value="TRANSFERENCIA">
                                    <span style="font-size:1.2rem;">🏦</span>
                                    Transferencia bancaria
                                </label>
                                <label class="metodo-pago-opcion">
                                    <input type="radio" name="metodoPago" value="PAYPAL">
                                    <img src="https://www.paypalobjects.com/webstatic/mktg/Logo/pp-logo-100px.png"
                                         alt="PayPal" style="height:20px;">
                                    PayPal
                                </label>
                                <label class="metodo-pago-opcion">
                                    <input type="radio" name="metodoPago" value="CONTRAENTREGA">
                                    <span style="font-size:1.2rem;">📦</span>
                                    Contra entrega
                                </label>

                                <%-- Secciones dinamicas por metodo de pago --%>
                                <div id="sec-tarjeta" style="display:none; margin-top:1rem;">
                                    <h4 style="margin-bottom:.5rem;">Datos de tarjeta</h4>
                                    <input type="text" id="titular" placeholder="Nombre del titular"
                                           style="width:100%;padding:.5rem;margin-bottom:.5rem;border:1px solid #ccc;border-radius:6px;">
                                    <input type="text" id="num-tarjeta" placeholder="1234 5678 9012 3456" maxlength="19"
                                           style="width:100%;padding:.5rem;margin-bottom:.5rem;border:1px solid #ccc;border-radius:6px;">
                                    <div style="display:flex;gap:.5rem;">
                                        <input type="text" id="expiracion" placeholder="MM/YY" maxlength="5"
                                               style="flex:1;padding:.5rem;border:1px solid #ccc;border-radius:6px;">
                                        <input type="text" id="cvv" placeholder="CVV" maxlength="4"
                                               style="flex:1;padding:.5rem;border:1px solid #ccc;border-radius:6px;">
                                    </div>
                                </div>
                                <div id="sec-transferencia" style="display:none; margin-top:1rem;padding:.8rem;background:#f0f4ff;border-radius:6px;">
                                    <p><strong>Banco:</strong> BBVA</p>
                                    <p><strong>CLABE:</strong> 012 180 0123456789 01</p>
                                    <p style="font-size:.8rem;color:#666;">Envía tu comprobante por WhatsApp al 6441234567</p>
                                </div>
                                <div id="sec-contra-entrega" style="display:none; margin-top:1rem;padding:.8rem;background:#f0fff4;border-radius:6px;">
                                    <p>💵 Pagarás en efectivo al recibir tu pedido.</p>
                                </div>
                                <div id="errores-pago" style="display:none; margin-top:.8rem; color:#c0392b; font-size:.9rem;"></div>
                            </div>

                            <div class="total-pago-box">
                                <h3>Total a pagar</h3>
                                <span class="monto-total-pago">
                                    $${sessionScope.carritoActual.total != null ? sessionScope.carritoActual.total : '0.00'}
                                </span>
                                <button id="btn-confirmar-pago" type="submit" class="btn-deportivo-accion btn-naranja" style="width:100%;">
                                    Confirmar pago
                                </button>
                            </div>
                        </div>

                    </div>
                </form>
            </main>
            <script src="${pageContext.request.contextPath}/js/nav.js"></script>                    
            <script src="${pageContext.request.contextPath}/js/pago.js"></script>
            <%@include file="/WEB-INF/fragmentos/footer.jspf"%>
        </div>
    </body>
</html>