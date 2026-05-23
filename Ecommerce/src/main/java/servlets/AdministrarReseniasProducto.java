/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import bos.ProductoBO;
import dtos.ProductoDTO;
import interfaces.IProductosBO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando Garces
 */
@WebServlet(name = "administrar-resenias-producto", urlPatterns = {"/administrarReseniasProducto"})
public class AdministrarReseniasProducto extends HttpServlet {

    IProductosBO productosBO = new ProductoBO();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdministrarReseniasProducto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdministrarReseniasProducto at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String idProductoStr = request.getParameter("idProducto");
        Long idProducto = null;
        
        try {
            if (idProductoStr == null || idProductoStr.isEmpty()) {
                throw new Exception("ID de producto faltante.");
            }
            idProducto = Long.parseLong(idProductoStr);

            ProductoDTO productoConResenias = productosBO.obtenerProductoPorId(idProducto);
            
            if (productoConResenias == null) {
                throw new Exception("Producto no encontrado.");
            }
            
            request.setAttribute("productoSeleccionado", productoConResenias);
            
            request.getRequestDispatcher("/administrarReseniaProducto.jsp").forward(request, response);
            
        } catch (NumberFormatException ex) {

            request.setAttribute("error", "ID de producto inválido.");
            request.getRequestDispatcher("/menuadministrador.jsp").forward(request, response);
        } catch (Exception ex) {

            System.err.println("Error al obtener reseñas del producto: " + ex.getMessage());
            request.setAttribute("error", "No se pudieron obtener las reseñas: " + ex.getMessage());
            request.getRequestDispatcher("/menuadministrador.jsp").forward(request, response);
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
        processRequest(request, response);
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
