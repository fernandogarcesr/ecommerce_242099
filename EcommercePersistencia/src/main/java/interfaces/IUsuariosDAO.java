/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.UsuarioDTO;
import entidades.Usuario;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Fernando Garces 
 */
public interface IUsuariosDAO {
    public Usuario iniciarSesion(String nombre, String contrasenia) throws PersistenciaException;
    public Usuario registrarUsuario(Usuario usuarioNuevo) throws PersistenciaException;
    public Usuario buscarPorCorreo(String correo) throws PersistenciaException;
    
    public List<Usuario> mostrarUsuarios() throws PersistenciaException;
    public void eliminarUsuario(Long idUsuario) throws PersistenciaException; 
    public void desactivarUsuario(Long idUsuario) throws PersistenciaException;
    public void activarUsuario(Long idUsuario) throws PersistenciaException;
    public Usuario buscarPorId(Long id) throws PersistenciaException;
    public Usuario editarUsuario(Usuario usuarioEditado) throws PersistenciaException;
}
