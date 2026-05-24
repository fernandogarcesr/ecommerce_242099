
package filtros;

import io.jsonwebtoken.Claims;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JWTUtil;
import java.io.IOException;

/**
 * Filtro JWT que valida el token en cada peticion.
 * Si el token es valido, sincroniza los datos con la sesion HTTP.
 * Permite que el backend opere de forma stateless validando JWT.
 *
 * @author Fernando Garces
 */
@WebFilter(urlPatterns = "/*")
public class FiltroJWT implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc)
            throws IOException, ServletException {

        HttpServletRequest  request  = (HttpServletRequest)  sr;
        HttpServletResponse response = (HttpServletResponse) sr1;

        String uri = request.getRequestURI();

        // No procesar recursos estaticos
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png")
                || uri.endsWith(".jpg") || uri.contains("/imgs/")) {
            fc.doFilter(sr, sr1);
            return;
        }

        // Extraer y validar el JWT
        String token  = JWTUtil.extraerToken(request);
        Claims claims = (token != null) ? JWTUtil.validarToken(token) : null;

        HttpSession session = request.getSession(false);

        if (claims != null) {
            // Token valido: sincronizar datos con la sesion si hace falta
            if (session == null || session.getAttribute("usuarioActual") == null) {
                // El token existe y es valido aunque no haya sesion activa
                // Inyectar el rol en el request para que los JSPs puedan usarlo
                request.setAttribute("jwtRol",     claims.get("rol"));
                request.setAttribute("jwtCorreo",  claims.getSubject());
                request.setAttribute("jwtIdUsuario", JWTUtil.getIdUsuario(token));
            }
        }

        // Continuar la cadena (el FiltroAutorizacion original sigue activo)
        fc.doFilter(sr, sr1);
    }

    @Override
    public void destroy() {}
}