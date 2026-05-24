// nav.js — SportsZone
// Menu para móvil (BOM + DOM)
 
document.addEventListener('DOMContentLoaded', () => {
 
    // ── Crear boton dinamicamente (manejo del DOM)
    const crearBotonHamburguesa = () => {
        const btn = document.createElement('button');
        btn.id = 'btn-menu-movil';
        btn.setAttribute('aria-label', 'Abrir menú');
        btn.setAttribute('aria-expanded', 'false');
        btn.innerHTML = `
            <span class="ham-linea"></span>
            <span class="ham-linea"></span>
            <span class="ham-linea"></span>
        `;
        return btn;
    };
 
    // ── Crear overlay oscuro para cerrar el menu al tocar fuera
    const crearOverlay = () => {
        const overlay = document.createElement('div');
        overlay.id = 'sidebar-overlay';
        document.body.appendChild(overlay);
        return overlay;
    };
 
    const header  = document.querySelector('.header-tienda');
    const sidebar = document.querySelector('.sidebar-tienda');
 
    if (!header || !sidebar) return;
 
    const btn     = crearBotonHamburguesa();
    const overlay = crearOverlay();
 
    header.insertAdjacentElement('afterbegin', btn);
 
    // Funcion flecha para abrir/cerrar sidebar
    const toggleMenu = () => {
        const abierto = sidebar.classList.toggle('sidebar-abierto');
        overlay.classList.toggle('overlay-visible', abierto);
        btn.setAttribute('aria-expanded', String(abierto));
        // BOM: bloquear scroll del body cuando el menu esta abierto en movil
        document.body.style.overflow = abierto ? 'hidden' : '';
    };
 
    const cerrarMenu = () => {
        sidebar.classList.remove('sidebar-abierto');
        overlay.classList.remove('overlay-visible');
        btn.setAttribute('aria-expanded', 'false');
        document.body.style.overflow = '';
    };
 
    btn.addEventListener('click', toggleMenu);
    overlay.addEventListener('click', cerrarMenu);
 
    // Cerrar menu al hacer click en cualquier enlace del sidebar 
    sidebar.querySelectorAll('a').forEach(enlace => {
        enlace.addEventListener('click', cerrarMenu);
    });
 
    // BOM: cerrar menu si se gira el dispositivo a landscape
    window.addEventListener('resize', () => {
        if (window.innerWidth > 860) {
            cerrarMenu();
        }
    });
 
    // ── Marcar enlace activo segun la URL actual
    const urlActual = window.location.pathname;
    sidebar.querySelectorAll('a').forEach(enlace => {
        if (enlace.getAttribute('href') && urlActual.includes(
            enlace.getAttribute('href').split('/').pop()
        )) {
            enlace.classList.add('activo');
        }
    });
 
});

