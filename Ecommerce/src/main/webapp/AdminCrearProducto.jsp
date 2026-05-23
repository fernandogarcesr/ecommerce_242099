<%-- AdminCrearProducto.jsp — formulario para añadir un producto nuevo --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear producto – SporstZone Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<div class="grid-container">
    <%@include file="/WEB-INF/fragmentos/aside.jspf" %>
    <%@include file="/WEB-INF/fragmentos/header.jspf" %>
    <main class="content">
        <a href="${pageContext.request.contextPath}/AdminCatalogo.jsp" class="btn-volver">← Volver al catálogo</a>
        <div class="top-contenedor"><h1>Nuevo producto</h1></div>
        <div class="caja-form">
            <form action="${pageContext.request.contextPath}/agregarProducto" method="post">
                <div class="input-bloque">
                    <label for="nombre">Nombre del producto</label>
                    <input type="text" id="nombre" name="nombre" class="input-campo" placeholder="Ej: Tenis Running X200" required>
                </div>
                <div class="input-bloque">
                    <label for="descripcion">Descripción</label>
                    <textarea id="descripcion" name="descripcion" class="input-campo"
                              rows="4" placeholder="Describe las características del artículo..." required></textarea>
                </div>
                <div class="input-bloque">
                    <label for="precio">Precio ($)</label>
                    <input type="number" id="precio" name="precio" class="input-campo"
                           placeholder="0.00" min="0" step="0.01" required>
                </div>
                <div class="input-bloque">
                    <label for="cantidadStock">Cantidad en stock</label>
                    <input type="number" id="cantidadStock" name="cantidadStock" class="input-campo"
                           placeholder="0" min="0" required>
                </div>
                <div class="botones-form">
                    <a href="${pageContext.request.contextPath}/AdminCatalogo.jsp" class="btn-deportivo-accion btn-outline">Cancelar</a>
                    <button type="submit" class="btn-deportivo-accion btn-naranja">Agregar producto</button>
                </div>
            </form>
        </div>
    </main>
    <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
</div>
</body>
</html>
