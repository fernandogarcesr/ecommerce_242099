/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
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
@Table(name = "reseñas")
public class Reseña implements Serializable {

    @Id
    @Column(name = "id_reseña")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "comentario", nullable = false)
    private String comentario;
    
    @Column(name = "estrellas", nullable = false)
    private Integer estrellas;
    
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    //muchas reseñas a un producto
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    
    //muchas reseñas de un usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public Date getFechaHora() {
        return fecha;
    }

    public void setFechaHora(Date fechaHora) {
        this.fecha = fechaHora;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Reseña() {
    }

    public Reseña(String comentario, Integer estrellas, Date fecha, Producto producto, Usuario usuario) {
        this.comentario = comentario;
        this.estrellas = estrellas;
        this.fecha = fecha;
        this.producto = producto;
        this.usuario = usuario;
    }
    
    
    public Reseña(Long id, String comentario, Integer estrellas, Date fecha, Producto producto, Usuario usuario) {
        this.id = id;
        this.comentario = comentario;
        this.estrellas = estrellas;
        this.fecha = fecha;
        this.producto = producto;
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
        if (!(object instanceof Reseña)) {
            return false;
        }
        Reseña other = (Reseña) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Rese\u00f1a[ id=" + id + " ]";
    }
    
}
