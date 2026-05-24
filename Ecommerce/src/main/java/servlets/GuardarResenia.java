
package servlets;

import bos.ReseniasBO;
import dtos.ReseñaDTO;
import dtos.UsuarioDTO;
import exception.ReseniaException;
import interfaces.IReseniasBO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * @author Fernando Garces Rodriguez - 242099
 * Descripcion: Recibe el formulario de CrearResenia.jsp,
 * invoca reseniasBO.agregarResenia() y redirige al catalogo.
 */
@WebServlet(name = "GuardarResenia", urlPatterns = {"/guardarResenia"})
public class GuardarResenia extends HttpServlet {

    private final IReseniasBO reseniasBO = new ReseniasBO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        UsuarioDTO usuario = (session != null) ? (UsuarioDTO) session.getAttribute("usuarioActual") : null;

        // Si no hay sesion, mandamos al login
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        String idProductoStr = request.getParameter("idProducto");
        String comentario = request.getParameter("comentario");
        String calificacionStr = request.getParameter("calificacion");

        try {
            Long idProducto = Long.parseLong(idProductoStr);
            Integer estrellas = Integer.parseInt(calificacionStr);

            // Construir el DTO con los datos del formulario
            ReseñaDTO dto = new ReseñaDTO();
            dto.setComentario(comentario);
            dto.setEstrellas(estrellas);
            dto.setFecha(new java.util.Date());
            dto.setIdProducto(idProducto);
            dto.setUsuario(usuario);

            // Guardar en la BD usando la capa de negocio
            reseniasBO.agregarResenia(idProducto, usuario.getId(), dto);

            // Redirigir al detalle del producto para que vea su resena publicada
            response.sendRedirect(request.getContextPath() + "/cargarproducto?id=" + idProducto);

        } catch (ReseniaException e) {
            request.setAttribute("error", "No se pudo guardar la reseña: " + e.getMessage());
            request.getRequestDispatcher("/CrearResenia.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("/CrearResenia.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/cargarproducto");
    }
}