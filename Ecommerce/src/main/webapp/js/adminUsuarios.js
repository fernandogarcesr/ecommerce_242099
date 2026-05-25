// Interacciones del panel de gestion de usuarios

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Gestión de Usuarios - SportsZone';

    // busqueda en tiempo real por nombre o correo
    const inputBuscar = document.getElementById('buscar-usuario');
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


