
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
 * @author Fernando Garces Rodriguez - 242099
 * Descripcion: Carga el carrito del usuario desde la BD,
 * lo guarda en sesion y muestra Carrito.jsp con datos reales.
 */
@WebServlet(name = "CargarCarrito", urlPatterns = {"/CargarCarrito"})
public class CargarCarrito extends HttpServlet {

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

        try {
            // Obtener el carrito actualizado desde la BD
            CarritoDTO carrito = carritosBO.obtenerCarritoUsuario(usuario.getId());

            // Guardarlo en sesion para que ProcesoPago.jsp lo lea
            session.setAttribute("carritoActual", carrito);
            request.setAttribute("carritoActual", carrito);

            request.getRequestDispatcher("/Carrito.jsp").forward(request, response);

        } catch (CarritoException e) {
            request.setAttribute("error", "Error al cargar carrito: " + e.getMessage());
            request.getRequestDispatcher("/Carrito.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
