<%-- AdminGestionPedidos.jsp — actualizar estado de cada pedido --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion de pedidos – SportsZone Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<div class="grid-container">
    <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
    <%@include file="/WEB-INF/fragmentos/header.jspf" %>
    <main class="content">
        <a href="${pageContext.request.contextPath}/AdminPrincipal.jsp" class="btn-volver">← Panel admin</a>
        <div class="top-contenedor"><h1>Gestión de pedidos</h1></div>
        <table class="tabla-deportiva-global">
            <thead>
                <tr>
                    <th># Pedido</th>
                    <th>Cliente (correo)</th>
                    <th>Total</th>
                    <th>Estado</th>
                    <th>Cambiar estado</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty requestScope.listaPedidos}">
                        <c:forEach var="p" items="${requestScope.listaPedidos}">
                            <tr>
                                <td style="font-weight:800;">#${p.id}</td>
                                <td style="color:var(--gris-texto);">${p.usuario.correo}</td>
                                <td style="color:var(--naranja);font-weight:800;">$${p.total}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${p.estado == 'PENDIENTE'}"><span class="badge badge-pendiente">Pendiente</span></c:when>
                                        <c:when test="${p.estado == 'ENVIADO'}"><span class="badge badge-enviado">Enviado</span></c:when>
                                        <c:otherwise><span class="badge badge-entregado">Entregado</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <div class="acciones-tabla">
                                        <form action="${pageContext.request.contextPath}/actualizarEstadoPedido" method="post">
                                            <input type="hidden" name="idPedido" value="${p.id}">
                                            <select name="estado-pedido">
                                                <option value="PENDIENTE">Pendiente</option>
                                                <option value="ENVIADO">Enviado</option>
                                                <option value="ENTREGADO">Entregado</option>
                                            </select>
                                            <button type="submit" class="btn-deportivo-accion btn-sm btn-naranja">Actualizar</button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr><td colspan="5" style="text-align:center;padding:2rem;color:var(--gris-texto);font-weight:700;">No hay pedidos registrados.</td></tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </main>
    <script src="${pageContext.request.contextPath}/js/nav.js"></script>
    <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
</div>
</body>
</html>