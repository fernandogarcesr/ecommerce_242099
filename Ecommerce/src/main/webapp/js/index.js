// Interacciones de la pagina principal

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'SportsZone - Tienda Deportiva';

    const ctx = document.body.dataset.ctx || '';

    // funcion flecha para animar las tarjetas de categoria al hacer hover
    const tarjetas = document.querySelectorAll('.categoria-card, .admin-card-link');
    tarjetas.forEach(card => {
        card.addEventListener('mouseenter', () => {
            card.style.transform = 'translateY(-3px)';
        });
        card.addEventListener('mouseleave', () => {
            card.style.transform = '';
        });
    });

    // redirigir al catalogo pasando por el servlet para que cargue los datos
    document.querySelectorAll('a[href*="Catalogo.jsp"]').forEach(enlace => {
        enlace.addEventListener('click', (e) => {
            e.preventDefault();
            window.location.href = ctx + '/cargarproducto';
        });
    });
});