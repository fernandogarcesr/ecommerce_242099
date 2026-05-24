
package utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

/**
 * Utilidad para generar y validar tokens JWT.
 * Permite que el backend sea stateless..
 *
 * @author Fernando Garces 
 */
public class JWTUtil {
    
     // Clave secreta para firmar los tokens (mínimo 256 bits para HS256)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Duracion del token: 8 horas en milisegundos
    private static final long EXPIRACION_MS = 8 * 60 * 60 * 1000L;

    /**
     * Genera un token JWT con los datos del usuario autenticado.
     *
     * @param idUsuario  ID del usuario
     * @param correo     Correo del usuario
     * @param rol        Rol del usuario (CLIENTE o ADMINISTRADOR)
     * @return Token JWT firmado
     */
    public static String generarToken(Long idUsuario, String correo, String rol) {
        Date ahora     = new Date();
        Date expiracion = new Date(ahora.getTime() + EXPIRACION_MS);

        return Jwts.builder()
                .setSubject(correo)
                .claim("idUsuario", idUsuario)
                .claim("rol", rol)
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * Valida un token JWT y devuelve sus claims si es valido.
     *
     * @param token El token JWT a validar
     * @return Claims del token, o null si es invalido o expiró
     */
    public static Claims validarToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Extrae el correo (subject) de un token valido.
     */
    public static String getCorreo(String token) {
        Claims claims = validarToken(token);
        return (claims != null) ? claims.getSubject() : null;
    }

    /**
     * Extrae el rol de un token valido.
     */
    public static String getRol(String token) {
        Claims claims = validarToken(token);
        return (claims != null) ? (String) claims.get("rol") : null;
    }

    /**
     * Extrae el ID del usuario de un token valido.
     */
    public static Long getIdUsuario(String token) {
        Claims claims = validarToken(token);
        if (claims == null) return null;
        Object id = claims.get("idUsuario");
        return (id instanceof Integer) ? ((Integer) id).longValue() : (Long) id;
    }

    /**
     * Extrae el token del header Authorization: Bearer <token>
     * o de una cookie llamada "jwt_token".
     */
    public static String extraerToken(jakarta.servlet.http.HttpServletRequest request) {
        // Intentar desde header Authorization
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        // Intentar desde cookie
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (jakarta.servlet.http.Cookie c : cookies) {
                if ("jwt_token".equals(c.getName())) {
                    return c.getValue();
                }
            }
        }
        return null;
    }
}
