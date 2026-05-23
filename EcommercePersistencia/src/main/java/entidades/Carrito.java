/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Fernando Garces
 */
@Entity
@Table(name = "carritos")
public class Carrito implements Serializable {

    @Id
    @Column(name = "id_carrito")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "total",  nullable = false)
    private Double total;
    
    //detallesCarrito
    //un carrito tiene varios detalles carrito, si se elimina el carrito sus detallesCarrito se van con el
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<DetallesCarrito> detallesCarrito;
    
    //usuario
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = true)
    private Usuario usuario;

    public Carrito() {
    }

    public Carrito(Double total, List<DetallesCarrito> detallesCarrito, Usuario usuario) {
        this.total = total;
        this.detallesCarrito = detallesCarrito;
        this.usuario = usuario;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetallesCarrito> getDetallesCarrito() {
        return detallesCarrito;
    }

    public void setDetallesCarrito(List<DetallesCarrito> detallesCarrito) {
        this.detallesCarrito = detallesCarrito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Carrito)) {
            return false;
        }
        Carrito other = (Carrito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Carrito[ id=" + id + " ]";
    }
    
}