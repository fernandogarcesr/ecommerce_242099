
package servlets;

import bos.CarritosBO;
import bos.PedidosBO;
import dtos.CarritoDTO;
import dtos.PedidoDTO;
import dtos.UsuarioDTO;
import exception.AgregarPedidoException;
import interfaces.ICarritosBO;
import interfaces.IPedidosBO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * @author  Fernando Garces Rodriguez - 242099
 * Descripcion: Procesa el checkout. Lee el carrito de sesion,
 * llama a pedidosBO.crearPedido(), vacia el carrito y muestra
 * PagoConfirmado.jsp con los datos del pedido generado.
 */
@WebServlet(name = "FinalizarPedido", urlPatterns = {"/finalizarPedido"})
public class FinalizarPedido extends HttpServlet {

    private final IPedidosBO pedidosBO = new PedidosBO();
    private final ICarritosBO carritosBO = new CarritosBO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        UsuarioDTO usuario = (session != null) ? (UsuarioDTO) session.getAttribute("usuarioActual") : null;

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        String tipoPago = request.getParameter("metodoPago");
         if (tipoPago == null || tipoPago.trim().isEmpty()) {
            tipoPago = "TARJETA";
        }

        CarritoDTO carrito = (CarritoDTO) session.getAttribute("carritoActual");
        if (carrito == null || carrito.getDetallesCarrito() == null || carrito.getDetallesCarrito().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/CargarCarrito");
            return;
        }

        try {
            // Crear el pedido en la BD usando el metodo del BO
            PedidoDTO pedidoCreado = pedidosBO.crearPedido(
                usuario.getId(),
                tipoPago,
                    usuario.getDireccion()
            );

            // Vaciar el carrito despues de confirmar el pedido
            new implementaciones.CarritosDAO().limpiarCarrito(carrito.getId());

            // Limpiar el carrito de la sesion
            session.removeAttribute("carritoActual");

            // Pasar el pedido a la vista de confirmacion
            session.setAttribute("pedidoConfirmado", pedidoCreado);
            // Enviar correo de confirmación
            try {
                enviarCorreoConfirmacion(usuario.getCorreo(), usuario.getNombre(), pedidoCreado.getId().toString());
            } catch (Exception mailEx) {
                System.err.println("Aviso: no se pudo enviar el correo: " + mailEx.getMessage());
            }
            response.sendRedirect(request.getContextPath() + "/PagoConfirmado.jsp");

        } catch (AgregarPedidoException e) {
            request.setAttribute("error", "No se pudo procesar el pedido: " + e.getMessage());
            request.getRequestDispatcher("/ProcesoPago.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("/ProcesoPago.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/CargarCarrito");
    }

    private void enviarCorreoConfirmacion(String destinatario, String nombre, String numeroPedido) throws Exception {
        String host = "smtp.gmail.com"; 
        String puerto = "587";
        String usuario = "fernandogarcesrodriguez@gmail.com";   
        String clave = "yqbs zymw ibra tbas";

        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", puerto);

        jakarta.mail.Session mailSession = jakarta.mail.Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(usuario, clave);
            }
        });

        jakarta.mail.Message mensaje = new jakarta.mail.internet.MimeMessage(mailSession);
        mensaje.setFrom(new jakarta.mail.internet.InternetAddress("fernandogarcesrodriguez@gmail.com"));
        mensaje.setRecipients(jakarta.mail.Message.RecipientType.TO,
                jakarta.mail.internet.InternetAddress.parse(destinatario));
        mensaje.setSubject("Pedido confirmado – SportsZone #" + numeroPedido);
        mensaje.setText("Hola " + nombre + ",\n\nTu pedido #" + numeroPedido
                + " ha sido confirmado exitosamente.\n\nGracias por comprar en SportsZone.");

        jakarta.mail.Transport.send(mensaje);
    }
}
