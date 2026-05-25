// Interacciones del panel de gestion de pedidos

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Gestión de Pedidos - SportsZone';

    // filtro en tiempo real por numero de pedido o correo del cliente
    const inputBuscar = document.getElementById('buscar-pedido');
    const filas = document.querySelectorAll('.tabla-deportiva-global tbody tr');

    if (inputBuscar) {
        inputBuscar.addEventListener('input', () => {
            const termino = inputBuscar.value.toLowerCase().trim();
            filas.forEach(fila => {
                fila.style.display = fila.textContent.toLowerCase().includes(termino) ? '' : 'none';
            });
        });
    }

    // confirmar antes de actualizar estado de pedido
    document.querySelectorAll('form[action*="actualizarEstadoPedido"]').forEach(form => {
        form.addEventListener('submit', (e) => {
            const select = form.querySelector('select[name="estado-pedido"]');
            if (select && !confirm(`¿Cambiar el estado del pedido a "${select.value}"?`)) {
                e.preventDefault();
            }
        });
    });
});
