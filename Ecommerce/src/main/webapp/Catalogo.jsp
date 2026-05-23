<%-- 
    Document   : Catalogo
    Created on : 24 mar 2026, 14:26:46
    Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo Deportivo - SportsZone</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div class="grid-container">
        <%@include file="/WEB-INF/fragmentos/header.jspf" %>
        
        <main class="content">
            <div class="grid-catalogo">
                
                <%@include file="/WEB-INF/fragmentos/aside-filtro.jspf" %>

                <div class="catalogo-productos-seccion">
                    <div class="top-contenedor">
                        <h1>Catálogo de Productos</h1>
                    </div>

                    <table class="tabla-deportiva-global">
                        <thead>
                            <tr>
                                <th style="width: 50%;">Artículo Deportivo</th>
                                <th style="width: 20%;">Precio</th>
                                <th style="width: 30%; text-align: center;">Operaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><strong>Tenis Running Volt Pro</strong><br><span style="font-size: 0.8rem; color: #64748b;">Calzado ligero para asfalto.</span></td>
                                <td style="font-weight: 700;">$2,499.00</td>
                                <td>
                                    <div style="display: flex; gap: 6px; justify-content: center;">
                                        <a href="DetallesProducto.jsp?id=1" class="btn-deportivo-accion btn-secundario">Detalles</a>
                                        <a href="Carrito.jsp?id=1" class="btn-deportivo-accion">Añadir</a>
                                        <a href="CrearResenia.jsp?id=1" class="btn-deportivo-accion btn-secundario">Reseña</a>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td><strong>Jersey de Fútbol Pro-Fit</strong><br><span style="font-size: 0.8rem; color: #64748b;">Tejido de secado rápido.</span></td>
                                <td style="font-weight: 700;">$1,199.00</td>
                                <td>
                                    <div style="display: flex; gap: 6px; justify-content: center;">
                                        <a href="DetallesProducto.jsp?id=2" class="btn-deportivo-accion btn-secundario">Detalles</a>
                                        <a href="Carrito.jsp?id=2" class="btn-deportivo-accion">Añadir</a>
                                        <a href="CrearResenia.jsp?id=2" class="btn-deportivo-accion btn-secundario">Reseña</a>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </main>

        <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
    </div>
</body>
</html>