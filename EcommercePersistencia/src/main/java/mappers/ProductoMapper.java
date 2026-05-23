package mappers;

import dtos.ProductoDTO;
import entidades.Producto;

/**
 *
 * @author Fernando Garces 
 */
public class ProductoMapper {

    public static ProductoDTO entityToDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getDescripcion(),
                //convierte el enum Disponibilidad a DisponibilidadDTO
                DisponibilidadMapper.toDTO(producto.getDisponibilidad()),
                producto.getEspecificacionesTecnicas(),
                producto.getRutaImagen(),
                ReseñaMapper.entityListToDTOList(producto.getResenias()));

        // mapea el campo valoracion del MER
        productoDTO.setValoracion(producto.getValoracion());

        return productoDTO;
    }

    public static Producto DTOToEntity(ProductoDTO producto) {
        Producto productoEntity = new Producto(
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getDescripcion(),
                //convierte DisponibilidadDTO al enum de entidad
                DisponibilidadMapper.toEntity(producto.getDisponibilidad()),
                producto.getEspecificacionesTecnicas(),
                ReseñaMapper.DTOListToEntityList(producto.getReseñas()));

        productoEntity.setRutaImagen(producto.getRutaImagen());
        productoEntity.setId(producto.getId());
        // Agregado: mapea el campo valoracion del MER
        productoEntity.setValoracion(producto.getValoracion());

        return productoEntity;
    }
}