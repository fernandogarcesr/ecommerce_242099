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
public class DetalleCarritoMapper {
    public static List<DetallesCarritoDTO> toListDTO(List<DetallesCarrito> detalles){
        List<DetallesCarritoDTO> detallesDTO = new ArrayList<>();
        for(DetallesCarrito detalle : detalles){
            detallesDTO.add(new DetallesCarritoDTO(detalle.getId(),detalle.getCantidadProducto(),
                    ProductoMapper.entityToDTO(detalle.getProducto()),detalle.getCarrito().getId()));
        }
        return detallesDTO;
    }
}
