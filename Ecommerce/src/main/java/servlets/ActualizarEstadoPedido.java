    
package servlets;

import bos.PedidosBO;
import dtos.EstadoPedidoDTO;
import dtos.PedidoDTO;
import exception.CambiarEstadoException;
import exception.ObtenerPedidoException;
import interfaces.IPedidosBO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *Servlet used to modify an order and it´s status
 * @author Fernando Garces
 */
@WebServlet(name = "ActualizarEstadoPedido", urlPatterns = {"/modificar_pedido"})
public class ActualizarEstadoPedido extends HttpServlet {

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
            out.println("<title>Servlet ActualizarEstadoPedido</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActualizarEstadoPedido at " + request.getContextPath() + "</h1>");
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
        String idPedidoStr = request.getParameter("idPedido");
        
        if (idPedidoStr == null || idPedidoStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de pedido faltante.");
            return;
        }
        
        try {
            Long idPedido = Long.parseLong(idPedidoStr);

            PedidoDTO pedido = pedidosBO.obtenerPedidoIndividual(idPedido);

            if (pedido == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Pedido no encontrado.");
                return;
            }
            
            request.setAttribute("pedidoIndividual", pedido);
            
            request.getRequestDispatcher("/administrarPedidoIndividual.jsp").forward(request, response);
            
        } catch (NumberFormatException ex) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de pedido inválido.");
        } catch (ObtenerPedidoException ex) {
            request.setAttribute("error", "Error al cargar el pedido: " + ex.getMessage());
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
        String idPedidoStr = request.getParameter("idPedido"); // Debe ser un campo oculto en el formulario
        String estadoStr = request.getParameter("estado-pedido");

        try {
            if (idPedidoStr == null || estadoStr == null) {
                throw new Exception("Datos faltantes en el formulario.");
            }

            Long idPedido = Long.parseLong(idPedidoStr);

            EstadoPedidoDTO nuevoEstado = EstadoPedidoDTO.valueOf(estadoStr);

            pedidosBO.cambiarEstadoPedido(idPedido, nuevoEstado);

            //NO SE SI LLAMA AL SERVLET O JSP??
            response.sendRedirect(request.getContextPath() + "/cargarpedidos"); 

        } catch (NumberFormatException ex) {
            request.setAttribute("error", "ID o formato de estado inválido.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (CambiarEstadoException ex) {
            request.setAttribute("error", "Error al actualizar el estado: " + ex.getMessage());
            request.getRequestDispatcher("/menuadministrador.jsp").forward(request, response); 
        } catch (Exception ex) {
            request.setAttribute("error", "Error inesperado: " + ex.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
