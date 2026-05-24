<%-- 
Document   : Catalogo
Created on : 24 mar 2026, 14:26:46
Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
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
            <%@include file="/WEB-INF/fragmentos/aside.jspf"%>
            <%@include file="/WEB-INF/fragmentos/header.jspf"%>

            <main class="content">
                <div class="grid-catalogo">

                    <%@include file="/WEB-INF/fragmentos/aside-filtro.jspf"%>

                    <div class="catalogo-productos-seccion">
                        <div class="top-contenedor">
                            <h1>Catálogo de productos</h1>
                        </div>

                        <c:if test="${not empty requestScope.mensajeStock}">
                            <div style="background:#FFEBEE; border-left:4px solid #E53935; color:#B71C1C;
                                 padding:12px 18px; border-radius:4px; margin-bottom:1rem;
                                 font-weight:600; font-size:0.9rem;">
                                ⚠ ${requestScope.mensajeStock}
                            </div>
                        </c:if>

                        <table class="tabla-deportiva-global">
                            <thead>
                                <tr>
                                    <th style="width:50%;">Artículo deportivo</th>
                                    <th style="width:20%;">Precio</th>
                                    <th style="width:30%;text-align:center;">Operaciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${not empty requestScope.listaProductos}">
                                        <c:forEach var="p" items="${requestScope.listaProductos}">
                                            <tr>
                                                <td>
                                                    <strong>${p.nombre}</strong><br>
                                                    <span style="font-size:.82rem;color:var(--gris-texto);">
                                                        ${p.descripcion}
                                                    </span>
                                                </td>
                                                <td style="font-weight:700;color:var(--naranja);">$${p.precio}</td>
                                                <td>
                                                    <div style="display:flex;gap:5px;justify-content:center;flex-wrap:wrap;">
                                                        <a href="${pageContext.request.contextPath}/cargarproducto?id=${p.id}"
                                                           class="btn-deportivo-accion btn-sm btn-negro">Detalles</a>
                                                        <a href="${pageContext.request.contextPath}/agregarCarrito?id=${p.id}"
                                                           class="btn-deportivo-accion btn-sm btn-naranja">Añadir</a>
                                                        <a href="${pageContext.request.contextPath}/cargarproducto?id=${p.id}&vista=resenia"
                                                           class="btn-deportivo-accion btn-sm btn-outline">Reseña</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td colspan="3" style="text-align:center;padding:2rem;
                                                color:var(--gris-texto);font-weight:700;">
                                                No hay productos disponibles con esos filtros.
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </main>
            <script>
            // Filtrado dinamico del catalogo con Fetch API y async/await
              // Manejo del DOM y BOM (document, window)

                const formFiltro = document.querySelector('.seccion-filtros form');
                const tablaCuerpo = document.querySelector('.tabla-deportiva-global tbody');

                // Función flecha para construir la URL de busqueda
                const construirURL = (base, params) => {
                    const url = new URL(base, window.location.href);
                    Object.entries(params).forEach(([k, v]) => {
                        if (v)
                            url.searchParams.set(k, v);
                    });
                    return url.toString();
                };

                // Funcion asíncrona con async/await y Fetch
                const filtrarProductos = async (e) => {
                    e.preventDefault();

                    const nombre = document.getElementById('txt_productos').value.trim();
                    const precio = document.getElementById('txt_precio').value.trim();
                    const orden = document.querySelector('input[name="orden"]:checked').value;
                    const solo = document.querySelector('input[name="soloDisponibles"]')?.checked;

                    const url = construirURL(
                            '${pageContext.request.contextPath}/cargarproducto',
                            {txt_productos: nombre, txt_precio: precio, orden, soloDisponibles: solo ? 'true' : ''}
                    );

                    try {
                        tablaCuerpo.innerHTML = '<tr><td colspan="3" style="text-align:center;padding:1.5rem;">Cargando...</td></tr>';

                        const response = await fetch(url, {
                            headers: {'X-Requested-With': 'XMLHttpRequest'}
                        });

                        if (!response.ok)
                            throw new Error('Error al obtener productos');

                        // Recargar con los nuevos parametros vía BOM
                        window.location.href = url;

                    } catch (error) {
                        // resolve/reject implícito mediante try/catch de async
                        return new Promise((resolve, reject) => {
                            reject(error);
                            tablaCuerpo.innerHTML =
                                    '<tr><td colspan="3" style="text-align:center;color:red;">Error al cargar productos.</td></tr>';
                            resolve();
                        });
                    }
                };

                // Manejo del DOM: escuchar el submit del formulario
                if (formFiltro) {
                    formFiltro.addEventListener('submit', filtrarProductos);
                }
            </script>

            <%@include file="/WEB-INF/fragmentos/footer.jspf"%>
        </div>
    </body>
</html>