// Interacciones de la ficha tecnica del producto

document.addEventListener('DOMContentLoaded', () => {

    // funcion flecha para cambiar la imagen principal al hacer click en miniaturas
    // si despues se agrega galeria de imagenes
    const imgPrincipal = document.querySelector('.img-producto-principal');

    // selector de cantidad — actualizar precio mostrado dinamicamente
    const selectCantidad = document.getElementById('cantidad');
    const precioBase = parseFloat(
        document.querySelector('.precio-producto')?.dataset.precio || 0
    );
    const precioDisplay = document.querySelector('.precio-producto');

    if (selectCantidad && precioBase && precioDisplay) {
        selectCantidad.addEventListener('change', () => {
            const cantidad = parseInt(selectCantidad.value) || 1;
            precioDisplay.textContent = '$' + (precioBase * cantidad).toFixed(2);
        });
    }

    // confirmar antes de agregar al carrito si ya hay productos
    const btnAgregar = document.querySelector('a[href*="agregarCarrito"]');
    if (btnAgregar) {
        btnAgregar.addEventListener('click', (e) => {
            const stockEl = document.querySelector('.stock-disponible');
            const stock = parseInt(stockEl?.dataset.stock || 1);
            if (stock <= 0) {
                e.preventDefault();
                alert('Lo sentimos, este producto está agotado.');
            }
        });
    }
});