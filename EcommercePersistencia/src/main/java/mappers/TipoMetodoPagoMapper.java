/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.TipoMetodoPagoDTO;
import entidades.TipoMetodoPago;

/**
 *
 * @author Fernando Garces 
 */
public class TipoMetodoPagoMapper {
    public static TipoMetodoPagoDTO entityToDTO(TipoMetodoPago tipoEntidad) {
        if (tipoEntidad == null) {
            return null;
        }

        return TipoMetodoPagoDTO.valueOf(tipoEntidad.name()); 
    }
    
    public static TipoMetodoPago dtoToEntity(TipoMetodoPagoDTO tipoDTO) {
        if (tipoDTO == null) {
            return null;
        }
        
        return TipoMetodoPago.valueOf(tipoDTO.name());
    }
}
