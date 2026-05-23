/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import java.util.ArrayList;
import java.util.List;

import dtos.ProductoDTO;
import dtos.ReseñaDTO;
import entidades.Producto;
import exception.AgregarProductoException;
import exception.EditarProductoException;
import exception.EliminarProductoException;
import exception.ObtenerProductosException;
import exception.PersistenciaException;
import implementaciones.ProductoDAO;
import interfaces.IProductosBO;
import interfaces.IProductosDAO;
import mappers.ProductoMapper;
import mappers.ReseñaMapper;

/**
 *
 * @author Fernando Garces
 */
public class ProductoBO implements IProductosBO{
    IProductosDAO productosDAO;
    public ProductoBO() {
        productosDAO = new ProductoDAO();
    }
    
    @Override
    public boolean eliminarProducto(Long id) throws EliminarProductoException {
        try {
            return productosDAO.eliminarProducto(id);
        } catch (PersistenciaException ex) {
            throw new EliminarProductoException("hubo un error al eliminar el producto");
        }
    }

    @Override
    public ProductoDTO agregarProducto(ProductoDTO nuevoProducto) throws AgregarProductoException {
        try {
            
            Producto producto = ProductoMapper.DTOToEntity(nuevoProducto);
            productosDAO.agregarProducto(producto);
            return ProductoMapper.entityToDTO(producto);
        } catch (PersistenciaException ex) {
            throw new AgregarProductoException("hubo un error al agregar un producto");
        }
    }
    @Override
    public void editarProducto(Long id, ProductoDTO nuevoProducto) throws EditarProductoException {
        try {
            productosDAO.editarProducto(id, nuevoProducto);
        } catch (PersistenciaException ex) {
            throw new EditarProductoException("hubo un problema al editar el perfil");
        }
    }

    @Override
    public List<ProductoDTO> obtenerProductos() throws ObtenerProductosException {
        try {
            List<Producto> productos = productosDAO.obtenerProductos();
            List<ProductoDTO> productosDTO = new ArrayList<>();
            for (Producto producto : productos) {
                ProductoDTO productoDTO = ProductoMapper.entityToDTO(producto);
                productosDTO.add(productoDTO);
            }
            return productosDTO;
        } catch (PersistenciaException ex){
            throw new ObtenerProductosException("hubo un problema al cargar los productos");
        }
    }
    
        @Override
        public ProductoDTO obtenerProductoPorId(Long id) throws ObtenerProductosException {
            try{
                Producto producto = productosDAO.obtenerProductoPorId(id);

                if (producto == null) {
                    return null;
                }
                ProductoDTO dto = ProductoMapper.entityToDTO(producto);
                return dto;


            } catch (PersistenciaException ex){
                throw new ObtenerProductosException("Error al cargar el producto");
            }
        }

    @Override
    public List<ProductoDTO> buscarProductos(String nombre, Double precioMin, Double precioMax) throws ObtenerProductosException {
        try {
        List<Producto> productos = productosDAO.buscarProductosDinamico(nombre, precioMin, precioMax);
        
        List<ProductoDTO> productosDTO = new ArrayList<>();
        for (Producto producto : productos) {
            productosDTO.add(ProductoMapper.entityToDTO(producto));
        }
        return productosDTO;
    } catch (PersistenciaException ex) {
        throw new ObtenerProductosException("Error al filtrar productos");
        }
    }

    @Override
    public List<ReseñaDTO> obtenerReseñas(Long id) throws ObtenerProductosException {
        try{
            Producto producto = productosDAO.obtenerProductoPorId(id);
            if (producto == null) {
                return null;
            }
            List<ReseñaDTO> dto = ReseñaMapper.entityListToDTOList(producto.getResenias());
            return dto;
        } catch (PersistenciaException ex){
            throw new ObtenerProductosException("Error al cargar el producto");
        }
    }
}
