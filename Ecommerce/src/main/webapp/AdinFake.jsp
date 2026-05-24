<%-- 
    Document   : AdinFake
    Created on : 24 may 2026, 14:04:09
    Author     : Fernando garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acceso denegado – SportsZone</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<div style="display:flex;flex-direction:column;align-items:center;justify-content:center;min-height:100vh;gap:1rem;">
    <h1 style="color:#c0392b;">X Acceso denegado X</h1>
    <p>No tienes permisos para ver esta sección.</p>
    <a href="${pageContext.request.contextPath}/Index.jsp" style="color:var(--naranja);">← Volver al inicio</a>
</div>
</body>
</html>
