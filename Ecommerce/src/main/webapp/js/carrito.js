// carrito.js — SportsZone
// Logica del carrito: actualizar cantidades con XHR, recalcular totales
 
// Funcion flecha para formatear precios
const formatearPrecio = (num) => '$' + parseFloat(num).toFixed(2);
 
// XMLHttpRequest envuelto en Promise (resolve y reject explicitos)
const actualizarCantidadXHR = (itemId, nuevaCantidad, contextPath) => {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open(
            'GET',
            contextPath + '/actualizarCantidad?id=' + itemId + '&cantidad=' + nuevaCantidad,
            true
        );
        xhr.onload = () => {
            if (xhr.status === 200) {
                resolve(JSON.parse(xhr.responseText));
            } else {
                reject(new Error('Error HTTP: ' + xhr.status));
            }
        };
        xhr.onerror = () => reject(new Error('Error de red'));
        xhr.send();
    });
};
 
// Recalcular el total del carrito leyendo todos los subtotales del DOM
const recalcularTotal = () => {
    let total = 0;
    document.querySelectorAll('.subtotal-celda').forEach(celda => {
        total += parseFloat(celda.textContent.replace('$', '')) || 0;
    });
    const totalDisplay = document.getElementById('total-carrito');
    if (totalDisplay) totalDisplay.textContent = formatearPrecio(total);
    return total;
};
 
// async/await para manejar cambio de cantidad
const manejarCambioCantidad = async (e) => {
    const select = e.target;
    if (!select.classList.contains('select-cantidad')) return;
 
    const fila           = select.closest('tr');
    const precioUnitario = parseFloat(
        fila.querySelector('.precio-unitario')?.dataset.precio || 0
    );
    const subtotalCelda  = fila.querySelector('.subtotal-celda');
    const nuevaCantidad  = parseInt(select.value);
    const nuevoSubtotal  = precioUnitario * nuevaCantidad;
 
    // Actualizar subtotal en el DOM inmediatamente (optimistic update)
    if (subtotalCelda) {
        subtotalCelda.textContent = formatearPrecio(nuevoSubtotal);
    }
 
    recalcularTotal();
 
    // Intentar persistir en el servidor si existe el endpoint
    const contextPath = document.body.dataset.ctx || '';
    const itemId = fila.dataset.itemId;
    if (itemId) {
        try {
            await actualizarCantidadXHR(itemId, nuevaCantidad, contextPath);
        } catch (err) {
            console.warn('No se pudo sincronizar cantidad:', err.message);
        }
    }
};
 
// Confirmar antes de vaciar el carrito
const confirmarVaciarCarrito = (e) => {
    const confirmado = window.confirm('¿Deseas eliminar todos los productos del carrito?');
    if (!confirmado) e.preventDefault();
};
 
document.addEventListener('DOMContentLoaded', () => {
 
    const tablaCarrito = document.querySelector('.tabla-deportiva-global');
    if (tablaCarrito) {
        tablaCarrito.addEventListener('change', manejarCambioCantidad);
    }
 
    // Boton vaciar carrito (si existe)
    const btnVaciar = document.getElementById('btn-vaciar-carrito');
    if (btnVaciar) {
        btnVaciar.addEventListener('click', confirmarVaciarCarrito);
    }
 
    // Calcular total inicial al cargar la página
    recalcularTotal();
});