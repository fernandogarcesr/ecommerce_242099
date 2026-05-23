/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Carrito;
import entidades.Producto;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Fernando Garces 
 */
public interface ICarritosDAO {

    public List<Carrito> obtenerCarritos() throws PersistenciaException;

    public Carrito obtenerCarritoUsuario(Long idUsuario) throws PersistenciaException;

    public Carrito agregarProducto(Producto producto, Long carritoId, Integer cantidad) throws PersistenciaException;

    public Carrito eliminarProducto(Long idProducto, Long idCarrito) throws PersistenciaException;
    
    public Carrito modificarCantidadProducto(Long carritoId, Long productoId, Integer nuevaCantidad) throws PersistenciaException;
    
    public void limpiarCarrito(Long carritoId) throws PersistenciaException;
}
