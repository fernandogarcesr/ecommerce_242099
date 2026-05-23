package servlets;

import entidades.Usuario;
import entidades.RolUsuario;
import implementaciones.UsuariosDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de capturar la información del formulario de registro y dar
 * de alta a los nuevos clientes en la plataforma.
 *
 * @author Fernando garces
 */
@WebServlet(name = "RegistraUsuario", urlPatterns = {"/registraUsuario"})
public class RegistraUsuario extends HttpServlet {

    private final UsuariosDAO usuariosDAO = new UsuariosDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si intentan entrar por URL directa, los mandamos al formulario visual
        response.sendRedirect(request.getContextPath() + "/Register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recoleccion de los parametros del formulario HTML
        request.setCharacterEncoding("UTF-8");
        String nombre = trim(request.getParameter("txt_nombre"));
        String telefono = trim(request.getParameter("txt_telefono"));
        String correo = trim(request.getParameter("txt_correo"));
        String direccion = trim(request.getParameter("txt_direccion"));
        String contrasenia = trim(request.getParameter("txt_password"));
        String confirmar = trim(request.getParameter("txt_password_confirm"));

        try {
            // Validacion de contraseñas identicas
            if (contrasenia == null || !contrasenia.equals(confirmar)) {
                request.setAttribute("error", "Las contraseñas ingresadas no coinciden.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
                return;
            }
            // Nombre sin digitos
            if (nombre.matches(".*\\d.*")) {
                request.setAttribute("error", "El nombre no debe contener números.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
                return;
            }

            // Telefono exactamente 10 digitos
            if (!telefono.matches("\\d{10}")) {
                request.setAttribute("error", "El teléfono debe tener exactamente 10 dígitos numéricos.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
                return;
            }

            // Verificacion de correo duplicado
            if (usuariosDAO.buscarPorCorreo(correo) != null) {
                request.setAttribute("error", "El correo electrónico ya se encuentra registrado.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
                return;
            }

            //Mapeo del objeto Entidad
            Usuario nuevoCliente = new Usuario();
            nuevoCliente.setNombre(nombre);
            nuevoCliente.setTelefono(telefono);
            nuevoCliente.setCorreo(correo);
            nuevoCliente.setDireccion(direccion);
            nuevoCliente.setContrasenia(contrasenia);
            nuevoCliente.setEsActivo(true);
            nuevoCliente.setRol(RolUsuario.CLIENTE);

            usuariosDAO.registrarUsuario(nuevoCliente);

            // Redirigimos al login con mensaje de exito
            request.setAttribute("mensajeExito", "¡Cuenta creada con éxito! Ya puedes ingresar.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Error interno al procesar el registro: " + e.getMessage());
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
    }

    private String trim(String s) {
        return (s == null) ? "" : s.trim();
    }
}
