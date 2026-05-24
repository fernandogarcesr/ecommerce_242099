<%-- 
    Document   : Pedidos
    Created on : 24 mar 2026, 14:28:30
    Author     : Fernando Garces
--%>
<%--
    Autor: Fernando Garces Rodriguez - 242099
    Descripcion: Historial de pedidos del cliente logueado.
    Se accede via /cargarpedidos que inyecta listaPedidos.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mis Pedidos - SportsZone</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    </head>
    <body>
        <div class="grid-container">
            <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
            <%@include file="/WEB-INF/fragmentos/header.jspf" %>

            <main class="content">
                <div class="top-contenedor">
                    <h1>Mis Compras</h1>
                </div>

                <table class="tabla-deportiva-global">
                    <thead>
                        <tr>
                            <th>Código Pedido</th>
                            <th>Fecha de Compra</th>
                            <th>Total Pagado</th>
                            <th>Método de Pago</th>
                            <th>Estatus</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty requestScope.listaPedidos}">
                                <c:forEach var="p" items="${requestScope.listaPedidos}">
                                    <tr>
                                        <td style="font-weight:700;color:#ff5200;">#${p.numeroPedido}</td>
                                        <td>${p.fechaHoraFormateada}</td>
                                        <td style="font-weight:700;">$<fmt:formatNumber value="${p.total}" maxFractionDigits="2" minFractionDigits="2"/></td>
                                        <td style="font-weight:600;">${p.metodoPago.tipo}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${p.estado == 'PENDIENTE'}">
                                                    <span style="background:#fef3f2;color:#b42318;padding:0.3rem 0.6rem;border-radius:4px;font-size:0.85rem;font-weight:700;">PENDIENTE</span>
                                                </c:when>
                                                <c:when test="${p.estado == 'ENVIADO'}">
                                                    <span style="background:#eff6ff;color:#1d4ed8;padding:0.3rem 0.6rem;border-radius:4px;font-size:0.85rem;font-weight:700;">ENVIADO</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span style="background:#f0fdf4;color:#166534;padding:0.3rem 0.6rem;border-radius:4px;font-size:0.85rem;font-weight:700;">ENTREGADO</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="5" style="text-align:center;padding:2rem;color:#64748b;font-weight:700;">
                                        Aún no has realizado ninguna compra.
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </main>

            <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
        </div>
    </body>
</html>
