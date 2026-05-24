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
import javax.swing.tree.ExpandVetoException;

/**
 * AgregarCarrito.java
 *
 * @author Fernando Garces Rodriguez - 242099 Descripcion: Recibe el id del
 * producto, llama a carritosBO.agregarProducto(), actualiza el carritoActual en
 * sesion y redirige a Carrito.jsp.
 */
@WebServlet(name = "AgregarCarrito", urlPatterns = {"/agregarCarrito"})
public class AgregarCarrito extends HttpServlet {

    private final ICarritosBO carritosBO = new CarritosBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        UsuarioDTO usuario = (session != null) ? (UsuarioDTO) session.getAttribute("usuarioActual") : null;

        // Si no esta logueado, mandamos al login
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cargarproducto");
            return;
        }

        try {
            Long idProducto = Long.parseLong(idStr);

            // Agregar el producto al carrito del usuario en la BD (cantidad 1 por defecto)
            CarritoDTO carritoActualizado = carritosBO.agregarProducto(usuario.getId(), idProducto, 1);

            // Guardar el carrito en sesion para que ProcesoPago.jsp pueda leerlo
            session.setAttribute("carritoActual", carritoActualizado);

            // Redirigir al carrito para que vea lo que acaba de agregar
            response.sendRedirect(request.getContextPath() + "/CargarCarrito");

        } catch (CarritoException e) {
            request.setAttribute("error", "No se pudo agregar el producto: " + e.getMessage());
            request.getRequestDispatcher("/cargarproducto").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("/cargarproducto").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
