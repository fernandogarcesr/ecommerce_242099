package interfaces;

import dtos.CarritoDTO;
import dtos.ProductoDTO;
import exception.CarritoException;
import java.util.List;

/**
 *
 * @author Fernando Garces 
 */
public interface ICarritosBO {

    public List<CarritoDTO> obtenerCarritos() throws CarritoException;

    public CarritoDTO obtenerCarritoUsuario(Long id) throws CarritoException;

    public CarritoDTO agregarProducto(Long idUsuario, Long idProducto, Integer cantidad) throws CarritoException;

    public CarritoDTO eliminarProducto(Long idProducto, Long idCarrito) throws CarritoException;

    public CarritoDTO modificarCantidadProducto(Long carritoId, Long productoId, Integer nuevaCantidad) throws CarritoException;

}
