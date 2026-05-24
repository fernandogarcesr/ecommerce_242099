// pago.js — SportsZone
// Validacion del formulario de pago y manejo dinamico de campos por metodo
 
// Funcion flecha para mostrar/ocultar secciones de pago según el metodo elegido
const toggleCamposPago = (metodo) => {
    const secTarjeta      = document.getElementById('sec-tarjeta');
    const secTransferencia = document.getElementById('sec-transferencia');
    const secContraEntrega = document.getElementById('sec-contra-entrega');
 
    if (secTarjeta)       secTarjeta.style.display       = 'none';
    if (secTransferencia) secTransferencia.style.display = 'none';
    if (secContraEntrega) secContraEntrega.style.display = 'none';
 
    if (metodo === 'TARJETA'        && secTarjeta)       secTarjeta.style.display       = 'block';
    if (metodo === 'TRANSFERENCIA'  && secTransferencia) secTransferencia.style.display = 'block';
    if (metodo === 'CONTRA_ENTREGA' && secContraEntrega) secContraEntrega.style.display = 'block';
};
 
// Validar numero de tarjeta (solo dígitos, 16 caracteres)
const validarTarjeta = (numero) => /^\d{16}$/.test(numero.replace(/\s/g, ''));
 
// Validar fecha de expiración MM/YY
const validarExpiracion = (fecha) => {
    if (!/^\d{2}\/\d{2}$/.test(fecha)) return false;
    const [mes, anio] = fecha.split('/').map(Number);
    if (mes < 1 || mes > 12) return false;
    const ahora = new Date();
    const expDate = new Date(2000 + anio, mes - 1);
    return expDate >= ahora;
};
 
// Validar CVV (3 o 4 dígitos)
const validarCVV = (cvv) => /^\d{3,4}$/.test(cvv);
 
// Funcion asíncrona para simular validacion del pago (async/await + Promise)
const simularValidacionPago = (metodo) => {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            // Simulacion: siempre válido para metodos no-tarjeta
            if (metodo !== 'TARJETA') {
                resolve({ valido: true });
            } else {
                resolve({ valido: true }); // En produccion real iría a un endpoint
            }
        }, 500);
    });
};
 
// Validar y enviar el formulario
const validarFormularioPago = async (e) => {
    e.preventDefault();
    const form   = e.target;
    const metodo = document.querySelector('input[name="metodoPago"]:checked')?.value || '';
 
    let errores = [];
 
    if (metodo === 'TARJETA') {
        const numTarjeta = document.getElementById('num-tarjeta')?.value || '';
        const expiracion = document.getElementById('expiracion')?.value || '';
        const cvv        = document.getElementById('cvv')?.value || '';
        const titular    = document.getElementById('titular')?.value.trim() || '';
 
        if (!titular)                      errores.push('El nombre del titular es requerido.');
        if (!validarTarjeta(numTarjeta))   errores.push('El número de tarjeta debe tener 16 dígitos.');
        if (!validarExpiracion(expiracion)) errores.push('La fecha de expiración no es válida o ya venció.');
        if (!validarCVV(cvv))              errores.push('El CVV debe tener 3 o 4 dígitos.');
    }
 
    const contenedorError = document.getElementById('errores-pago');
    if (errores.length > 0) {
        if (contenedorError) {
            contenedorError.innerHTML = errores.map(err => `<p>⚠ ${err}</p>`).join('');
            contenedorError.style.display = 'block';
        }
        return;
    }
 
    if (contenedorError) contenedorError.style.display = 'none';
 
    // Boton de carga mientras se procesa
    const btnConfirmar = document.getElementById('btn-confirmar-pago');
    if (btnConfirmar) {
        btnConfirmar.textContent = 'Procesando...';
        btnConfirmar.disabled = true;
    }
 
    try {
        await simularValidacionPago(metodo);
        form.submit(); // Enviar despues de validacion exitosa
    } catch (err) {
        if (btnConfirmar) {
            btnConfirmar.textContent = 'Confirmar pedido';
            btnConfirmar.disabled = false;
        }
        if (contenedorError) {
            contenedorError.innerHTML = '<p>X Error al procesar el pago. Intenta de nuevo.</p>';
            contenedorError.style.display = 'block';
        }
    }
};
 
// Formatear numero de tarjeta en grupos de 4
const formatearNumeroTarjeta = (e) => {
    let val = e.target.value.replace(/\D/g, '').substring(0, 16);
    e.target.value = val.replace(/(.{4})/g, '$1 ').trim();
};
 
// Formatear expiracion como MM/YY
const formatearExpiracion = (e) => {
    let val = e.target.value.replace(/\D/g, '').substring(0, 4);
    if (val.length >= 3) val = val.substring(0, 2) + '/' + val.substring(2);
    e.target.value = val;
};
 
document.addEventListener('DOMContentLoaded', () => {
 
    // Enlazar cambio de metodo de pago
    document.querySelectorAll('input[name="metodoPago"]').forEach(radio => {
        radio.addEventListener('change', (e) => toggleCamposPago(e.target.value));
    });
 
    // Mostrar seccion correcta al cargar segun el radio seleccionado por defecto
    const metodoInicial = document.querySelector('input[name="metodoPago"]:checked')?.value;
    if (metodoInicial) toggleCamposPago(metodoInicial);
 
    // Formateo dinamico de campos de tarjeta
    const numTarjetaInput = document.getElementById('num-tarjeta');
    const expiracionInput = document.getElementById('expiracion');
    if (numTarjetaInput) numTarjetaInput.addEventListener('input', formatearNumeroTarjeta);
    if (expiracionInput)  expiracionInput.addEventListener('input', formatearExpiracion);
 
    // Enlazar validacion al form de pago
    const formPago = document.getElementById('form-pago');
    if (formPago) {
        formPago.addEventListener('submit', validarFormularioPago);
    }
});