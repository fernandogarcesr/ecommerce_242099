// Validacion del formulario de inicio de sesion

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Iniciar Sesión - SportsZone';

    const mostrarError = (msg) => {
        let div = document.getElementById('msg-error-login');
        if (!div) {
            div = document.createElement('div');
            div.id = 'msg-error-login';
            div.style.cssText = 'background:#FFEBEE;border-left:4px solid #E53935;color:#B71C1C;padding:10px 14px;border-radius:4px;margin-bottom:1rem;font-size:0.9rem;font-weight:600;';
            const form = document.querySelector('form');
            if (form) form.insertAdjacentElement('beforebegin', div);
        }
        div.textContent = msg;
        div.style.display = 'block';
    };

    const validarLogin = (e) => {
        // IDs correo y contrasenia
        const correo = document.getElementById('correo')?.value.trim() || '';
        const pass = document.getElementById('contrasenia')?.value || '';

        if (!correo || !pass) {
            e.preventDefault();
            mostrarError('Por favor completa todos los campos.');
            return;
        }

        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(correo)) {
            e.preventDefault();
            mostrarError('El correo electrónico no tiene un formato válido.');
        }
    };

    const form = document.querySelector('form');
    if (form) form.addEventListener('submit', validarLogin);
});