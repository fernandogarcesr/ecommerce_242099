package dtos;

import java.util.List;

/**
 *
 * @author Fernando Garces
 */
public class UsuarioDTO {
     private Long id;
     private String nombre;
     private String direccion;
     private String telefono;
     private String correo;
     private String contrasenia;
     private Boolean esActivo;
     private RolUsuarioDTO rol;
     private List<ReseñaDTO> reseña;

    public UsuarioDTO(Long id, String nombre, String direccion, String telefono, String correo, String contrasenia, Boolean esActivo, RolUsuarioDTO rol, List<ReseñaDTO> reseña) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.esActivo = esActivo;
        this.rol = rol;
        this.reseña = reseña;
    }

    public UsuarioDTO(Long id, String nombre, String direccion, String telefono, String correo, String contrasenia, Boolean esActivo, RolUsuarioDTO rol) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.esActivo = esActivo;
        this.rol = rol;
    }

    public UsuarioDTO(String nombre, String direccion, String telefono, String correo, String contrasenia, Boolean esActivo, RolUsuarioDTO rol) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.esActivo = esActivo;
        this.rol = rol;
    }

    public UsuarioDTO() {
    }
    
    
    

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public RolUsuarioDTO getRol() {
        return rol;
    }

    public void setRol(RolUsuarioDTO rol) {
        this.rol = rol;
    }

    public List<ReseñaDTO> getReseña() {
        return reseña;
    }

    public void setReseña(List<ReseñaDTO> reseña) {
        this.reseña = reseña;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", correo=" + correo + ", contrasenia=" + contrasenia + ", esActivo=" + esActivo + ", rol=" + rol + ", rese\u00f1a=" + reseña + '}';
    }
     
    
     
}
