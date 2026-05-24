
package implementaciones;
import entidades.Usuario;
import exception.PersistenciaException;
import interfaces.IUsuariosDAO;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * DAO que implementa las operaciones de persistencia para la gestión de usuarios
 * utilizando JPA y consultas JPQL limpias.
 * @author Fernando Garces
 */
public class UsuariosDAO implements IUsuariosDAO {

    @Override
    public Usuario iniciarSesion(String correo, String contrasenia) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();

        try {
            String contraseniaEncriptadaIngresada;
            try {
                // Hasheamos la clave ingresada para compararla con el hash almacenado
                contraseniaEncriptadaIngresada = encriptarContrasenia(contrasenia);
            } catch (NoSuchAlgorithmException e) {
                throw new PersistenciaException("Error crítico de seguridad: Algoritmo de hash no soportado.", e);
            }

            // Consulta clásica para buscar al usuario por su identificador único (correo)
            String jpql = "SELECT u FROM Usuario u WHERE u.correo = :correo";
            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            query.setParameter("correo", correo);

            Usuario usuario;
            try {
                usuario = query.getSingleResult();
            } catch (NoResultException e) {
                System.err.println("Log: Intento de acceso fallido. Correo inexistente: " + correo);
                return null;
            }

            // Sanitizamos los hashes quitando espacios en blanco fantasmas
            String hashBDLimpio = usuario.getContrasenia().trim();
            String hashNuevoLimpio = contraseniaEncriptadaIngresada.trim();

            if (hashBDLimpio.equals(hashNuevoLimpio)) {
                System.out.println("Log: Autenticación exitosa en el sistema para: " + correo);
                return usuario;
            } else {
                System.err.println("Log: Contraseña incorrecta para el usuario de la tienda: " + correo);
                return null;
            }

        } catch (PersistenciaException e) {
            throw e;
        } catch (Exception e) {
            throw new PersistenciaException("Fallo general en la persistencia durante el login: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Usuario registrarUsuario(Usuario usuarioNuevo) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

    try {
        entityManager.getTransaction().begin();

        // Hasheamos la contraseña de manera segura
        usuarioNuevo.setContrasenia(encriptarContrasenia(usuarioNuevo.getContrasenia()));
        
        // Asignamos el estado activo y el rol predeterminado de CLIENTE
        // para asegurar que al loguearse no sea rechazado por las validaciones.
        if (usuarioNuevo.getEsActivo() == null) {
            usuarioNuevo.setEsActivo(true);
        }
        if (usuarioNuevo.getRol() == null) {
            usuarioNuevo.setRol(entidades.RolUsuario.CLIENTE);
        }

        entityManager.persist(usuarioNuevo);
        entityManager.getTransaction().commit();

        return usuarioNuevo;
    } catch (Exception e) {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
        throw new PersistenciaException("Error en la transacción al dar de alta al usuario: " + e.getMessage(), e);
    } finally {
        entityManager.close();
    }
    }

    /**
     * Encripta cadenas de texto plano utilizando el algoritmo estándar SHA-256.
     */
    private static String encriptarContrasenia(String contrasenia) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(contrasenia.getBytes(StandardCharsets.UTF_8));
        return bytesAHex(hashBytes);
    }

    /**
     * Auxiliar para transformar arreglos de bytes crudos a un formato legible hexadecimal.
     */
    private static String bytesAHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public Usuario buscarPorCorreo(String correo) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            String jpql = "SELECT u FROM Usuario u WHERE u.correo = :correo";
            TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
            query.setParameter("correo", correo);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Retorno controlado si no hay coincidencias
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar el correo solicitado en los registros", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Usuario> mostrarUsuarios() throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            String jpql = "SELECT u FROM Usuario u";
            return em.createQuery(jpql, Usuario.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al listar los usuarios del sistema: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarUsuario(Long idUsuario) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery(
                    "DELETE FROM DetallesCarrito dc WHERE dc.carrito.usuario.id = :uid")
                    .setParameter("uid", idUsuario)
                    .executeUpdate();

            em.createQuery(
                    "DELETE FROM Carrito c WHERE c.usuario.id = :uid")
                    .setParameter("uid", idUsuario)
                    .executeUpdate();
            Usuario usuario = em.find(Usuario.class, idUsuario);
            if (usuario != null) {
                em.remove(usuario);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al remover el registro de usuario", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void desactivarUsuario(Long idUsuario) throws PersistenciaException {
        cambiarEstado(idUsuario, false); // Estado bloqueado/inactivo
    }

    @Override
    public void activarUsuario(Long idUsuario) throws PersistenciaException {
        cambiarEstado(idUsuario, true); // Estado activo de la cuenta
    }

    @Override
    public Usuario buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Centraliza el cambio lógico de estado (activación/desactivación) solicitado por el Admin.
     */
    private void cambiarEstado(Long idUsuario, boolean activo) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, idUsuario);
            if (usuario != null) {
                usuario.setEsActivo(activo);
                em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al modificar el estado de activación en la BD", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Usuario editarUsuario(Usuario usuarioEditado) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();

            Usuario usuarioExistente = em.find(Usuario.class, usuarioEditado.getId());
            if (usuarioExistente == null) {
                throw new PersistenciaException("Registro no encontrado para modificar. ID: " + usuarioEditado.getId());
            }

            // Actualización controlada de los datos personales permitidos
            usuarioExistente.setNombre(usuarioEditado.getNombre());
            usuarioExistente.setTelefono(usuarioEditado.getTelefono());
            usuarioExistente.setCorreo(usuarioEditado.getCorreo());
            usuarioExistente.setDireccion(usuarioEditado.getDireccion());
            
            em.merge(usuarioExistente);
            em.getTransaction().commit();

            return usuarioExistente;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Fallo operativo al actualizar los campos del usuario: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
