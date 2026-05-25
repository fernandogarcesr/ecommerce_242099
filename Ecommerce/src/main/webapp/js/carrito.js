// carrito.js — SportsZone
// Actualización dinámica del carrito con XMLHttpRequest y async/await

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Mi Carrito - SportsZone';

    const ctx = document.body.dataset.ctx || '';
// Funcion flecha para formatear precios
    const formatearPrecio = (num) => '$' + parseFloat(num).toFixed(2);
// XMLHttpRequest envuelto en Promise (resolve y reject explicitos)
    const actualizarCantidadXHR = (itemId, cantidad) => {
        return new Promise((resolve, reject) => {
            const xhr = new XMLHttpRequest();
            xhr.open('GET', ctx + '/actualizarCantidad?id=' + itemId + '&cantidad=' + cantidad, true);
            xhr.onload = () => {
                xhr.status === 200
                        ? resolve(JSON.parse(xhr.responseText))
                        : reject(new Error('Error HTTP: ' + xhr.status));
            };
            xhr.onerror = () => reject(new Error('Error de red'));
            xhr.send();
        });
    };

// async/await para manejar cambio de cantidad
    const manejarCambio = async (e) => {
        const select = e.target;
        if (!select.classList.contains('select-cantidad'))
            return;

        const fila = select.closest('tr');
        const precioUnitario = parseFloat(
                fila.querySelector('.precio-unitario')?.dataset.precio || 0
                );
        const subtotalCelda = fila.querySelector('.subtotal-celda');
        const nuevaCantidad = parseInt(select.value);

        // Actualizar subtotal en el DOM inmediatamente (optimistic update)
        if (subtotalCelda) {
            subtotalCelda.textContent = formatearPrecio(precioUnitario * nuevaCantidad);
        }

        // Recalcular total en el DOM
        let total = 0;
        document.querySelectorAll('.subtotal-celda').forEach(c => {
            total += parseFloat(c.textContent.replace('$', '')) || 0;
        });

        const totalDisplay = document.getElementById('total-carrito');
        if (totalDisplay)
            totalDisplay.textContent = formatearPrecio(total);
    };

    const tabla = document.querySelector('.tabla-deportiva-global');
    if (tabla)
        tabla.addEventListener('change', manejarCambio);
});

