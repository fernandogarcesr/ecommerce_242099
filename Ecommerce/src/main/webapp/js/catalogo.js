// catalogo.js — SportsZone
// Filtros del catalogo con Fetch API, async/await y manejo del DOM
 
// Funcion flecha para construir URLs con parametros (BOM: URL)
const construirURL = (base, params) => {
    const url = new URL(base, window.location.href);
    Object.entries(params).forEach(([k, v]) => {
        if (v) url.searchParams.set(k, v);
        else   url.searchParams.delete(k);
    });
    return url.toString();
};
 
// Mostrar indicador de carga en la tabla
const mostrarCargando = (tbody) => {
    if (tbody) {
        tbody.innerHTML = `
            <tr>
                <td colspan="3" style="text-align:center;padding:2rem;color:var(--gris-texto);">
                    ... Cargando productos...
                </td>
            </tr>`;
    }
};
 
// Funcion asíncrona con Fetch para filtrar productos
const filtrarProductos = async (e) => {
    e.preventDefault();
 
    const nombre  = document.getElementById('txt_productos')?.value.trim() || '';
    const precio  = document.getElementById('txt_precio')?.value.trim() || '';
    const ordenEl = document.querySelector('input[name="orden"]:checked');
    const orden   = ordenEl ? ordenEl.value : 'Menor';
    const solo    = document.querySelector('input[name="soloDisponibles"]')?.checked;
 
    const contextPath = document.body.dataset.ctx || '';
 
    const url = construirURL(contextPath + '/cargarproducto', {
        txt_productos:   nombre,
        txt_precio:      precio,
        orden:           orden,
        soloDisponibles: solo ? 'true' : ''
    });
 
    const tbody = document.querySelector('.tabla-deportiva-global tbody');
    mostrarCargando(tbody);
 
    try {
        // Fetch con async/await 
        const response = await fetch(url, {
            headers: { 'X-Requested-With': 'XMLHttpRequest' }
        });
 
        if (!response.ok) throw new Error('Error al obtener productos: ' + response.status);
 
        // BOM: actualizar la URL del navegador sin recargar 
        window.history.pushState({}, '', url);
 
        // Navegar para que el JSP renderice la tabla con los resultados
        window.location.href = url;
 
    } catch (error) {
        // Promise explicita con resolve y reject
        return new Promise((_resolve, reject) => {
            reject(error);
        }).catch(() => {
            if (tbody) {
                tbody.innerHTML = `
                    <tr>
                        <td colspan="3" style="text-align:center;padding:2rem;color:var(--rojo);font-weight:700;">
                            X Error al cargar productos. Intenta de nuevo.
                        </td>
                    </tr>`;
            }
        });
    }
};
 
// Limpiar filtros y recargar catálogo completo
const limpiarFiltros = () => {
    const txtNombre = document.getElementById('txt_productos');
    const txtPrecio = document.getElementById('txt_precio');
    const checkDisp = document.querySelector('input[name="soloDisponibles"]');
    const ordenMenor = document.querySelector('input[name="orden"][value="Menor"]');
 
    if (txtNombre)  txtNombre.value  = '';
    if (txtPrecio)  txtPrecio.value  = '';
    if (checkDisp)  checkDisp.checked = false;
    if (ordenMenor) ordenMenor.checked = true;
 
    const contextPath = document.body.dataset.ctx || '';
    window.location.href = contextPath + '/cargarproducto';
};
 
document.addEventListener('DOMContentLoaded', () => {
 
    const formFiltro = document.querySelector('.seccion-filtros form');
    if (formFiltro) {
        formFiltro.addEventListener('submit', filtrarProductos);
    }
 
    const btnLimpiar = document.getElementById('btn-limpiar-filtros');
    if (btnLimpiar) {
        btnLimpiar.addEventListener('click', limpiarFiltros);
    }
 
    // Busqueda en vivo al escribir en el campo nombre
    const txtNombre = document.getElementById('txt_productos');
    if (txtNombre) {
        let timer;
        txtNombre.addEventListener('input', () => {
            clearTimeout(timer);
            timer = setTimeout(() => {
                const form = txtNombre.closest('form');
                if (form) form.dispatchEvent(new Event('submit', { cancelable: true }));
            }, 600);
        });
    }
});