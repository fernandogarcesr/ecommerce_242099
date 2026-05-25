// Validacion y UX del formulario de perfil de usuario

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Mi Perfil - SportsZone';

    const mostrarMensaje = (msg, esError = true) => {
        let div = document.getElementById('msg-perfil');
        if (!div) {
            div = document.createElement('div');
            div.id = 'msg-perfil';
            const form = document.querySelector('form');
            if (form)
                form.insertAdjacentElement('beforebegin', div);
        }
        div.style.cssText = esError
                ? 'background:#FFEBEE;border-left:4px solid #E53935;color:#B71C1C;padding:10px 14px;border-radius:4px;margin-bottom:1rem;font-size:0.9rem;font-weight:600;'
                : 'background:#E8F5E9;border-left:4px solid #43A047;color:#1B5E20;padding:10px 14px;border-radius:4px;margin-bottom:1rem;font-size:0.9rem;font-weight:600;';
        div.textContent = msg;
        div.style.display = 'block';
    };

    const validarPerfil = (e) => {
        const nombre = document.getElementById('txt_nombre')?.value.trim() || '';
        const correo = document.getElementById('txt_email')?.value.trim() || '';

        if (!nombre || !correo) {
            e.preventDefault();
            mostrarMensaje('El nombre y el correo son obligatorios.');
            return;
        }

        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(correo)) {
            e.preventDefault();
            mostrarMensaje('El correo no tiene un formato válido.');
        }
    };

    const form = document.querySelector('form');
    if (form)
        form.addEventListener('submit', validarPerfil);
});