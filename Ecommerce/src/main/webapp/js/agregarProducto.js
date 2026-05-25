// Validacion del formulario para agregar producto nuevo

document.addEventListener('DOMContentLoaded', () => {

    document.title = 'Agregar Producto - SportsZone';

    // preview de la imagen seleccionada antes de subir
    const inputImagen = document.getElementById('imagen');
    if (inputImagen) {
        inputImagen.addEventListener('change', (e) => {
            const archivo = e.target.files[0];
            if (!archivo) return;

            let preview = document.getElementById('preview-imagen');
            if (!preview) {
                preview = document.createElement('img');
                preview.id = 'preview-imagen';
                preview.style.cssText = 'max-width:160px;max-height:160px;object-fit:contain;border:1px solid #ddd;border-radius:6px;margin-top:8px;display:block;';
                inputImagen.insertAdjacentElement('afterend', preview);
            }
            preview.src = URL.createObjectURL(archivo);
        });
    }

    // validacion del formulario
    const validarFormulario = (e) => {
        const nombre = document.getElementById('nombre')?.value.trim() || '';
        const precio = document.getElementById('precio')?.value || '';
        const stock = document.getElementById('stock')?.value || '';

        if (!nombre) {
            e.preventDefault();
            alert('El nombre del producto es obligatorio.');
            return;
        }
        if (!precio || parseFloat(precio) <= 0) {
            e.preventDefault();
            alert('El precio debe ser mayor a 0.');
            return;
        }
        if (stock === '' || parseInt(stock) < 0) {
            e.preventDefault();
            alert('El stock no puede ser negativo.');
        }
    };

    const form = document.querySelector('form');
    if (form) form.addEventListener('submit', validarFormulario);
});