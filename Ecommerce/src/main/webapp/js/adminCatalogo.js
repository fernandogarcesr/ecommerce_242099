// Busqueda en tiempo real en el catalogo del administrador

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Catálogo Admin - SportsZone';

    // busqueda en tiempo real sobre la tabla sin hacer peticion al servidor
    const inputBuscar = document.getElementById('buscar-admin');
    const filas = document.querySelectorAll('.tabla-deportiva-global tbody tr');

    if (inputBuscar) {
        inputBuscar.addEventListener('input', () => {
            const termino = inputBuscar.value.toLowerCase().trim();
            filas.forEach(fila => {
                const texto = fila.textContent.toLowerCase();
                fila.style.display = texto.includes(termino) ? '' : 'none';
            });
        });
    }

    // confirmacion antes de eliminar producto
    document.querySelectorAll('form[action*="borrarproducto"]').forEach(form => {
        form.addEventListener('submit', (e) => {
            if (!confirm('¿Estás seguro de que deseas eliminar este producto?')) {
                e.preventDefault();
            }
        });
    });
});