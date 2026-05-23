
package mappers;

import dtos.EstadoPedidoDTO;
import entidades.EstadoPedido;

/**
 *
 * @author Fernando Garces 
 */
public class EstadoPedidoMapper {
    
    /**
     * Método que convierte el enum de entidad a enum de DTO
     * @param estado estado del pedido
     * @return el nuevo enum DTO
     */
    public static EstadoPedidoDTO toDTO(EstadoPedido estado) {
        if (estado == null) return null;
        return EstadoPedidoDTO.valueOf(estado.name());
    }
    
    /**
     * Método que convierte el enum de  DTO a enum entidad 
     * @param estado estado del pedido
     * @return el nuevo enum entidad.
     */
    public static EstadoPedido toEntity(EstadoPedidoDTO estado) {
        if (estado == null) return null;
        return EstadoPedido.valueOf(estado.name());
    }
}
