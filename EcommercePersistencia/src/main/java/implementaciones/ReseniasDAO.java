/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import dtos.ReseñaDTO;
import entidades.Producto;
import entidades.Reseña;
import entidades.Usuario;
import exception.PersistenciaException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import interfaces.IReseniasDAO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Fernando Garces 
 */
public class ReseniasDAO implements IReseniasDAO{

    @Override
    public void eliminarResenia(Long id) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            
            String jpqlQuery = """
                               DELETE FROM Reseña r WHERE r.id = :id
                               """;
            Query query = entityManager.createQuery(jpqlQuery);
            query.setParameter("id", id);
            query.executeUpdate();
            
            entityManager.getTransaction().commit();

        } catch(Exception e) {
            if (entityManager.getTransaction().isActive()) { 
            entityManager.getTransaction().rollback();
        }
        throw new PersistenciaException("Error al eliminar la reseña: " + e.getMessage(), e);
        } finally{
            entityManager.close();
        }
    }

    /**
     * Método para obtener todas las resenias de la base de datos
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public List<Reseña> obtenerResenias() throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {

            String jpql = "SELECT r FROM Reseña r";

            List<Reseña> resenias = em.createQuery(jpql, Reseña.class)
                    .getResultList();

            return resenias;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener resenias: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public Reseña agregarResenia(ReseñaDTO nuevaResenia) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();

        try {
            em.getTransaction().begin();

            Producto producto = em.find(Producto.class, nuevaResenia.getIdProducto());
            
            if (producto == null) {
                throw new PersistenciaException("No se encontró el producto con ID: " + nuevaResenia.getIdProducto());
            }

            if (nuevaResenia.getUsuario() == null || nuevaResenia.getUsuario().getId() == null) {
                throw new PersistenciaException("Datos de usuario inválidos en la reseña.");
            }
            Usuario usuario = em.find(Usuario.class, nuevaResenia.getUsuario().getId());
            
            if (usuario == null) {
                throw new PersistenciaException("No se encontró el usuario con ID: " + nuevaResenia.getUsuario().getId());
            }


            Reseña reseñaEntity = new Reseña();
            reseñaEntity.setComentario(nuevaResenia.getComentario());
            reseñaEntity.setEstrellas(nuevaResenia.getEstrellas());
  
            reseñaEntity.setFechaHora(
                nuevaResenia.getFecha() != null 
                ? nuevaResenia.getFecha()
                : new Date()
            );

            reseñaEntity.setProducto(producto);
            reseñaEntity.setUsuario(usuario);

            em.persist(reseñaEntity);

            em.getTransaction().commit();
            return reseñaEntity;

        } catch (PersistenciaException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al agregar la reseña: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
}
