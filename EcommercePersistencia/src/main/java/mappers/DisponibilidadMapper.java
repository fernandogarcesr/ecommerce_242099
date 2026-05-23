package mappers;
 
import dtos.DisponibilidadDTO;
import entidades.Disponibilidad;
 
/**
 * Mapper entre el enum de entidad Disponibilidad y su DTO
 */
public class DisponibilidadMapper {
 
    public static DisponibilidadDTO toDTO(Disponibilidad disponibilidad) {
        if (disponibilidad == null) return null;
        return DisponibilidadDTO.valueOf(disponibilidad.name());
    }
 
    public static Disponibilidad toEntity(DisponibilidadDTO disponibilidadDTO) {
        if (disponibilidadDTO == null) return null;
        return Disponibilidad.valueOf(disponibilidadDTO.name());
    }
}