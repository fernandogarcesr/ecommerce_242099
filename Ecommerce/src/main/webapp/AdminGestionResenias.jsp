<%-- AdminGestionResenias.jsp — moderar reseñas de usuarios --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de reseñas – SportsZone Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<div class="grid-container">
    <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
    <%@include file="/WEB-INF/fragmentos/header.jspf" %>
    <main class="content">
        <a href="${pageContext.request.contextPath}/AdminPrincipal.jsp" class="btn-volver">← Panel admin</a>
        <div class="top-contenedor"><h1>Gestión de reseñas</h1></div>
        <table class="tabla-deportiva-global">
            <thead>
                <tr>
                    <th>Usuario</th>
                    <th>Producto</th>
                    <th>Calificacion</th>
                    <th>Comentario</th>
                    <th>Fecha</th>
                    <th>Accion</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty requestScope.listaResenias}">
                        <c:forEach var="r" items="${requestScope.listaResenias}">
                            <tr>
                                <td style="font-weight:800;">${r.usuario.nombre}</td>
                                <td>${requestScope.nombresProductos[r.idProducto]}</td>
                                <td style="color:var(--naranja);">
                                    <c:forEach begin="1" end="${r.estrellas}" var="i">★</c:forEach>
                                </td>
                                <td style="max-width:240px;font-size:.85rem;color:var(--gris-texto);">${r.comentario}</td>
                                <td style="font-size:.82rem;">${r.fecha}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/eliminarResenia" method="post"
                                          onsubmit="return confirm('¿Eliminar esta reseña?')">
                                        <input type="hidden" name="reseniaId" value="${r.id}">
                                        <button type="submit" class="btn-deportivo-accion btn-sm btn-rojo">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr><td colspan="6" style="text-align:center;padding:2rem;color:var(--gris-texto);font-weight:700;">No hay reseñas publicadas.</td></tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </main>
    <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
</div>
</body>
</html>
