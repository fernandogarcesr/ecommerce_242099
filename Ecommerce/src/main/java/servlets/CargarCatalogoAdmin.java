
package servlets;

import bos.ProductoBO;
import dtos.ProductoDTO;
import interfaces.IProductosBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author Fernando garces
 */
@WebServlet("/CargarCatalogoAdmin")
public class CargarCatalogoAdmin extends HttpServlet {

    private final IProductosBO productoBO = new ProductoBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Buscamos los productos en la BD
            List<ProductoDTO> listaProductos = productoBO.obtenerProductos();
            
            // Los guardamos en el request
            request.setAttribute("productos", listaProductos);
            
            // Redirigimos la peticion al JSP del administrador
            request.getRequestDispatcher("AdminCatalogo.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error al cargar el inventario: " + e.getMessage());
            request.getRequestDispatcher("AdminPrincipal.jsp").forward(request, response);
        }
    }
}