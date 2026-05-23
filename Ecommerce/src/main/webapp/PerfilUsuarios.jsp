<%-- 
    Document   : PerfilUsuarios
    Created on : 24 mar 2026, 14:28:56
    Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Perfil - SportsZone</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <div class="grid-container">
        <%@include file="/WEB-INF/fragmentos/header.jspf" %>
        <%@include file="/WEB-INF/fragmentos/aside.jspf" %>

        <main class="content form-wrapper-centro">
            <div class="caja-autenticacion" style="max-width: 550px;">
                <div style="text-align: center; margin-bottom: 2rem;">
                    <img src="./imgs/user.png" alt="Usuario" style="width: 70px; height: 70px; border-radius: 50%; margin-bottom: 0.5rem;">
                    <h1 style="font-size: 1.5rem; font-weight: 900; text-transform: uppercase;">Información de Cuenta</h1>
                </div>

                <form action="editar_perfil" method="POST">
                    <div class="input-bloque">
                        <label for="txt_nombre">Nombre Completo</label>
                        <input type="text" id="txt_nombre" name="nombre" class="input-campo" value="${sessionScope.usuarioActual.nombre}">
                    </div>      
                    <div class="input-bloque">
                        <label for="txt_numero">Teléfono</label>
                        <input type="text" id="txt_numero" name="telefono" class="input-campo" value="${sessionScope.usuarioActual.telefono}">
                    </div>                  
                    <div class="input-bloque">
                        <label for="txt_email">Email</label>
                        <input type="email" id="txt_email" name="correo" class="input-campo" value="${sessionScope.usuarioActual.correo}">
                    </div>
                    <div class="input-bloque">
                        <label for="txt_direccion">Dirección de Entrega</label>
                        <input type="text" id="txt_direccion" name="direccion" class="input-campo" value="${sessionScope.usuarioActual.direccion}">
                    </div>

                    <button type="submit" class="btn-deportivo-accion" style="width: 100%; padding: 0.8rem; margin-top: 1rem;">
                        Guardar Cambios
                    </button>
                </form>
            </div>
        </main>

        <%@include file="/WEB-INF/fragmentos/footer.jspf" %>
    </div>
</body>
</html>