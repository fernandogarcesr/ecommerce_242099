/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Fernando Garces
 */
@Entity
@Table (name="usuarios")
public class Usuario implements Serializable {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    
    @Column(name = "telefono", length = 10, nullable = false)
    private String telefono;
    
    @Column(name = "direccion", length = 100, nullable = false)
    private String direccion;
    
    @Column(name = "correo", length = 100, nullable = false)
    private String correo;
    
    @Column(name = "contraseña", length = 100, nullable = false)
    private String contrasenia;
    
    @Column(name = "esActivo", nullable = false)
    private Boolean esActivo;
    
    @Column(name = "rol", nullable = false)
    @Enumerated(EnumType.STRING)
    private RolUsuario rol;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Reseña> resenias;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public List<Reseña> getResenias() {
        return resenias;
    }

    public void setResenias(List<Reseña> resenias) {
        this.resenias = resenias;
    }
    
   
    public Usuario() {
    }

    public Usuario(Long id, String nombre, String telefono, String direccion, String correo, String contrasenia, Boolean esActivo, RolUsuario rol, List<Reseña> resenias) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.esActivo = esActivo;
        this.rol = rol;
        this.resenias = resenias;
    }

    public Usuario(String nombre, String telefono, String direccion, String correo, String contrasenia, Boolean esActivo, RolUsuario rol, List<Reseña> resenias) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.esActivo = esActivo;
        this.rol = rol;
        this.resenias = resenias;
    }

    public Usuario(String nombre, String telefono, String direccion, String correo, String contrasenia, Boolean esActivo, RolUsuario rol) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.esActivo = esActivo;
        this.rol = rol;
        this.resenias = new ArrayList<>();
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuario[ id=" + id + " ]";
    }

    @PrePersist
    public void prePersist() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = new Date();
        }
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
