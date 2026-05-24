package servlets;

import bos.UsuariosBO;
import dtos.UsuarioDTO;
import interfaces.IUsuariosBO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Controlador encargado de procesar la autenticacion de usuarios. Valida
 * credenciales y distribuye el flujo según el rol asignado.
 *
 * @author Fernando Garces
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    // Capa de negocio para la gestión de cuentas
    private final IUsuariosBO usuariosBO = new UsuariosBO();

    /**
     * Manda al usuario directo a la pantalla visual de Login si intenta acceder
     * por GET.
     *
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/Login.jsp");
    }

    /**
     * Recibe los datos del formulario deportivo y valida el acceso.
     *
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Captura de los parámetros obligatorios del formulario
        request.setCharacterEncoding("UTF-8");
        String correo = request.getParameter("correo");
        if (correo == null) correo = "";
        correo = correo.trim().toLowerCase();
        
        String contraseniaPlana = request.getParameter("contrasenia");
        if (contraseniaPlana == null) contraseniaPlana = "";

        try {

            // Mandamos el Hash resultante a validar contra la base de datos
            UsuarioDTO usuario = usuariosBO.iniciarSesion(correo, contraseniaPlana);

            // Si los datos estan mal, regresamos al login con un mensaje de advertencia
            if (usuario == null) {
                request.setAttribute("mensaje", "El correo electrónico o la contraseña son incorrectos.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                return;
            }

            // Validamos si la cuenta del cliente se encuentra activa en la tienda
            if (usuario.getEsActivo() != null && !usuario.getEsActivo()) {
                request.setAttribute("mensaje", "Esta cuenta se encuentra temporalmente suspendida.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                return;
            }

            // Creamos o recuperamos la sesion HTTP del servidor
            HttpSession session = request.getSession(true);
            session.setAttribute("usuarioActual", usuario);
            session.setAttribute("rol", usuario.getRol().name());

            // Generar JWT y guardarlo en cookie HttpOnly
            String token = utils.JWTUtil.generarToken(
                    usuario.getId(),
                    usuario.getCorreo(),
                    usuario.getRol().name()
            );
            jakarta.servlet.http.Cookie jwtCookie = new jakarta.servlet.http.Cookie("jwt_token", token);
            jwtCookie.setHttpOnly(true); 
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(8 * 60 * 60); // 8 horas
            response.addCookie(jwtCookie);
            session.setAttribute("jwtToken", token);

            // Redireccion estrategica dependiendo del nivel de acceso (Admin o Cliente)
            if ("ADMINISTRADOR".equals(usuario.getRol().name())) {
                response.sendRedirect(request.getContextPath() + "/AdminPrincipal.jsp");
                return;
            }

            if ("CLIENTE".equals(usuario.getRol().name())) {
                response.sendRedirect(request.getContextPath() + "/Index.jsp");
                return;
            }

        } catch (Exception e) {
            // Manejo de errores imprevistos en los servidores o base de datos
            request.setAttribute("mensaje", "Fallo interno en el sistema de SportsZone: " + e.getMessage());
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador de Autenticacion - SportsZone";
    }
}
