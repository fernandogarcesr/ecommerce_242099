/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ReseñaDTO;
import exception.EliminarReseñaException;
import exception.ObtenerReseniasException;
import exception.ReseniaException;
import java.util.List;

/**
 *
 * @author Fernando Garces 
 */
public interface IReseniasBO {
     public void eliminarResenia(long id) throws EliminarReseñaException;
     public List<ReseñaDTO> obtenerResenias() throws ObtenerReseniasException;
     public ReseñaDTO agregarResenia(Long idProducto, Long idUsuario, ReseñaDTO dto) throws ReseniaException;
}
