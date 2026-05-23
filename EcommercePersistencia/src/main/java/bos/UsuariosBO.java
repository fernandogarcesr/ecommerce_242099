/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import java.util.ArrayList;
import java.util.List;

import dtos.UsuarioDTO;
import entidades.Usuario;
import exception.AdministrarUsuarioException;
import exception.EditarUsuarioException;
import exception.PersistenciaException;
import exception.UsuarioInexistenteException;
import exception.UsuarioNoRegistradoException;
import implementaciones.UsuariosDAO;
import interfaces.IUsuariosBO;
import interfaces.IUsuariosDAO;
import mappers.UsuarioMapper;

/**
 *
 * @author Fernando Garces
 */
public class UsuariosBO implements IUsuariosBO{
    IUsuariosDAO usuariosDAO;

    public UsuariosBO() {
        this.usuariosDAO = new UsuariosDAO();
    }
    
    
    

    @Override
    public UsuarioDTO iniciarSesion(String correo, String contrasenia) throws UsuarioInexistenteException {
        try {
            Usuario usuario= usuariosDAO.iniciarSesion(correo, contrasenia);
            if(usuario != null){
                return UsuarioMapper.entityToDTO(usuario);
            }
            return null;
        } catch (PersistenciaException ex) {
            throw new UsuarioInexistenteException("No hay un usuario que coincida con ese correo y contrasenia ");
        }
    }

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO nuevoUsuario) throws UsuarioNoRegistradoException {
        try{
            Usuario usuarioExistente = usuariosDAO.buscarPorCorreo(nuevoUsuario.getCorreo());
            
            if (usuarioExistente != null ){
                throw new UsuarioNoRegistradoException("El correo ya se encuentra registrado");
            }
            
            
            
            Usuario usuarioRegistrado = usuariosDAO.registrarUsuario(UsuarioMapper.DTOToEntity(nuevoUsuario));
            
            return UsuarioMapper.entityToDTO(usuarioRegistrado);
            
        } catch (PersistenciaException e){
            throw new UsuarioNoRegistradoException("No se pudo registrar al usuario " + e.getMessage(), e);
        }
    }

    @Override
    public List<UsuarioDTO> mostrarUsuarios() throws AdministrarUsuarioException {
        try {
            List<Usuario> usuarios = usuariosDAO.mostrarUsuarios();
            List<UsuarioDTO> dtos = new ArrayList<>();
            for(Usuario u : usuarios){
                dtos.add(UsuarioMapper.entityToDTO(u));
            }
            return dtos;
        } catch (PersistenciaException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void eliminarUsuario(Long idUsuario) throws AdministrarUsuarioException {
        try {
            Usuario usuario = usuariosDAO.buscarPorId(idUsuario);

            if (usuario == null) {
                throw new AdministrarUsuarioException("El usuario con ID " + idUsuario + " no existe.");
            }

            if (usuario.getEsActivo()) {
                throw new AdministrarUsuarioException("No se puede eliminar un usuario ACTIVO. Debes desactivarlo primero.");
            }

            usuariosDAO.eliminarUsuario(idUsuario);

        } catch (PersistenciaException ex) {
            throw new AdministrarUsuarioException("Error al intentar eliminar el usuario: " + ex.getMessage(), ex);
        }
    }


    @Override
    public void desactivarUsuario(Long idUsuario) throws AdministrarUsuarioException {
        try {
            usuariosDAO.desactivarUsuario(idUsuario);
        } catch (PersistenciaException ex) {
            throw new AdministrarUsuarioException("No se pudo desactivar al usuario. " + ex.getMessage(), ex);
        }
    }

    @Override
    public void activarUsuario(Long idUsuario) throws AdministrarUsuarioException {
        try {
            usuariosDAO.activarUsuario(idUsuario);
        } catch (PersistenciaException ex) {
            throw new AdministrarUsuarioException("No se pudo activar al usuario. " + ex.getMessage(), ex);
        }
    }

    @Override
    public UsuarioDTO editarUsuario(UsuarioDTO usuarioEditado) throws EditarUsuarioException {
        if (usuarioEditado.getNombre() != null && usuarioEditado.getNombre().matches(".*\\d.*")) {
            throw new EditarUsuarioException("El nombre no puede contener números.");
        }


        if (usuarioEditado.getTelefono() != null) {
            if (!usuarioEditado.getTelefono().matches("\\d{10}")) {
                throw new EditarUsuarioException("El número de teléfono debe tener exactamente 10 dígitos numéricos.");
            }
        }

        try {

            Usuario usuarioEntity = UsuarioMapper.DTOToEntity(usuarioEditado); 

            Usuario usuarioActualizado = usuariosDAO.editarUsuario(usuarioEntity);

            return UsuarioMapper.entityToDTO(usuarioActualizado);

        } catch (PersistenciaException e) {

            throw new EditarUsuarioException("Error al intentar editar el usuario en la base de datos: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new EditarUsuarioException("Ocurrió un error inesperado al editar el usuario.", e);
        }
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) throws UsuarioInexistenteException {
          try {
            Usuario usu = usuariosDAO.buscarPorId(id);
            return UsuarioMapper.entityToDTO(usu);
            
        } catch (PersistenciaException ex) {
            throw new UsuarioInexistenteException("no existe el usuario");
            
        }
               
    }
}
