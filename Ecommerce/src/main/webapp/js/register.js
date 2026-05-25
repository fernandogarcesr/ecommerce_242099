// Validacion del formulario de registro de usuario

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Crear Cuenta - SportsZone';

    const mostrarError = (msg) => {
        let div = document.getElementById('msg-error-reg');
        if (!div) {
            div = document.createElement('div');
            div.id = 'msg-error-reg';
            div.style.cssText = 'background:#FFEBEE;border-left:4px solid #E53935;color:#B71C1C;padding:10px 14px;border-radius:4px;margin-bottom:1rem;font-size:0.9rem;font-weight:600;';
            const form = document.querySelector('form');
            if (form) form.insertAdjacentElement('beforebegin', div);
        }
        div.textContent = msg;
        div.style.display = 'block';
    };

    const validarRegistro = (e) => {
        // IDs reales en Register.jsp
        const nombre    = document.getElementById('txt_nombre')?.value.trim() || '';
        const correo    = document.getElementById('txt_correo')?.value.trim() || '';
        const pass      = document.getElementById('txt_password')?.value || '';
        const pass2     = document.getElementById('txt_password_confirm')?.value || '';
        const telefono  = document.getElementById('txt_telefono')?.value.trim() || '';
        const direccion = document.getElementById('txt_direccion')?.value.trim() || '';

        if (!nombre || !correo || !pass || !pass2 || !telefono || !direccion) {
            e.preventDefault();
            mostrarError('Todos los campos son obligatorios.');
            return;
        }

        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(correo)) {
            e.preventDefault();
            mostrarError('El correo no tiene un formato válido.');
            return;
        }

        if (pass.length < 6) {
            e.preventDefault();
            mostrarError('La contraseña debe tener al menos 6 caracteres.');
            return;
        }

        // validar que las contraseñas coincidan
        if (pass !== pass2) {
            e.preventDefault();
            mostrarError('Las contraseñas no coinciden.');
        }
    };

    const form = document.querySelector('form');
    if (form) form.addEventListener('submit', validarRegistro);
});