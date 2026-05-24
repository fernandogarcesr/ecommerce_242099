/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import bos.UsuariosBO;
import dtos.UsuarioDTO;
import exception.AdministrarUsuarioException;
import interfaces.IUsuariosBO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Fernando Garces
 */
@WebServlet(name = "AdministrarUsuarios", urlPatterns = {"/administrar-usuarios"})
public class AdministrarUsuarios extends HttpServlet {

    IUsuariosBO usuariosBO = new UsuariosBO();

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
            out.println("<title>Servlet AdministrarUsuarios</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdministrarUsuarios at " + request.getContextPath() + "</h1>");
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
            List<UsuarioDTO> listaUsuarios = usuariosBO.mostrarUsuarios();

            request.setAttribute("usuarios", listaUsuarios);
            request.getRequestDispatcher("/AdminUsuarios.jsp").forward(request, response);
        } catch (AdministrarUsuarioException e) {
            e.printStackTrace();
            request.setAttribute("usuarios", new java.util.ArrayList<>());
            request.setAttribute("mensajeError", "Error al cargar usuarios: " + e.getMessage());
            request.getRequestDispatcher("/AdminUsuarios.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("usuarios", new java.util.ArrayList<>());
            request.setAttribute("mensajeError", "Error del sistema: " + e.getMessage());
            request.getRequestDispatcher("/AdminUsuarios.jsp").forward(request, response);
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
        String accion = request.getParameter("accion");
        String idStr = request.getParameter("idUsuario");

        try {
            Long id = Long.parseLong(idStr);

            // no permitir eliminar ni desactivar al admin
            dtos.UsuarioDTO usuarioSesion = (dtos.UsuarioDTO) request.getSession(false).getAttribute("usuarioActual");
            if (id.equals(usuarioSesion.getId())) {
                request.setAttribute("mensajeError", "No puedes modificar ni eliminar tu propia cuenta de administrador.");
                doGet(request, response);
                return;
            }

            if ("activar".equals(accion)) {
                usuariosBO.activarUsuario(id);
            } else if ("desactivar".equals(accion)) {
                usuariosBO.desactivarUsuario(id);
            } else if ("eliminar".equals(accion)) {
                usuariosBO.eliminarUsuario(id);
            }
            response.sendRedirect(request.getContextPath() + "/administrar-usuarios");

        } catch (AdministrarUsuarioException e) {
            request.setAttribute("MensajeError", e.getMessage());
            doGet(request, response);
        } catch (Exception e) {
            request.setAttribute("mensajeError", "Error inesperado: " + e.getMessage());
            doGet(request, response);
        }

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
