/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;



/**
 *
 * @author Fernando Garces
 */
public class DetallesCarritoDTO {
    private Long id;
    private Integer cantidadProductos;
    private ProductoDTO producto;
    private Long idcarrito;

    public DetallesCarritoDTO(Long id, Integer cantidadProductos, ProductoDTO producto, Long carrito) {
        this.id = id;
        this.cantidadProductos = cantidadProductos;
        this.producto = producto;
        this.idcarrito = carrito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(Integer cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public Long getCarrito() {
        return idcarrito;
    }

    public void setCarrito(Long carrito) {
        this.idcarrito = carrito;
    }

    @Override
    public String toString() {
        return "DetallesCarritoDTO{" + "id=" + id + ", cantidadProductos=" + cantidadProductos + ", producto=" + producto + ", idcarrito=" + idcarrito + '}';
    }
    
    
}
