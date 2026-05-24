package servlets;

import bos.CarritosBO;
import dtos.CarritoDTO;
import dtos.UsuarioDTO;
import exception.CarritoException;
import interfaces.ICarritosBO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * @author Fernando Garces Rodriguez - 242099 Descripcion: Elimina un producto
 * del carrito del usuario usando carritosBO.eliminarProducto() y recarga el
 * carrito.
 */
@WebServlet(name = "QuitarCarrito", urlPatterns = {"/quitarCarrito"})
public class QuitarCarrito extends HttpServlet {

    private final ICarritosBO carritosBO = new CarritosBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        UsuarioDTO usuario = (session != null) ? (UsuarioDTO) session.getAttribute("usuarioActual") : null;

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        String idProductoStr = request.getParameter("id");
        String idCarritoStr = request.getParameter("carritoId");

        try {
            Long idProducto = Long.parseLong(idProductoStr);
            Long idCarrito = Long.parseLong(idCarritoStr);

            // Eliminar el producto del carrito en la BD
            CarritoDTO carritoActualizado = carritosBO.eliminarProducto(idProducto, idCarrito);

            // Actualizar la sesion con el carrito nuevo
            session.setAttribute("carritoActual", carritoActualizado);

            response.sendRedirect(request.getContextPath() + "/CargarCarrito");

        } catch (CarritoException e) {
            response.sendRedirect(request.getContextPath() + "/CargarCarrito");
        }
    }
}
