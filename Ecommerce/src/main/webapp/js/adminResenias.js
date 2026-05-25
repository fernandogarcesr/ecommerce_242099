// Interacciones del panel de gestion de reseñas

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Gestión de Reseñas - SportsZone';

    // filtro en tiempo real
    const inputBuscar = document.getElementById('buscar-resenia');
    const filas = document.querySelectorAll('.tabla-deportiva-global tbody tr');

    if (inputBuscar) {
        inputBuscar.addEventListener('input', () => {
            const termino = inputBuscar.value.toLowerCase().trim();
            filas.forEach(fila => {
                fila.style.display = fila.textContent.toLowerCase().includes(termino) ? '' : 'none';
            });
        });
    }
});