<%-- 
    Document   : Pedidos
    Created on : 24 mar 2026, 14:28:30
    Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Historial de Pedidos - SportsZone</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div class="grid-container">
        <%@include file="/WEB-INF/fragmentos/header.jspf" %>
        <%@include file="/WEB-INF/fragmentos/aside.jspf" %>

        <main class="content">
            <div class="top-contenedor">
                <a href="Index.jsp" class="btn-regresar"><img src="./imgs/back.png" alt="Atrás"></a>
                <h1>Mis Compras</h1>
            </div>

            <table class="tabla-deportiva-global">
                <thead>
                    <tr>
                        <th>Código Pedido</th>
                        <th>Fecha de Compra</th>
                        <th>Total Pagado</th>
                        <th>Estatus de Envío</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td style="font-weight: 700; color: #ff5200;">#458921</td>
                        <td>06/05/2026</td>
                        <td style="font-weight: 700;">$2,499.00</td>
                        <td><span style="background: #fef3f2; color: #b42318; padding: 0.3rem 0.6rem; border-radius: 4px; font-size: 0.85rem; font-weight: 700;">PENDIENTE</span></td>
                    </tr>
                </tbody>
            </table>
        </main>

        <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
    </div>
</body>
</html>
