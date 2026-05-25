package servlets;

import bos.ProductoBO;
import bos.ReseniasBO;
import dtos.ProductoDTO;
import dtos.ReseñaDTO;
import exception.ObtenerProductosException;
import interfaces.IProductosBO;
import interfaces.IReseniasBO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando Garces
 */
@WebServlet(name = "CargarProducto", urlPatterns = {"/cargarproducto"})
public class CargarProducto extends HttpServlet {

    IProductosBO productosBO = new ProductoBO();
    private final IReseniasBO reseniasBO = new ReseniasBO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String vista = request.getParameter("vista");

        // Parametros del filtro que viene del aside-filtro.jspf
        String txtProducto = request.getParameter("txt_productos");
        String txtPrecio = request.getParameter("txt_precio");
        String orden = request.getParameter("orden");
        String soloDisponibles = request.getParameter("soloDisponibles");

        try {
            // Si viene un id, cargamos ese producto específico
            if (idParam != null && !idParam.isEmpty()) {
                Long id = Long.parseLong(idParam);
                // buscamos en la lista completa el DTO correspondiente
                List<ProductoDTO> productos = productosBO.obtenerProductos();
                ProductoDTO productoSeleccionado = null;
                for (ProductoDTO p : productos) {
                    if (p.getId().equals(id)) {
                        productoSeleccionado = p;
                        break;
                    }
                }

                if (productoSeleccionado != null) {
                    request.setAttribute("producto", productoSeleccionado);

                    //filtrar reseñas
                    List<ReseñaDTO> reseniasFiltradas = new ArrayList<>();

                    try {
                        List<ReseñaDTO> todasLasResenias = reseniasBO.obtenerResenias();

                        // Filtramos las que pertenencen a este producto
                        for (ReseñaDTO r : todasLasResenias) {
                            if (r.getIdProducto() == id.longValue()) {
                                reseniasFiltradas.add(r);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error o no hay reseñas en la BD: " + e.getMessage());
                    }
                    request.setAttribute("listaResenias", reseniasFiltradas);
                    if ("resenia".equals(vista)) {
                        request.getRequestDispatcher("/CrearResenia.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/DetallesProducto.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/cargarproducto");
                }
                return;
            }
            // Filtros
            String nombre = (txtProducto != null && !txtProducto.trim().isEmpty())
                    ? txtProducto.trim() : null;

            Double precioMin = null;
            Double precioMax = null;

            // Si orden es null o vacío, default a "Menor"
            if (orden == null || orden.trim().isEmpty()) {
                orden = "Menor";
            }

            if (txtPrecio != null && !txtPrecio.trim().isEmpty()) {
                try {
                    double precioBase = Double.parseDouble(txtPrecio.trim());
                    if ("Mayor".equals(orden)) {
                        precioMin = precioBase;   // mayores al precio → precio mínimo
                    } else {
                        precioMax = precioBase;   // menores al precio → precio máximo
                    }
                } catch (NumberFormatException e) {
                    // precio inválido, ignorar filtro
                }
            }

            // Cargar el inventario completo para Catalogo o Admin
            List<ProductoDTO> productos;
            if (nombre != null || precioMin != null || precioMax != null) {
                productos = productosBO.buscarProductos(nombre, precioMin, precioMax);
            } else {
                productos = productosBO.obtenerProductos();
            }

            // Filtro de solo disponible
            if ("true".equals(soloDisponibles)) {
                List<ProductoDTO> filtrados = new ArrayList<>();
                for (ProductoDTO p : productos) {
                    if (p.getDisponibilidad() != null
                            && "DISPONIBLE".equals(p.getDisponibilidad().name())) {
                        filtrados.add(p);
                    }
                }
                productos = filtrados;
            }

            request.setAttribute("txtProducto", txtProducto);
            request.setAttribute("txtPrecio", txtPrecio);
            request.setAttribute("orden", orden);
            request.setAttribute("soloDisponibles", soloDisponibles);

            if ("adminProducto".equals(vista)) {
                request.setAttribute("listaProductos", productos);
                request.getRequestDispatcher("/AdminCatalogo.jsp").forward(request, response);
            } else {
                request.setAttribute("listaProductos", productos); // Va al Catalogo de clientes con 'listaProductos'
                request.getRequestDispatcher("/Catalogo.jsp").forward(request, response);
            }

        } catch (ObtenerProductosException ex) {
            request.setAttribute("mensaje", "Error al cargar productos " + ex.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
