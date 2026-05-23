/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Fernando Garces
 */
@Entity
@Table(name = "detalles_carrito")
public class DetallesCarrito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidadProducto;

    @Column(name = "importe", nullable = false, precision = 10, scale = 2)
    private Float importe;

    //muchos carritos estan asociados a un producto
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    //muchos DetallesCarritos estan asociados a un carrito
    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = true)
    private Carrito carrito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public DetallesCarrito() {
    }

    public DetallesCarrito(Integer cantidadProducto, Producto producto, Carrito carrito) {
        this.cantidadProducto = cantidadProducto;
        this.importe = (float) (cantidadProducto * producto.getPrecio());
        this.producto = producto;
        this.carrito = carrito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesCarrito)) {
            return false;
        }
        DetallesCarrito other = (DetallesCarrito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetallesCarrito[ id=" + id + " ]";
    }

}
