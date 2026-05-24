
package servlets;
import bos.UsuariosBO;
import dtos.UsuarioDTO;
import exception.EditarUsuarioException;
import interfaces.IUsuariosBO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * @author  Fernando Garces Rodriguez - 242099
 * Descripcion: Recibe los datos del form de PerfilUsuarios.jsp,
 * llama a usuariosBO.editarUsuario() y actualiza el objeto
 * usuarioActual en sesion para reflejar los cambios en el header.
 */
@WebServlet(name = "EditarPerfil", urlPatterns = {"/editarPerfil"})
public class EditarPerfil extends HttpServlet {

    private final IUsuariosBO usuariosBO = new UsuariosBO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        UsuarioDTO usuarioActual = (session != null) ? (UsuarioDTO) session.getAttribute("usuarioActual") : null;

        if (usuarioActual == null) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        // Capturar los campos del formulario
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        // Actualizar el DTO con los nuevos datos
        usuarioActual.setNombre(nombre);
        usuarioActual.setTelefono(telefono);
        usuarioActual.setDireccion(direccion);

        try {
            // Persistir los cambios en la BD
            UsuarioDTO actualizado = usuariosBO.editarUsuario(usuarioActual);

            // Actualizar el objeto en sesion para que el header muestre el nombre nuevo
            session.setAttribute("usuarioActual", actualizado);

            request.setAttribute("mensajeExito", "Perfil actualizado correctamente.");
            request.getRequestDispatcher("/PerfilUsuarios.jsp").forward(request, response);

        } catch (EditarUsuarioException e) {
            request.setAttribute("error", "No se pudo actualizar: " + e.getMessage());
            request.getRequestDispatcher("/PerfilUsuarios.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/PerfilUsuarios.jsp").forward(request, response);
    }
}
