package bos;

import dtos.CarritoDTO;
import entidades.Carrito;
import exception.CarritoException;
import exception.PersistenciaException;
import implementaciones.CarritosDAO;
import implementaciones.ProductoDAO;
import interfaces.ICarritosBO;
import interfaces.ICarritosDAO;
import interfaces.IProductosDAO;
import java.util.List;
import mappers.CarritoMapper;
import entidades.Usuario;
import implementaciones.UsuariosDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author Fernando Garces
 */
public class CarritosBO implements ICarritosBO {

    private ICarritosDAO carritosDAO;
    private IProductosDAO productosDAO;

    public CarritosBO() {
        this.carritosDAO = new CarritosDAO();
        this.productosDAO = new ProductoDAO();
    }

    @Override
    public List<CarritoDTO> obtenerCarritos() throws CarritoException {
        try {
            return CarritoMapper.listEntityToDTO(carritosDAO.obtenerCarritos());
        } catch (PersistenciaException e) {
            throw new CarritoException("Error al obtener los carritos: " + e.getMessage(), e);
        }
    }

    @Override
    public CarritoDTO obtenerCarritoUsuario(Long id) throws CarritoException {
        try {
            Carrito carrito = carritosDAO.obtenerCarritoUsuario(id);

            if (carrito != null) {
                return CarritoMapper.entityToDTO(carrito);
            }

            return null;

        } catch (PersistenciaException e) {
            throw new CarritoException("Error al obtener el carrito: " + e.getMessage(), e);
        }
    }

    @Override
    public CarritoDTO eliminarProducto(Long idProducto, Long idCarrito) throws CarritoException {
        try {
            Carrito carritoActualizado = carritosDAO.eliminarProducto(idProducto, idCarrito);
            return CarritoMapper.entityToDTO(carritoActualizado);
        } catch (PersistenciaException e) {
            throw new CarritoException("Error al eliminar el producto del carrito: " + e.getMessage(), e);
        }
    }

    @Override
    public CarritoDTO modificarCantidadProducto(Long carritoId, Long productoId, Integer nuevaCantidad) throws CarritoException {
        try {
            Carrito carritoActualizado = carritosDAO.modificarCantidadProducto(carritoId, productoId, nuevaCantidad);
            return CarritoMapper.entityToDTO(carritoActualizado);
        } catch (PersistenciaException e) {
            throw new CarritoException("Error al modificar la cantidad del producto en el carrito: " + e.getMessage(), e);
        }
    }

    @Override
    public CarritoDTO agregarProducto(Long idUsuario, Long idProducto, Integer cantidad) throws CarritoException {
        try {

            // Intentar obtener el carrito existente del usuario
            Carrito carrito = carritosDAO.obtenerCarritoUsuario(idUsuario);

            // Si el usuario no tiene carrito, creamos uno nuevo en la BD
            if (carrito == null) {
                carrito = new Carrito();
                // Necesitamos la entidad Usuario para asignarla al carrito
                implementaciones.UsuariosDAO usuariosDAO = new implementaciones.UsuariosDAO();
                entidades.Usuario usuarioEntidad = usuariosDAO.buscarPorId(idUsuario);
                if (usuarioEntidad == null) {
                    throw new CarritoException("No se encontró el usuario con ID: " + idUsuario);
                }
                carrito.setUsuario(usuarioEntidad);
                carrito.setTotal(0.0);
                carrito.setDetallesCarrito(new java.util.ArrayList<>());

                // Persistir el carrito nuevo en la BD
                javax.persistence.EntityManager em = implementaciones.ManejadorConexiones.getEntityManager();
                try {
                    em.getTransaction().begin();
                    em.persist(carrito);
                    em.getTransaction().commit();
                    // Recargar para obtener el ID generado
                    carrito = carritosDAO.obtenerCarritoUsuario(idUsuario);
                } finally {
                    em.close();
                }
            }

            // Ahora sí tenemos un carrito valido, agregar el producto
            entidades.Producto producto = productosDAO.obtenerProductoPorId(idProducto);
            if (producto == null) {
                throw new CarritoException("No se encontró el producto con ID: " + idProducto);
            }

            Carrito actualizado = carritosDAO.agregarProducto(producto, carrito.getId(), cantidad);
            return CarritoMapper.entityToDTO(actualizado);

        } catch (CarritoException e) {
            throw e;
        } catch (Exception e) {
            // Captura NullPointerException, RuntimeException y PersistenciaException
            throw new CarritoException("Error al agregar producto al carrito: " + e.getMessage());
        }
    }

}
