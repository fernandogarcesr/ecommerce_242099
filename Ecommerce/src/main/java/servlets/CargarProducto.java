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
                    request.getRequestDispatcher("/DetallesProducto.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/cargarproducto");
                }
                return;
            }

            // Cargar el inventario completo para Catálogo o Admin
            List<ProductoDTO> productos = productosBO.obtenerProductos();

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
