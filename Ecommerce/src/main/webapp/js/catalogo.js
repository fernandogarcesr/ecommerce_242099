// catalogo.js — SportsZone
// Filtrado dinámico con Fetch, async/await y funciones flecha

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Catálogo Deportivo - SportsZone';

    const ctx = document.body.dataset.ctx || '';

// Funcion flecha para construir URLs con parametros
    const construirURL = (base, params) => {
        const url = new URL(base, window.location.href);
        Object.entries(params).forEach(([k, v]) => {
            if (v)
                url.searchParams.set(k, v);
        });
        return url.toString();
    };

// async/await + Fetch para filtrar productos
    const filtrarProductos = async (e) => {
        e.preventDefault();

        const nombre = document.getElementById('txt_productos')?.value.trim() || '';
        const precio = document.getElementById('txt_precio')?.value.trim() || '';
        const ordenEl = document.querySelector('input[name="orden"]:checked');
        const orden = ordenEl ? ordenEl.value : 'Menor';
        const solo = document.querySelector('input[name="soloDisponibles"]')?.checked;

        const contextPath = document.body.dataset.ctx || '';

        const url = construirURL(ctx + '/cargarproducto', {
            txt_productos: nombre,
            txt_precio: precio,
            orden: orden,
            soloDisponibles: solo ? 'true' : ''
        });
        const tbody = document.querySelector('.tabla-deportiva-global tbody');

        try {
            if (tbody) {
                tbody.innerHTML =
                        '<tr><td colspan="3" style="text-align:center;padding:1.5rem;">Buscando...</td></tr>';
            }

            const response = await fetch(url, {
                headers: {'X-Requested-With': 'XMLHttpRequest'}
            });

            if (!response.ok)
                throw new Error('Error al obtener productos');

            // BOM: navegar a la URL filtrada
            window.location.href = url;

        } catch (error) {
            // resolve y reject explícitos
            return new Promise((resolve, reject) => {
                reject(error);
                if (tbody) {
                    tbody.innerHTML =
                            '<tr><td colspan="3" style="text-align:center;color:red;">' +
                            'Error al cargar productos.</td></tr>';
                }
                resolve();
            });
        }
    };

    const formFiltro = document.querySelector('.seccion-filtros form');
    if (formFiltro) {
        formFiltro.addEventListener('submit', filtrarProductos);
    }

});