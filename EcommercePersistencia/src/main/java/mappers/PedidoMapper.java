/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import java.util.ArrayList;
import java.util.List;

import dtos.DetallePedidoDTO;
import dtos.EstadoPedidoDTO;
import dtos.MetodoDePagoDTO;
import dtos.PedidoDTO;
import dtos.UsuarioDTO;
import entidades.DetallesPedido;
import entidades.Pedido;

/**
 *
 * @author Fernando Garces 
 */
public class PedidoMapper {
    public static PedidoDTO entityToDTO(Pedido pedido){
        List<DetallePedidoDTO> detallesDTO = new ArrayList<>();
        if (pedido.getDetallesPedido() != null) {
            for (DetallesPedido detalle : pedido.getDetallesPedido()) {
                detallesDTO.add(DetallePedidoMapper.entityToDTO(detalle)); 
            }
        }
        System.out.println(pedido.getUsuario());
        UsuarioDTO usuarioDTO = UsuarioMapper.entityToDTO(pedido.getUsuario()); 

        MetodoDePagoDTO metodoPagoDTO = MetodoPagoMapper.entityToDTO(pedido.getMetodoPago());

        EstadoPedidoDTO estadoDTO = EstadoPedidoMapper.toDTO(pedido.getEstado());
        
        return new PedidoDTO(
                pedido.getId(),
                pedido.getNumeroPedido(),
                estadoDTO,
                pedido.getTotal(),
                pedido.getFecha(),
                metodoPagoDTO,
                detallesDTO,
                pedido.getDireccion(),
                usuarioDTO
        );
    }
    
    public static Pedido dtoToEntity(PedidoDTO pedidoDTO){
        Pedido pedido = new Pedido();

        pedido.setId(pedidoDTO.getId());
        pedido.setNumeroPedido(pedidoDTO.getNumeroPedido());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setFecha(pedidoDTO.getFecha());
        pedido.setDireccion(pedidoDTO.getDireccion());

        // Estado
        if (pedidoDTO.getEstado() != null) {
            pedido.setEstado(EstadoPedidoMapper.toEntity(pedidoDTO.getEstado()));
        }

        // Metodo de pago
        if (pedidoDTO.getMetodoPago() != null) {
            pedido.setMetodoPago(MetodoPagoMapper.dtoToEntity(pedidoDTO.getMetodoPago()));
        }

        // Usuario
        if (pedidoDTO.getUsuario() != null) {
            pedido.setUsuario(UsuarioMapper.DTOToEntity(pedidoDTO.getUsuario()));
        }

        // Lista de detalles
        List<DetallesPedido> detallesEntity = new ArrayList<>();
        if (pedidoDTO.getDetallesPedido() != null) {
            for (DetallePedidoDTO detalleDTO : pedidoDTO.getDetallesPedido()) {
                detallesEntity.add(DetallePedidoMapper.dtoToEntity(detalleDTO));
            }
        }
        
        // ¡AGREGAR ESTO! Asignar el Pedido padre a cada detalle
        for (DetallesPedido detalle : detallesEntity) { 
            detalle.setPedido(pedido); 
        }
        
        pedido.setDetallesPedido(detallesEntity);

        return pedido;
        
    }
}
