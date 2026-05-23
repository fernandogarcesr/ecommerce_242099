/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.CarritoDTO;
import entidades.Carrito;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando Garces 
 */
public class CarritoMapper {

    public static CarritoDTO entityToDTO(Carrito carrito){
        return new CarritoDTO(carrito.getId(),carrito.getTotal(),DetalleCarritoMapper.toListDTO(carrito.getDetallesCarrito()), 
                UsuarioMapper.entityToDTO(carrito.getUsuario()));
    }
    
    public static List<CarritoDTO> listEntityToDTO(List<Carrito> carritos) {
        List<CarritoDTO> carritosDTO = new ArrayList<>();
        
        for (Carrito carrito : carritos) {
            carritosDTO.add(entityToDTO(carrito));  
        }
        
        return carritosDTO;
    }

}
