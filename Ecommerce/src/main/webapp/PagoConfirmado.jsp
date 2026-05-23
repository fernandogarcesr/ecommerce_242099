<%-- 
    Document   : PagoConfirmado
    Created on : 24 mar 2026, 14:28:13
    Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>Confirmación de Compra</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <%@include  file="/WEB-INF/fragmentos/header.jspf"%>

    <div class="contenido-flex">
        <%@include  file="/WEB-INF/fragmentos/aside.jspf"%>

        <main class="main-confirmacion">
            
            <div class = "top-contenedor">
                <a href="./index.html"><img src="./imgs/back.png" alt=""></a>
                <h1>Compra Realizada Con exito!</h1>
            </div>

            <div class="caja-informacion">
                <h3 class="titulo-caja">Detalles del Pedido</h3>
                <hr class="linea-caja">
                
                <div class="grid-detalles">
                    <div class="detalle-etiqueta">Número de Pedido:</div>
                    <div class="detalle-valor">#458921</div>

                    <div class="detalle-etiqueta">Fecha de Compra:</div>
                    <div class="detalle-valor">6 de Marzo de 2026</div>

                    <div class="detalle-etiqueta">Dirección de Entrega:</div>
                    <div class="detalle-valor">ITSON, Av. Kino y Antonio Caso #205</div>

                    <div class="detalle-etiqueta">Destinatario:</div>
                    <div class="detalle-valor">Ramón Zamudio</div>
                </div>
            </div>

            <div class="caja-informacion">
                <h3 class="titulo-caja">Resúmen de Productos</h3>
                <hr class="linea-caja">
                
                <div class="lista-productos-confirmacion">
                    <div class="fila-producto">
                        <span class="prod-nombre">Escáner OBD2 V529</span>
                        <span class="prod-cantidad">Cantidad: 1</span>
                        <span class="prod-precio">$699</span>
                    </div>
                    <div class="fila-producto">
                        <span class="prod-nombre">Kit de Faros LED H4 H7 H11</span>
                        <span class="prod-cantidad">Cantidad: 1</span>
                        <span class="prod-precio">$392</span>
                    </div>
                </div>

                <hr class="linea-caja gruesa">
                
                <div class="fila-total-confirmacion">
                    <span>TOTAL PAGADO:</span>
                    <span>$1,091</span>
                </div>
            </div>

            <div class="contenedor-btn-centro">
                <a href="./index.html" class="btn-regresar-inicio">Regresar al Inicio</a>
            </div>

        </main>
    </div>

    <%@include  file="/WEB-INF/fragmentos/footer.jspf"%>
</body>
</html>