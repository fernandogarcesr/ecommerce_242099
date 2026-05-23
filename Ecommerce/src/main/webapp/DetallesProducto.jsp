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
<body>
    <div class="grid-container">
        <%@include file="/WEB-INF/fragmentos/header.jspf" %>
        <%@include file="/WEB-INF/fragmentos/aside.jspf" %>

        <main class="content">
            <div class="top-contenedor">
                <a href="${pageContext.request.contextPath}/Catalogo.jsp" class="btn-regresar">
                    <img src="${pageContext.request.contextPath}/imgs/back.png" alt="Atrás">
                </a>
                <h1>Ficha Técnica del Producto</h1>
            </div>

            <div class="detalle-producto-container">
                <div class="foto-producto-box">
                    <img src="${pageContext.request.contextPath}/imgs/prod-shoes.jpg" alt="Calzado Deportivo">
                    <div style="font-size: 1.1rem; color: #e2b93b; font-weight: 700; margin-top: 0.5rem;">⭐⭐⭐⭐☆ 4.0</div>
                </div>

                <div class="info-compra-box">
                    <h1 style="font-size: 1.8rem; font-weight: 900; text-transform: uppercase; color: #0f172a;">Tenis Running Volt Pro</h1>
                    <p style="color: #64748b; font-size: 0.9rem; margin-top: 0.3rem;">Categoría: Calzado de Rendimiento</p>
                    
                    <div class="precio-tag">$2,499.00</div>
                    
                    <p style="font-size: 0.95rem; color: #334155; line-height: 1.6; margin-bottom: 1.5rem;">
                        Equipados con tecnología de amortiguación responsiva para mitigar impactos en distancias largas. Su malla exterior Pro-Fit garantiza transpirabilidad óptima durante entrenamientos de alta intensidad.
                    </p>

                    <div style="background: #ffffff; padding: 1.2rem; border-radius: 8px; border: 1px solid #e2e8f0; margin-top: auto;">
                        <p style="font-weight: 700; margin-bottom: 0.5rem; font-size: 0.95rem;">Disponibilidad: <span style="color: #22c55e;">En Stock</span></p>
                        <p style="font-size: 0.85rem; color: #64748b; margin-bottom: 1rem;">Unidades en almacén: 37</p>
                        <p style="font-size: 0.85rem; color: #475569; margin-bottom: 1.2rem;"><strong>Especificaciones:</strong> Suela de goma, Malla Pro-Fit, Peso 240g</p>
                        
                        <div style="display: flex; gap: 10px;">
                            <a href="${pageContext.request.contextPath}/Carrito.jsp" class="btn-deportivo-accion" style="flex: 1;">Agregar al Carrito</a>
                        </div>
                    </div>
                </div>
            </div>

            <div style="margin-top: 2.5rem; background: #ffffff; padding: 2rem; border-radius: 12px; border: 1px solid #e2e8f0;">
                <h2 style="font-size: 1.2rem; text-transform: uppercase; margin-bottom: 1.5rem; border-bottom: 2px solid #ff5200; padding-bottom: 0.4rem; color: #0f172a;">Opiniones de Compradores</h2>
                
                <div style="display: flex; flex-direction: column; gap: 1rem;">
                    <div style="padding: 1rem; background: #f8fafc; border-radius: 6px; border-left: 3px solid #cbd5e1;">
                        <h4 style="font-size: 0.95rem; font-weight: 700; color: #0f172a;">Pedro Esquer</h4>
                        <p style="font-size: 0.8rem; color: #64748b; margin: 0.2rem 0;">Calificación: 5 estrellas - 16 de Octubre del 2025</p>
                        <p style="color: #334155; font-size: 0.9rem; line-height: 1.5;">Excelente soporte para carreras de media distancia. Altamente recomendados.</p>
                    </div>
                </div>
            </div>
        </main>

        <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
    </div>
</body>
</html>