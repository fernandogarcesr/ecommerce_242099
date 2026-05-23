/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.DetallePedidoDTO;
import dtos.ProductoDTO;
import entidades.DetallesPedido;
import entidades.Pedido;
import entidades.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando Garces 
 */
public class DetallePedidoMapper {
    public static DetallesPedido dtoToEntity(DetallePedidoDTO detalleDTO) {
        if (detalleDTO == null) {
            return null;
        }

        DetallesPedido detalle = new DetallesPedido();
        
        detalle.setCantidad(detalleDTO.getCantidad());
        
        Producto producto = ProductoMapper.DTOToEntity(detalleDTO.getProducto()); 
        detalle.setProducto(producto); 

        
        return detalle;
    }

    /**
     * Convierte una lista de DetallePedidoDTO a una lista de DetallesPedido.
     */
    public static List<DetallesPedido> listDtoToEntity(List<DetallePedidoDTO> detallesDTO) {
        List<DetallesPedido> listaEntidades = new ArrayList<>();
        if (detallesDTO != null) {
            for (DetallePedidoDTO dto : detallesDTO) {
                listaEntidades.add(dtoToEntity(dto));
            }
        }
        return listaEntidades;
    }
    
    public static DetallePedidoDTO entityToDTO(DetallesPedido detalle) {
        if (detalle == null) {
            return null;
        }

        ProductoDTO productoDTO = ProductoMapper.entityToDTO(detalle.getProducto()); 

        return new DetallePedidoDTO(
                detalle.getId(), 
                detalle.getCantidad(),
                productoDTO
        );
    }

    /**
     * Convierte una lista de entidades DetallesPedido a una lista de DetallePedidoDTO.
     */
    public static List<DetallePedidoDTO> listEntityToDTO(List<DetallesPedido> detallesEntidad) {
        List<DetallePedidoDTO> listaDTO = new ArrayList<>();
        if (detallesEntidad != null) {
            for (DetallesPedido entidad : detallesEntidad) {
                listaDTO.add(entityToDTO(entidad));
            }
        }
        return listaDTO;
    }
}
