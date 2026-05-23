/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.RolUsuarioDTO;
import entidades.RolUsuario;

/**
 *
 * @author Fernando Garces 
 */
public class RolUsuarioMapper {
     public static RolUsuarioDTO toDTO(RolUsuario rol) {
        if (rol == null) return null;
        return RolUsuarioDTO.valueOf(rol.name());
    }

    public static RolUsuario toEntity(RolUsuarioDTO dto) {
        if (dto == null) return null;
        return RolUsuario.valueOf(dto.name());
    }
}
