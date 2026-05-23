/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;

import dtos.ProductoDTO;
import dtos.ReseñaDTO;
import exception.AgregarProductoException;
import exception.EditarProductoException;
import exception.EliminarProductoException;
import exception.ObtenerProductosException;

/**
 *
 * @author Fernando Garces 
 */
public interface IProductosBO {
    public boolean eliminarProducto(Long id) throws EliminarProductoException;
    public ProductoDTO agregarProducto(ProductoDTO nuevoProducto) throws AgregarProductoException; 
    public void editarProducto(Long id, ProductoDTO nuevoProducto) throws EditarProductoException; 
    public List<ProductoDTO> obtenerProductos() throws ObtenerProductosException;
    public ProductoDTO obtenerProductoPorId(Long id) throws ObtenerProductosException;
    public List<ProductoDTO> buscarProductos(String nombre, Double precioMin, Double precioMax) throws ObtenerProductosException;
    public List<ReseñaDTO> obtenerReseñas(Long id)throws ObtenerProductosException;    
}

