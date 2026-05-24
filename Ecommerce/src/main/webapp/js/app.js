// SportsZone - app.js
// Manejo del DOM, BOM, Fetch, XMLHttpRequest, async/await, funciones flecha

// ─── MÓDULO CATÁLOGO ────────────────────────────────────────────────────────

// Funcion flecha para construir URLs con parametros
const construirURL = (base, params) => {
    const url = new URL(base, window.location.href);
    Object.entries(params).forEach(([k, v]) => {
        if (v) url.searchParams.set(k, v);
    });
    return url.toString();
};

// Funcion asincrona con async/await y Fetch API
const filtrarProductos = async (e) => {
    e.preventDefault();

    const nombre  = document.getElementById('txt_productos')?.value.trim() || '';
    const precio  = document.getElementById('txt_precio')?.value.trim() || '';
    const ordenEl = document.querySelector('input[name="orden"]:checked');
    const orden   = ordenEl ? ordenEl.value : 'Menor';
    const solo    = document.querySelector('input[name="soloDisponibles"]')?.checked;

    const contextPath = document.body.dataset.ctx || '';

    const url = construirURL(contextPath + '/cargarproducto', {
        txt_productos:    nombre,
        txt_precio:       precio,
        orden:            orden,
        soloDisponibles:  solo ? 'true' : ''
    });

    const tablaCuerpo = document.querySelector('.tabla-deportiva-global tbody');

    try {
        if (tablaCuerpo) {
            tablaCuerpo.innerHTML =
                '<tr><td colspan="3" style="text-align:center;padding:1.5rem;">Cargando...</td></tr>';
        }

        // Fetch con async/await
        const response = await fetch(url, {
            headers: { 'X-Requested-With': 'XMLHttpRequest' }
        });

        if (!response.ok) throw new Error('Error al obtener productos');

        // BOM: navegar a la URL filtrada
        window.location.href = url;

    } catch (error) {
        // Promise con resolve y reject explícitos
        return new Promise((resolve, reject) => {
            reject(error);
            if (tablaCuerpo) {
                tablaCuerpo.innerHTML =
                    '<tr><td colspan="3" style="text-align:center;color:red;">' +
                    'Error al cargar productos.</td></tr>';
            }
            resolve();
        });
    }
};

// MODULO CARRITO 

// Funcion flecha para formatear precios
const formatearPrecio = (num) => '$' + parseFloat(num).toFixed(2);

// XMLHttpRequest envuelto en Promise (resolve y reject explícitos)
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

// async/await para manejar cambio de cantidad en el carrito
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

    if (subtotalCelda) {
        subtotalCelda.textContent = formatearPrecio(nuevoSubtotal);
    }

    // Recalcular total general desde el DOM
    let total = 0;
    document.querySelectorAll('.subtotal-celda').forEach(celda => {
        total += parseFloat(celda.textContent.replace('$', '')) || 0;
    });

    const totalDisplay = document.getElementById('total-carrito');
    if (totalDisplay) totalDisplay.textContent = formatearPrecio(total);
};

// INICIALIZACION

// Manejo del DOM: esperar a que cargue el documento
document.addEventListener('DOMContentLoaded', () => {

    // Enlazar filtro del catalogo
    const formFiltro = document.querySelector('.seccion-filtros form');
    if (formFiltro) {
        formFiltro.addEventListener('submit', filtrarProductos);
    }

    // Enlazar cambios de cantidad en el carrito
    const tablaCarrito = document.querySelector('.tabla-deportiva-global');
    if (tablaCarrito) {
        tablaCarrito.addEventListener('change', manejarCambioCantidad);
    }

});

