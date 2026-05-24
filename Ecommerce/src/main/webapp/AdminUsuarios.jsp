<%-- 
   Document   : AdminUsuarios
   Created on : 24 mar 2026, 14:26:32
   Author     : Fernando Garces
--%>

<%-- AdminUsuarios.jsp — gestion de cuentas de usuario --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Usuarios – SportsZone Admin</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    </head>
    <body>
        <div class="grid-container">
            <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
            <%@include file="/WEB-INF/fragmentos/header.jspf" %>
            <main class="content">
                <a href="${pageContext.request.contextPath}/AdminPrincipal.jsp" class="btn-volver">← Panel admin</a>
                <div class="top-contenedor">
                    <h1>Gestión de usuarios</h1>
                </div>
                <c:if test="${not empty requestScope.mensajeError}">
                    <div style="background:#FFEBEE;border:1px solid #FFCDD2;color:#B71C1C;
                         padding:12px 16px;border-radius:6px;margin-bottom:1rem;font-size:0.9rem;">
                        ${requestScope.mensajeError}
                    </div>
                </c:if>
                <table class="tabla-deportiva-global">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Correo</th>
                            <th>Fecha de registro</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty requestScope.usuarios}">
                                <c:forEach var="u" items="${requestScope.usuarios}">
                                    <tr>
                                        <td style="font-weight:800;">${u.nombre}</td>
                                        <td style="color:var(--gris-texto);">${u.correo}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${not empty u.fechaRegistro}">
                                        <fmt:formatDate value="${u.fechaRegistro}" pattern="dd/MM/yyyy"/>
                                    </c:when>
                                    <c:otherwise>—</c:otherwise>
                                </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${u.esActivo != null and u.esActivo}">
                                            <span class="badge badge-activo">Activo</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-inactivo">Inactivo</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <div class="acciones-tabla">
                                        <form action="${pageContext.request.contextPath}/administrar-usuarios" method="post" style="display:inline;">
                                            <input type="hidden" name="accion" value="${u.esActivo != null and u.esActivo ? 'desactivar' : 'activar'}">
                                            <input type="hidden" name="idUsuario" value="${u.id}">
                                            <button type="submit" class="btn-deportivo-accion btn-sm ${u.esActivo != null and u.esActivo? 'btn-rojo' : 'btn-verde'}">
                                                ${u.esActivo != null and u.esActivo? 'Desactivar' : 'Activar'}
                                            </button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/administrar-usuarios" method="post" style="display:inline;"
                                              onsubmit="return confirm('¿Eliminar permanentemente este usuario?')">
                                            <input type="hidden" name="accion" value="eliminar">
                                            <input type="hidden" name="idUsuario" value="${u.id}">  
                                            <button type="submit" class="btn-deportivo-accion btn-sm btn-rojo" <c:if test="${u.esActivo != null and u.esActivo}">
                                                    disabled title="Desactiva el usuario primero"
                                                    style="opacity:0.4; cursor:not-allowed;"
                                                </c:if>>
                                                Eliminar
                                            </button>
                                        </form>
                                    </div>
                                </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr><td colspan="5" style="text-align:center;padding:2rem;color:var(--gris-texto);font-weight:700;">No hay usuarios registrados.</td></tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </main>
            <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
        </div>
    </body>
</html>