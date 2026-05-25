// Interacciones del panel principal del administrador

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Panel Admin - SportsZone';

    // animacion de entrada para las tarjetas del panel
    const tarjetas = document.querySelectorAll('.admin-card-link');
    tarjetas.forEach((card, i) => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(10px)';
        card.style.transition = `opacity 0.3s ease ${i * 0.07}s, transform 0.3s ease ${i * 0.07}s`;
        requestAnimationFrame(() => {
            card.style.opacity = '1';
            card.style.transform = 'translateY(0)';
        });
    });
});