/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import java.util.ArrayList;
import java.util.List;

import dtos.ReseñaDTO;
import dtos.UsuarioDTO;
import entidades.Reseña;
import entidades.Usuario;
import exception.EliminarReseñaException;
import exception.ObtenerReseniasException;
import exception.PersistenciaException;
import exception.ReseniaException;
import implementaciones.ProductoDAO;
import implementaciones.ReseniasDAO;
import implementaciones.UsuariosDAO;
import interfaces.IProductosDAO;
import interfaces.IReseniasBO;
import interfaces.IReseniasDAO;
import interfaces.IUsuariosDAO;
import mappers.ReseñaMapper;
import mappers.UsuarioMapper;

/**
 *
 * @author Fernando Garces
 */
public class ReseniasBO implements IReseniasBO {

    IReseniasDAO reseniaDAO;
    IProductosDAO productosDAO;
    IUsuariosDAO usuariosDAO;

    public ReseniasBO() {
        reseniaDAO = new ReseniasDAO();
        productosDAO = new ProductoDAO();
        usuariosDAO = new UsuariosDAO();
    }

    /**
     * Método para eliminar una reseña por su id.
     * @param id id de la reseña a eliminar.
     * @throws EliminarReseñaException 
     */
    @Override
    public void eliminarResenia(long id) throws EliminarReseñaException {
        try {
            reseniaDAO.eliminarResenia(id);
        } catch (PersistenciaException ex) {
            throw new EliminarReseñaException("La reseña a elimimnar no existe");
        }
    }

    /**
     * Método de negocio que obtiene todas las reseñas registradas y las
     * convierte en DTO para mandarlas.
     *
     * @return la lista de las reseñas en DTO.
     * @throws ObtenerReseniasException
     */
    @Override
    public List<ReseñaDTO> obtenerResenias() throws ObtenerReseniasException {

        try {
            List<Reseña> resenias = reseniaDAO.obtenerResenias();

            List<ReseñaDTO> reseniasDTO = new ArrayList<>();

            for (Reseña resenia : resenias) {
                ReseñaDTO reseniaDTO = ReseñaMapper.entityToDTO(resenia);
                reseniasDTO.add(reseniaDTO);
            }

            return reseniasDTO;
        } catch (PersistenciaException ex) {
            throw new ObtenerReseniasException("No se pudieron obtener las reseñas");
        }

    }

    @Override
    public ReseñaDTO agregarResenia(Long idProducto, Long idUsuario, ReseñaDTO nuevaResenia) throws ReseniaException {


        try {
            boolean compro = productosDAO.verificarCompraUsuario(idUsuario, idProducto);
            if (!compro) {
                throw new ReseniaException("Solo puedes opinar de productos que has comprado.");
            }
            
            if (nuevaResenia.getEstrellas() == null || nuevaResenia.getEstrellas() < 1 || nuevaResenia.getEstrellas() > 5) {
                throw new ReseniaException("La calificación debe ser entre 1 y 5 estrellas.");
            }

            if (nuevaResenia.getComentario() == null || nuevaResenia.getComentario().trim().isEmpty()) {
                throw new ReseniaException("El comentario de la reseña no puede estar vacío.");
            }
            
            nuevaResenia.setIdProducto(idProducto);
            
            Usuario usuario = usuariosDAO.buscarPorId(idUsuario);
            UsuarioDTO usuarioDTO = UsuarioMapper.entityToDTO(usuario);
            
            nuevaResenia.setUsuario(usuarioDTO);
            
            Reseña resenia = reseniaDAO.agregarResenia(nuevaResenia);
            
            ReseñaDTO reseniaDTO = ReseñaMapper.entityToDTO(resenia);
            
            return reseniaDTO;
        } catch (PersistenciaException ex) {
            throw new ReseniaException("Error al intentar guardar la reseña: " + ex.getMessage(), ex);
        }
    }

}
