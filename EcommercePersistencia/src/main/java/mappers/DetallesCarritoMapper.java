/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.DetallesCarritoDTO;
import entidades.DetallesCarrito;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando Garces 
 */
public class DetallesCarritoMapper {
    
    
    public static DetallesCarritoDTO entityToDTO(DetallesCarrito detalleCarrito){
        DetallesCarritoDTO dto = new DetallesCarritoDTO(detalleCarrito.getId(), detalleCarrito.getCantidadProducto(), 
                 ProductoMapper.entityToDTO(detalleCarrito.getProducto()), null);
        return dto;
    }
            
            
            
            
            
            
    public static List<DetallesCarritoDTO> listEntityToDTO(List<DetallesCarrito> detallesCarrito){
        List<DetallesCarritoDTO> listaDTO = new ArrayList<>();
        if (detallesCarrito != null) {
            for (DetallesCarrito entidad : detallesCarrito) {
                listaDTO.add(entityToDTO(entidad));
            }
        }
        return listaDTO;
    }
}
