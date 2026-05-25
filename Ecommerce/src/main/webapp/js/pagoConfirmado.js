// Interacciones de la pantalla de confirmacion de compra

document.addEventListener('DOMContentLoaded', () => {

    document.title = '¡Compra Confirmada! - SportsZone';

    // funcion flecha evitar que el usuario regrese atras con el boton del navegador
    // y vuelva a ver el formulario de pago ya procesado
    history.pushState(null, '', window.location.href);
    window.addEventListener('popstate', () => {
        history.pushState(null, '', window.location.href);
    });

    // animacion simple de entrada para la caja de confirmacion
    const caja = document.querySelector('.caja-form, .confirmacion-box');
    if (caja) {
        caja.style.opacity = '0';
        caja.style.transform = 'translateY(12px)';
        caja.style.transition = 'opacity 0.4s ease, transform 0.4s ease';
        requestAnimationFrame(() => {
            caja.style.opacity = '1';
            caja.style.transform = 'translateY(0)';
        });
    }
});