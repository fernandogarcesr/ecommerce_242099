package implementaciones;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dtos.ProductoDTO;
import entidades.Disponibilidad;
import entidades.Producto;
import entidades.Reseña;
import exception.PersistenciaException;
import interfaces.IProductosDAO;
import mappers.DisponibilidadMapper;

/**
 *
 * @author Fernando Garces 
 */
public class ProductoDAO implements IProductosDAO {

    @Override
    public boolean eliminarProducto(Long id) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            Query queryDetalles = entityManager.createQuery(
                    "DELETE FROM DetallesPedido d WHERE d.producto.id = :id");
            queryDetalles.setParameter("id", id);
            queryDetalles.executeUpdate();

            Query queryResenias = entityManager.createQuery(
                    "DELETE FROM Reseña r WHERE r.producto.id = :id");
            queryResenias.setParameter("id", id);
            queryResenias.executeUpdate();

            Query queryProducto = entityManager.createQuery(
                    "DELETE FROM Producto p WHERE p.id = :id");
            queryProducto.setParameter("id", id);
            queryProducto.executeUpdate();

            entityManager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new PersistenciaException("Error al eliminar el producto: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Producto agregarProducto(Producto nuevoProducto) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(nuevoProducto);
            entityManager.getTransaction().commit();
            return nuevoProducto;

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new PersistenciaException("Error al registrar el producto: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void editarProducto(Long id, ProductoDTO nuevoProducto) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            String nuevoNombre = nuevoProducto.getNombre();
            Double nuevoPrecio = nuevoProducto.getPrecio();
            Integer nuevoStock = nuevoProducto.getStock();
            String nuevaDescripcion = nuevoProducto.getDescripcion();
            String nuevaRutaImagen = nuevoProducto.getRutaImagen();
            // Se cambió de boolean a enum para manejar el estado del producto
            Disponibilidad nuevaDisponibilidad = DisponibilidadMapper.toEntity(nuevoProducto.getDisponibilidad());

            em.getTransaction().begin();

            String jpql = "UPDATE Producto p SET "
                    + "p.nombre = :nombre, "
                    + "p.precio = :precio, "
                    + "p.stock = :stock, "
                    + "p.descripcion = :descripcion, "
                    + "p.disponibilidad = :disponibilidad, "
                    + "p.rutaImagen = :rutaImagen "
                    + "WHERE p.id = :id";

            Query query = em.createQuery(jpql);
            query.setParameter("nombre", nuevoNombre);
            query.setParameter("precio", nuevoPrecio);
            query.setParameter("stock", nuevoStock);
            query.setParameter("descripcion", nuevaDescripcion);
            query.setParameter("disponibilidad", nuevaDisponibilidad);
            query.setParameter("rutaImagen", nuevaRutaImagen);
            query.setParameter("id", id);

            int filasAfectadas = query.executeUpdate();
            em.getTransaction().commit();

            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se encontró el producto con ID: " + id);
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            throw new PersistenciaException("Error al editar el producto con ID: " + id + " | " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public List<Producto> obtenerProductos() throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener productos: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public Producto obtenerProductoPorId(Long id) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            String jpql = "SELECT p FROM Producto p "
                    + "LEFT JOIN FETCH p.resenias r "
                    + "LEFT JOIN FETCH r.usuario "
                    + "WHERE p.id=:id";

            return em.createQuery(jpql, Producto.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (javax.persistence.NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar el producto: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public List<Producto> buscarProductosDinamico(String nombre, Double precioMin, Double precioMax) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            StringBuilder jpql = new StringBuilder("SELECT p FROM Producto p WHERE 1=1");

            if (nombre != null && !nombre.isEmpty()) jpql.append(" AND LOWER(p.nombre) LIKE :nombre");
            if (precioMin != null) jpql.append(" AND p.precio >= :precioMin");
            if (precioMax != null) jpql.append(" AND p.precio <= :precioMax");

            Query query = em.createQuery(jpql.toString(), Producto.class);

            if (nombre != null && !nombre.isEmpty()) query.setParameter("nombre", "%" + nombre.toLowerCase() + "%");
            if (precioMin != null) query.setParameter("precioMin", precioMin);
            if (precioMax != null) query.setParameter("precioMax", precioMax);

            return query.getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar productos con filtros: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public List<Reseña> obtenerReseñas(Long id) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            String jpql = "SELECT p FROM Producto p LEFT JOIN FETCH p.resenias WHERE p.id=:id";
            Producto producto = em.createQuery(jpql, Producto.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return producto.getResenias();
        } catch (javax.persistence.NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar el producto: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public boolean verificarCompraUsuario(Long idUsuario, Long idProducto) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            String jpql = "SELECT COUNT(dp) FROM DetallesPedido dp "
                    + "JOIN dp.pedido p "
                    + "WHERE p.usuario.id = :idUsuario "
                    + "AND dp.producto.id = :idProducto";

            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("idUsuario", idUsuario);
            query.setParameter("idProducto", idProducto);

            return query.getSingleResult() > 0;

        } catch (Exception e) {
            throw new PersistenciaException("Error al verificar si el usuario compró el producto", e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
}