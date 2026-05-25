<%-- 
    Document   : CrearReseña
    Created on : 24 mar 2026, 14:27:19
    Author     : Fernando Garces
--%>
<%--
    Autor: Fernando Garces Rodriguez - 242099
    Descripcion: Formulario para publicar una resena de un producto.
    Recibe el id del producto por parametro URL.
    El form envia a /guardarResenia con POST.
    Cancelar va al servlet /cargarproducto para que el catalogo
    vuelva a mostrar los productos con normalidad.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dejar Reseña - SportsZone</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div class="grid-container">
        <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
        <%@include file="/WEB-INF/fragmentos/header.jspf" %>
        
        <main class="content">
            <div class="top-contenedor">
                <h1>Calificar Artículo</h1>
            </div>
            
            <c:if test="${not empty requestScope.error}">
                <div class="alerta-error">${requestScope.error}</div>
            </c:if>

            <div class="detalle-producto-container" style="grid-template-columns: 300px 1fr; padding: 2rem;">
                <div class="foto-producto-box">
                    <c:choose>
                        <c:when test="${not empty requestScope.producto}">
                            <img src="${pageContext.request.contextPath}/${requestScope.producto.rutaImagen}"
                                 alt="${requestScope.producto.nombre}" style="max-height:250px;">
                            <h3 style="margin-top:1rem;text-align:center;">${requestScope.producto.nombre}</h3>
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/imgs/prod-shoes.jpg"
                                 alt="Producto" style="max-height:250px;">
                            <h3 style="margin-top:1rem;text-align:center;">Producto</h3>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="info-compra-box">
                    <%-- El form envia al servlet guardarResenia --%>
                    <form action="${pageContext.request.contextPath}/guardarResenia" method="POST">
                        <%-- ID del producto oculto para que el servlet sepa a cual producto pertenece --%>
                        <input type="hidden" name="idProducto" value="${param.id}">
                        
                        <div class="input-bloque">
                            <label for="cmb_calificacion">Calificación del Rendimiento</label>
                            <select id="cmb_calificacion" name="calificacion" class="input-campo" style="width: 120px;">
                                <option value="5">⭐⭐⭐⭐⭐ 5</option>
                                <option value="4">⭐⭐⭐⭐☆ 4</option>
                                <option value="3">⭐⭐⭐☆☆ 3</option>
                                <option value="2">⭐⭐☆☆☆ 2</option>
                                <option value="1">⭐☆☆☆☆ 1</option>
                            </select>
                        </div>

                        <div class="input-bloque">
                            <label for="txt_comentario">Comparte tu experiencia</label>
                            <textarea id="txt_comentario" name="comentario" class="input-campo" rows="6" placeholder="Escribe aquí tu opinión sobre el material, talla o durabilidad..." style="resize: none;" required></textarea>
                        </div>

                        <div style="display: flex; gap: 10px; margin-top: 1.5rem;">
                            <button type="submit" class="btn-deportivo-accion">Publicar Reseña</button>
                            <a href="${pageContext.request.contextPath}/cargarproducto" class="btn-deportivo-accion btn-secundario">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </main>
        <script src="${pageContext.request.contextPath}/js/nav.js"></script>
        <script src="${pageContext.request.contextPath}/js/crearResenia.js"></script>
        <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
    </div>
</body>
</html>