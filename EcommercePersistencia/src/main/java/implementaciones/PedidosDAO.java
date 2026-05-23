/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entidades.EstadoPedido;
import entidades.Pedido;
import entidades.Usuario;
import exception.PersistenciaException;
import interfaces.IPedidosDAO;

/**
 *
 * @author Fernando Garces 
 */
public class PedidosDAO implements IPedidosDAO {

    @Override
    public void cambiarEstadoPedido(Long id, EstadoPedido estado) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();

        try {

            em.getTransaction().begin();
            em.createQuery("UPDATE Pedido p SET p.estado = :estado WHERE p.id = :id")
                    .setParameter("estado", estado)
                    .setParameter("id", id)
                    .executeUpdate();

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al cambiar estado de pedido: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public Pedido obtenerPedidoIndividual(Long id) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            String jpql = """
                SELECT p FROM Pedido p
                LEFT JOIN FETCH p.detallesPedido dp
                LEFT JOIN FETCH dp.producto prod
                LEFT JOIN FETCH p.usuario u
                WHERE p.id = :id
            """;

            TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);
            query.setParameter("id", id);

            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al encontrar el pedido", e);
        }
    }

    @Override
    public List<Pedido> obtenerTodosPedidos() throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();

        try {
            String jpql = "SELECT p FROM Pedido p";

            List<Pedido> pedidos = em.createQuery(jpql, Pedido.class)
                    .getResultList();
            return pedidos;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener productos: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public Pedido agregarPedido(Pedido nuevoPedido) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {

            entityManager.getTransaction().begin();

            Usuario usuarioDetached = nuevoPedido.getUsuario();

            if (usuarioDetached != null && usuarioDetached.getId() != null) {

                Usuario usuarioManaged = entityManager.getReference(Usuario.class, usuarioDetached.getId());

                nuevoPedido.setUsuario(usuarioManaged);
            }

            entityManager.persist(nuevoPedido);
            entityManager.getTransaction().commit();
            return nuevoPedido;

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al registrar el pedido: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Pedido> obtenerPedidosPorUsuario(Long idUsuario) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {

            // Consulta JPQL para obtener los pedidos de un usuario específico, ordenados por fecha descendente
            String jpql
                    = "SELECT DISTINCT p FROM Pedido p "
                    + "LEFT JOIN FETCH p.detallesPedido "
                    + "WHERE p.usuario.id = :idUsuario "
                    + "ORDER BY p.fecha DESC";

            TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
            query.setParameter("idUsuario", idUsuario);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar pedidos del usuario", e);
        } finally {
            em.close();
        }
    }

}
