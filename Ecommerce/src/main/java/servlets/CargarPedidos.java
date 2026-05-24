/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import bos.PedidosBO;
import dtos.PedidoDTO;
import dtos.UsuarioDTO;
import exception.ObtenerPedidoException;
import interfaces.IPedidosBO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Fernando Garces
 */
@WebServlet(name = "CargarPedidos", urlPatterns = {"/cargarpedidos"})
public class CargarPedidos extends HttpServlet {

    IPedidosBO pedidosBO = new PedidosBO();

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
            out.println("<title>Servlet CargarPedidos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CargarPedidos at " + request.getContextPath() + "</h1>");
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
        try {
            HttpSession session = request.getSession(false);
        UsuarioDTO usuario = (session != null) ? (UsuarioDTO) session.getAttribute("usuarioActual") : null;
        String rol = (session != null) ? (String) session.getAttribute("rol") : null;

        if ("ADMINISTRADOR".equals(rol)) {
            // Admin ve todos los pedidos
            List<PedidoDTO> pedidos = pedidosBO.obtenerTodosPedidos();
            request.setAttribute("listaPedidos", pedidos);
            request.getRequestDispatcher("/AdminGestionPedidos.jsp").forward(request, response);
        } else {
            // Cliente ve solo sus pedidos
            if (usuario == null) {
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
                return;
            }
            List<PedidoDTO> pedidos = pedidosBO.obtenerPedidosPorUsuario(usuario.getId());
            request.setAttribute("listaPedidos", pedidos);
            request.getRequestDispatcher("/Pedidos.jsp").forward(request, response);
        }
        } catch (ObtenerPedidoException ex) {
            request.setAttribute("mensaje", "Error: " + ex.getMessage());
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
