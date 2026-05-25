// Interacciones de la pagina de historial de pedidos

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Mis Pedidos - SportsZone';

    // para mejor compatibilidad en tablas con fondo dinamico
    const filas = document.querySelectorAll('.tabla-deportiva-global tbody tr');
    filas.forEach(fila => {
        fila.addEventListener('mouseenter', () => {
            fila.style.background = 'rgba(240,78,35,0.06)';
        });
        fila.addEventListener('mouseleave', () => {
            fila.style.background = '';
        });
    });
});