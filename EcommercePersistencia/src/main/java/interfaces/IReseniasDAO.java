/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.ReseñaDTO;
import entidades.Reseña;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Fernando Garces 
 */
public interface IReseniasDAO {
    public void eliminarResenia(Long id) throws PersistenciaException;
    public List<Reseña> obtenerResenias() throws PersistenciaException;
    public Reseña agregarResenia(ReseñaDTO nuevaResenia) throws PersistenciaException;
}
