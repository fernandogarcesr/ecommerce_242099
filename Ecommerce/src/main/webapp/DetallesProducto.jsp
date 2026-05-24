<%-- 
    Document   : DetallesProducto
    Created on : 24 mar 2026, 14:27:54
    Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalles del Artículo - SportsZone</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body data-ctx="${pageContext.request.contextPath}">
    <div class="grid-container">
        <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
        <%@include file="/WEB-INF/fragmentos/header.jspf" %>

        <main class="content">
            <div class="top-contenedor">
                <a href="${pageContext.request.contextPath}/cargarproducto" class="btn-regresar">
                    <img src="${pageContext.request.contextPath}/imgs/back.png" alt="Atrás">
                </a>
                <h1>Ficha Técnica del Producto</h1>
            </div>

            <div class="detalle-producto-container">
                <div class="foto-producto-box">
                    <img src="${pageContext.request.contextPath}/${producto.rutaImagen}" alt="${producto.nombre}" onerror="this.src='${pageContext.request.contextPath}/imgs/prod-shoes.jpg'">
                    <div style="font-size: 1.1rem; color: #e2b93b; font-weight: 700; margin-top: 0.5rem;">⭐⭐⭐⭐☆ 4.0</div>
                </div>

                <div class="info-compra-box">
                    <h1 style="font-size: 1.8rem; font-weight: 900; text-transform: uppercase; color: #0f172a;">${producto.nombre}</h1>
                    <p style="color: #64748b; font-size: 0.9rem; margin-top: 0.3rem;">Código de Artículo: #${producto.id}</p>
                    
                    <div class="precio-tag">$${producto.precio}</div>
                    
                    <p style="font-size: 0.95rem; color: #334155; line-height: 1.6; margin-bottom: 1.5rem;">
                       ${producto.descripcion}
                    </p>

                    <div style="background: #ffffff; padding: 1.2rem; border-radius: 8px; border: 1px solid #e2e8f0; margin-top: auto;">
                        <p style="font-weight: 700; margin-bottom: 0.5rem; font-size: 0.95rem;">Disponibilidad: <span style="color: ${producto.stock > 0 ? '#22c55e' : '#ef4444'};">${producto.stock > 0 ? 'En Stock' : 'Agotado'}</span></p>
                        <p style="font-size: 0.85rem; color: #64748b; margin-bottom: 1rem;">Unidades en almacén: ${producto.stock}</p>
                        <p style="font-size: 0.85rem; color: #475569; margin-bottom: 1.2rem; border-top: 1px solid #e2e8f0; padding-top: 0.5rem;"><strong>Especificaciones:</strong> Garantía original de SportsZone, materiales de alto rendimiento aptos para entrenamiento deportivo intensivo. </p>
                        
                        <div style="display: flex; gap: 10px;">
                            <a href="${pageContext.request.contextPath}/agregarCarrito?id=${producto.id}" class="btn-deportivo-accion" style="flex: 1;">Agregar al Carrito</a>
                        </div>
                    </div>
                </div>
            </div>

            <%-- Renias de compradores, de forma dinamica con un for para recorerlas--%>
            <div style="margin-top: 2.5rem; background: #ffffff; padding: 2rem; border-radius: 12px; border: 1px solid #e2e8f0;">
                <h2 style="font-size: 1.2rem; text-transform: uppercase; margin-bottom: 1.5rem; border-bottom: 2px solid #ff5200; padding-bottom: 0.4rem; color: #0f172a;">Opiniones de Compradores</h2>
                
                <div style="display: flex; flex-direction: column; gap: 1rem;">
                    <c:choose>
                        <c:when test="${not empty requestScope.listaResenias}">
                            <c:forEach var="res" items="${requestScope.listaResenias}">
                                <div class="resena-card" style="padding: 1rem; background: #f8fafc; border-radius: 6px; border-left: 3px solid #ff5200; margin-bottom: 0.5rem;">
                                    <div class="resena-header" style="display: flex; justify-content: space-between; align-items: center;">
                                        <h4 class="resena-usuario" style="font-size: 0.95rem; font-weight: 700; color: #0f172a;">${res.usuario.nombre}</h4>
                                        <span class="resena-fecha" style="font-size: 0.8rem; color: #64748b;">${res.fecha}</span>
                                    </div>
                                    <div class="resena-estrellas" style="color: #e2b93b; margin: 0.2rem 0;">
                                        Calificación: <strong>${res.estrellas} / 5 Estrellas</strong>
                                    </div>
                                    <p class="resena-texto" style="color: #334155; font-size: 0.9rem; line-height: 1.5;">
                                        ${res.comentario}
                                    </p>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p style="color: var(--gris-texto); font-size: 0.95rem; font-style: italic; text-align: center; padding: 1rem 0;">
                                Este artículo deportivo aún no cuenta con opiniones. ¡Sé el primero en dejar una reseña!
                            </p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            
        </main>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>
        <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
    </div>
</body>
</html>