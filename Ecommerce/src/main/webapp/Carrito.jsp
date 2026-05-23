<%-- 
    Document   : Carrito
    Created on : 24 mar 2026, 14:21:25
    Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <%@include file="/WEB-INF/fragmentos/header.jspf" %>
        <%@include file="/WEB-INF/fragmentos/aside.jspf" %>

        <main class="content">
            <div class="top-contenedor">
                <a href="Catalogo.jsp" class="btn-regresar"><img src="./imgs/back.png" alt="Atrás"></a>
                <h1>Carrito de Compras</h1>
            </div>

            <div style="display: grid; grid-template-columns: 1fr 320px; gap: 2rem; align-items: start;">
                
                <table class="tabla-deportiva-global" style="margin-top: 0;">
                    <thead>
                        <tr>
                            <th>Artículo</th>
                            <th>Cantidad</th>
                            <th>Subtotal</th>
                            <th style="text-align: center;">Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><strong>Tenis Running Volt Pro</strong></td>
                            <td>
                                <select class="input-campo" style="padding: 0.3rem; width: 70px;">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                </select>
                            </td>
                            <td style="font-weight: 700;">$2,499.00</td>
                            <td style="text-align: center;">
                                <button class="btn-deportivo-accion" style="background: #ef4444; padding: 0.4rem 0.8rem;">Quitar</button>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <div style="background: #ffffff; padding: 2rem; border-radius: 8px; border: 1px solid #e2e8f0;">
                    <h3 style="font-size: 1.1rem; text-transform: uppercase; margin-bottom: 1rem; border-bottom: 1px solid #e2e8f0; padding-bottom: 0.5rem;">Resumen de Orden</h3>
                    <div style="display: flex; justify-content: space-between; margin-bottom: 1.5rem; font-size: 1.2rem; font-weight: 800;">
                        <span>Total:</span>
                        <span style="color: #ff5200;">$2,499.00</span>
                    </div>
                    <a href="ProcesoPago.jsp" class="btn-deportivo-accion" style="width: 100%;">Proceder al Checkout</a>
                </div>

            </div>
        </main>

        <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
    </div>
</body>
</html>