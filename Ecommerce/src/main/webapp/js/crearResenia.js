// Validacion del formulario de crear reseña

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Calificar Artículo - SportsZone';

    const validarResenia = (e) => {
        // IDs reales en CrearResenia.jsp: txt_comentario y cmb_calificacion
        const comentario    = document.getElementById('txt_comentario')?.value.trim() || '';
        const calificacion  = document.getElementById('cmb_calificacion')?.value || '';

        if (!comentario) {
            e.preventDefault();
            let div = document.getElementById('msg-resenia');
            if (!div) {
                div = document.createElement('div');
                div.id = 'msg-resenia';
                div.style.cssText = 'background:#FFEBEE;border-left:4px solid #E53935;color:#B71C1C;padding:10px 14px;border-radius:4px;margin-bottom:1rem;font-size:0.9rem;font-weight:600;';
                e.target.insertAdjacentElement('beforebegin', div);
            }
            div.textContent = 'Por favor escribe tu opinión antes de publicar.';
            div.style.display = 'block';
            return;
        }

        if (!calificacion) {
            e.preventDefault();
            alert('Selecciona una calificación.');
        }
    };

    const form = document.querySelector('form');
    if (form) form.addEventListener('submit', validarResenia);
});