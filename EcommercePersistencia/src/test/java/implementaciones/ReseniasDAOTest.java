///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package implementaciones;
//
//import dtos.ReseñaDTO;
//import dtos.RolUsuarioDTO;
//import dtos.UsuarioDTO;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// *
// * @author
// */
//public class ReseniasDAOTest {
//    
//    public ReseniasDAOTest() {
//    }
//
//
////    @Test
////    public void testAgregarResenia() throws Exception {
////        System.out.println("agregarResenia");
////
////        Long idProductoExistente = 1L; 
////
////        UsuarioDTO usuario = new UsuarioDTO(
////            1L,                          // id
////            "Juan Pérez",                // nombre
////            "Av. Reforma #123",          // direccion
////            "6441234567",                // telefono
////            "juan.perez@example.com",    // correo
////            "secreta123",                // contrasenia
////            true,                        // esActivo
////            RolUsuarioDTO.CLIENTE                          // rol
////        );
////
////
////        // 3. CREAR EL DTO DE LA RESEÑA
////        ReseñaDTO nuevaResenia = new ReseñaDTO(
////            "Test JUnit: Producto excelente, muy recomendado.", // Comentario
////            5,                                                // Estrellas (1-5)
////            new java.util.Date(),                              // Fecha y Hora
////            idProductoExistente,                              // ID del Producto asociado
////            usuario                                        // Objeto UsuarioDTO asociado
////        );
////
////        // 4. INSTANCIAR EL DAO Y EJECUTAR
////        ReseniasDAO instance = new ReseniasDAO();
////        
////        try {
////            // Ejecutamos el método
////            instance.agregarResenia(nuevaResenia);
////            
////            // 5. VERIFICACIÓN (Manual)
////            System.out.println("Reseña agregada exitosamente.");
////            // Si el método no lanza excepción, la prueba pasa.
////            // Opcional: Podrías llamar a instance.obtenerResenias() para verificar que la lista creció.
////            
////        } catch (Exception e) {
////            // Si algo falla (ej. IDs no existen), la prueba fallará y mostrará el error
////            e.printStackTrace();
////            fail("Falló al agregar la reseña: " + e.getMessage());
////        }
////    }
//    
//    
//}
