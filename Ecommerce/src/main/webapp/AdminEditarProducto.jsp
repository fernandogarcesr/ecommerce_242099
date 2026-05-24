<%-- AdminEditarProducto.jsp — editar datos de un producto existente --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar producto – SportstZone Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<div class="grid-container">
    <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
    <%@include file="/WEB-INF/fragmentos/header.jspf" %>
    <main class="content">
        <a href="${pageContext.request.contextPath}/cargarproducto?vista=adminProducto" class="btn-volver">← Volver al catálogo</a>
        <div class="top-contenedor"><h1>Editar producto</h1></div>
        <div class="caja-form">
            <form action="${pageContext.request.contextPath}/EditarProducto" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${requestScope.producto.id}">
                <div class="input-bloque">
                    <label for="nombre">Nombre del producto</label>
                    <input type="text" id="nombre" name="nombre" class="input-campo"
                           value="${requestScope.producto.nombre}" required>
                </div>
                <div class="input-bloque">
                    <label for="descripcion">Descripción</label>
                    <textarea id="descripcion" name="desc" class="input-campo" rows="4" required>${requestScope.producto.descripcion}</textarea>
                </div>
                    <div class="input-bloque">
                        <label for="precio">Precio ($)</label>
                        <input type="number" id="precio" name="precio" class="input-campo"
                               value="${requestScope.producto.precio}" min="0" step="0.01" required>
                    </div>
                    <div class="input-bloque">
                        <label for="stock">Stock</label>
                        <input type="number" id="stock" name="stock" class="input-campo"
                               value="${requestScope.producto.stock}" min="0" required>
                    </div>
                    <input type="hidden" name="rutaImagenActual" value="${requestScope.producto.rutaImagen}">
                    <div class="botones-form">
                        <a href="${pageContext.request.contextPath}/cargarproducto?vista=adminProducto" class="btn-deportivo-accion btn-outline">Cancelar</a>
                        <button type="submit" class="btn-deportivo-accion btn-naranja">Actualizar</button>
                    </div>
            </form>
        </div>
    </main>
    <script src="${pageContext.request.contextPath}/js/nav.js"></script>
    <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
</div>
</body>
</html>
