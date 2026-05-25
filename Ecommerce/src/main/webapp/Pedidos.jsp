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
            <%@include file="/WEB-INF/fragmentos/aside.jspf"%>
            <%@include file="/WEB-INF/fragmentos/header.jspf"%>

            <main class="content">
                <div class="top-contenedor">
                    <h1>Mis compras</h1>
                </div>

                <c:if test="${not empty requestScope.error}">
                    <div class="alerta-error">${requestScope.error}</div>
                </c:if>

                <table class="tabla-deportiva-global">
                    <thead>
                        <tr>
                            <th>Pedido</th>
                            <th>Fecha</th>
                            <th>Total pagado</th>
                            <th>Método de pago</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty requestScope.listaPedidos}">
                                <c:forEach var="p" items="${requestScope.listaPedidos}">
                                    <tr>
                                        <td style="font-weight:700;color:var(--naranja);">#${p.numeroPedido}</td>
                                        <td>${p.fechaHoraFormateada}</td>
                                        <td style="font-weight:700;">
                                            $<fmt:formatNumber value="${p.total}" maxFractionDigits="2" minFractionDigits="2"/>
                                        </td>
                                        <td>${p.metodoPago.tipo}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${p.estado == 'PENDIENTE'}">
                                                    <span class="badge badge-pendiente">Pendiente</span>
                                                </c:when>
                                                <c:when test="${p.estado == 'ENVIADO'}">
                                                    <span class="badge badge-enviado">Enviado</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge badge-entregado">Entregado</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="5" style="text-align:center;padding:2rem;
                                    color:var(--gris-texto);font-weight:700;">
                                        Aún no has realizado ninguna compra.
                                        <a href="${pageContext.request.contextPath}/cargarproducto"
                                           style="color:var(--naranja);">Ver catálogo</a>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </main>
            <script src="${pageContext.request.contextPath}/js/nav.js"></script>
            <script src="${pageContext.request.contextPath}/js/pedidos.js"></script>
            <%@include file="/WEB-INF/fragmentos/footer.jspf"%>
        </div>
    </body>
</html>
