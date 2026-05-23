<%-- 
    Document   : ProcesoPago
    Created on : 24 mar 2026, 14:29:17
    Author     : Fernando Garces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>Proceso de Pago</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <%@include  file="/WEB-INF/fragmentos/header.jspf"%>

    <div class="contenido-flex">
         <%@include  file="/WEB-INF/fragmentos/aside.jspf"%>

        <main class="main-pago">
            <div class="btn-back-container">
                <a href="./carrito.html" class="btn-back">&#8592;</a>
            </div>

            <div class="envio-info-container">
                <div class="datos-envio">
                    <p>Enviar a <strong>Ramón Zamudio</strong></p>
                    <a href="#" class="enlace-direccion">ITSON, Av. Kino y Antonio Caso #205</a>
                    <br><br>
                    <a href="#" class="enlace-cambiar">Cambiar dirección de entrega</a>
                </div>
                <div class="fecha-llegada">
                    <p>Llegaría el <strong>6 de Marzo</strong></p>
                </div>
            </div>

            <div class="pago-grid">
                
                <div class="pago-productos-box">
                    
                    <div class="pago-item">
                        <img src="./imgs/sonywh5.jpeg" alt="Escáner" class="pago-img">
                        <div class="pago-detalles">
                            <p class="pago-titulo">Sony wh10000-xm5</p>
                            <div class="pago-controles">
                                <div class="item-cantidad">
                                    <label>cantidad:</label>
                                    <select>
                                        <option value="1">1</option>
                                    </select>
                                </div>
                            </div>
                            <button class="btn-eliminar">Eliminar</button>
                        </div>
                        <div class="pago-precio">
                            <p>$4,953.00</p>
                        </div>
                    </div>

                    <hr class="divisor-pago">

                    <div class="pago-item">
                        <img src="./imgs/focos.webp" alt="Focos LED" class="pago-img">
                        <div class="pago-detalles">
                            <p class="pago-titulo">H4 H7 H11 9005 9006 H1 H3 Kit De Faros Auto Led 18000lm Luz Alta Baja Kit 80W 6500K Bombillas LED</p>
                            <div class="pago-controles">
                                <div class="item-cantidad">
                                    <select>
                                        <option value="1">1</option>
                                    </select>
                                </div>
                            </div>
                            <button class="btn-eliminar">Eliminar</button>
                        </div>
                        <div class="pago-precio">
                            <p>$392</p>
                        </div>
                    </div>
                </div>

                <div class="pago-sidebar">
                    
                    <div class="metodos-pago-box">
                        <h3>Método de Pago</h3>
                        <div class="logos-grid">
                            <div class="logo-tarjeta"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Visa_Inc._logo.svg/200px-Visa_Inc._logo.svg.png" alt="Visa"></div>
                            <div class="logo-tarjeta"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/PayPal.svg/200px-PayPal.svg.png" alt="PayPal"></div>
                            <div class="logo-tarjeta"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Mastercard-logo.svg/200px-Mastercard-logo.svg.png" alt="MasterCard"></div>
                            <div class="logo-tarjeta" style="background-color: #003b87; color: white; display: flex; align-items: center; justify-content: center; font-weight: bold; font-family: sans-serif; border-radius: 4px;">Coppel</div>
                        </div>
                    </div>

                    <div class="total-pago-box">
                        <h3>Total</h3>
                        <div class="total-input">$5,345</div>
                        <a href="./pagoConfirmado.html">Confirmar Pago</a>
                    </div>

                </div>
            </div>
        </main>
    </div>

     <%@include  file="/WEB-INF/fragmentos/footer.jspf"%>
</body>
</html>
