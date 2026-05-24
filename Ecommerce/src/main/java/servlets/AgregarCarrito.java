package servlets;

import bos.ProductoBO;
import dtos.ProductoDTO;
import dtos.DisponibilidadDTO;
import interfaces.IProductosBO;
import java.util.List;
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

            // verificamos si el producto tiene stock disponible
            IProductosBO productosBO = new ProductoBO();
            List<ProductoDTO> todos = productosBO.obtenerProductos();
            ProductoDTO productoAgregar = null;
            for (ProductoDTO p : todos) {
                if (p.getId().equals(idProducto)) {
                    productoAgregar = p;
                    break;
                }
            }

            // si no hay stock o esta marcado como no disponible, regresamos al catalogo con mensaje
            if (productoAgregar == null
                    || productoAgregar.getStock() == null
                    || productoAgregar.getStock() <= 0
                    || DisponibilidadDTO.NO_DISPONIBLE.equals(productoAgregar.getDisponibilidad())) {
                request.setAttribute("mensajeStock", "Lo sentimos, este producto está agotado.");
                request.setAttribute("listaProductos", todos);
                request.getRequestDispatcher("/Catalogo.jsp").forward(request, response);
                return;
            }

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
