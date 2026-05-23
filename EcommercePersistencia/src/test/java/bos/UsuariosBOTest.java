/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bos;

import dtos.RolUsuarioDTO;
import dtos.UsuarioDTO;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Fernando Garces 
 */
public class UsuariosBOTest {
    
    public UsuariosBOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
//
    /**
     * Test of iniciarSesion method, of class UsuariosBO.
     */
//    @Test
//    public void testIniciarSesion() throws Exception {
//        System.out.println("iniciarSesion");
//        String correo = "zamudio@potros.com";
//        String contrasenia = "admin123";
//        UsuariosBO instance = new UsuariosBO();
//        UsuarioDTO expResult = instance.buscarPorId(1L);
//        UsuarioDTO result = instance.iniciarSesion(correo, contrasenia);
//        assertEquals(expResult.getContrasenia(), result.getContrasenia());
//
//    }

//    /**
//     * Test of registrarUsuario method, of class UsuariosBO.
//     */
//    @Test
//    public void testRegistrarUsuarioOK() throws Exception {
//        UsuariosBO usuarioBO = new UsuariosBO();
//        UsuarioDTO usuario = new UsuarioDTO("Pedro Zamudio", 
//                "Real del arco #208", 
//                "0123456789", 
//                "zamudio@potros.com", 
//                "admin123", 
//                Boolean.TRUE, 
//                RolUsuarioDTO.ADMINISTRADOR);
//        
//        
//        UsuarioDTO usuarioRegistar = usuarioBO.registrarUsuario(usuario);
//        
//        assertEquals(usuario.getNombre(), usuarioRegistar.getNombre());
//        assertEquals(usuario.getCorreo(), usuarioRegistar.getCorreo());
//        assertEquals(usuario.getTelefono(), usuarioRegistar.getTelefono());
//    }
//
//    /**
//     * Test of mostrarUsuarios method, of class UsuariosBO.
//     */
//    @Test
//    public void testMostrarUsuarios() throws Exception {
//        System.out.println("mostrarUsuarios");
//        UsuariosBO instance = new UsuariosBO();
//        List<UsuarioDTO> expResult = null;
//        List<UsuarioDTO> result = instance.mostrarUsuarios();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of eliminarUsuario method, of class UsuariosBO.
//     */
//    @Test
//    public void testEliminarUsuario() throws Exception {
//        System.out.println("eliminarUsuario");
//        Long idUsuario = null;
//        UsuariosBO instance = new UsuariosBO();
//        instance.eliminarUsuario(idUsuario);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of desactivarUsuario method, of class UsuariosBO.
//     */
//    @Test
//    public void testDesactivarUsuario() throws Exception {
//        System.out.println("desactivarUsuario");
//        Long idUsuario = null;
//        UsuariosBO instance = new UsuariosBO();
//        instance.desactivarUsuario(idUsuario);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of activarUsuario method, of class UsuariosBO.
//     */
//    @Test
//    public void testActivarUsuario() throws Exception {
//        System.out.println("activarUsuario");
//        Long idUsuario = null;
//        UsuariosBO instance = new UsuariosBO();
//        instance.activarUsuario(idUsuario);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of editarUsuario method, of class UsuariosBO.
//     */
//    @Test
//    public void testEditarUsuario() throws Exception {
//        System.out.println("editarUsuario");
//        UsuarioDTO usuarioEditado = null;
//        UsuariosBO instance = new UsuariosBO();
//        UsuarioDTO expResult = null;
//        UsuarioDTO result = instance.editarUsuario(usuarioEditado);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of buscarPorId method, of class UsuariosBO.
//     */
//    @Test
//    public void testBuscarPorId() throws Exception {
//        System.out.println("buscarPorId");
//        Long id = null;
//        UsuariosBO instance = new UsuariosBO();
//        UsuarioDTO expResult = null;
//        UsuarioDTO result = instance.buscarPorId(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
