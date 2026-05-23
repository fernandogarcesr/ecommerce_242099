<%-- 
    Document   : CrearReseña
    Created on : 24 mar 2026, 14:27:19
    Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dejar Reseña - SportsZone</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div class="grid-container">
        <%@include file="/WEB-INF/fragmentos/header.jspf" %>
        <%@include file="/WEB-INF/fragmentos/aside.jspf" %>

        <main class="content">
            <div class="top-contenedor">
                <h1>Calificar Artículo</h1>
            </div>

            <div class="detalle-producto-container" style="grid-template-columns: 300px 1fr; padding: 2rem;">
                <div class="foto-producto-box">
                    <img src="${pageContext.request.contextPath}/imgs/prod-shoes.jpg" alt="Tenis de Deporte" style="max-height: 250px;">
                    <h3 style="margin-top: 1rem; text-align: center;">Tenis Running Volt Pro</h3>
                </div>

                <div class="info-compra-box">
                    <form action="guardar_reseña" method="POST">
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
                            <label for="txt_comentario">Comparte tu experiencia con el equipo</label>
                            <textarea id="txt_comentario" name="comentario" class="input-campo" rows="6" placeholder="Escribe aquí tu opinión sobre el material, talla o durabilidad..." style="resize: none;" required></textarea>
                        </div>

                        <div style="display: flex; gap: 10px; margin-top: 1.5rem;">
                            <button type="submit" class="btn-deportivo-accion">Publicar Reseña</button>
                            <a href="Catalogo.jsp" class="btn-deportivo-accion btn-secundario">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </main>

        <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
    </div>
</body>
</html>