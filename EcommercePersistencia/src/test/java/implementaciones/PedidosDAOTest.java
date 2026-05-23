/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package implementaciones;

import entidades.EstadoPedido;
import entidades.MetodoPago;
import entidades.Pedido;
import entidades.TipoMetodoPago;
import entidades.Usuario;
import exception.PersistenciaException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 
 */
public class PedidosDAOTest {
    
//    PedidosDAO dao = new PedidosDAO();
//    public PedidosDAOTest() {
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
//
//    /**
//     * Test of cambiarEstadoPedido method, of class PedidosDAO.
//     */
//    @Test
//    public void testCambiarEstadoPedido() throws Exception {
//    }
//
//    /**
//     * Test of obtenerPedidoIndividual method, of class PedidosDAO.
//     */
//    @Test
//    public void testObtenerPedidoIndividual() throws Exception {
//    }
//
//    /**
//     * Test of obtenerTodosPedidos method, of class PedidosDAO.
//     */
//    @Test
//    public void testObtenerTodosPedidos() throws Exception {
//    }
//
//    /**
//     * Test of agregarPedido method, of class PedidosDAO.
//     */
//    @Test
//    public void testAgregarPedido() throws Exception {
//        Usuario usuario = new Usuario();
//        usuario.setId(1l);
//        Date fecha = new Date();
//        MetodoPago pago = new MetodoPago(2500.0, LocalDateTime.now(), TipoMetodoPago.TARJETA);
//        Pedido pedido =  new Pedido("MX2fa0456", EstadoPedido.ENVIADO, 2500.0, fecha, "Calle paris cerca del six #3233", usuario, pago);
//        
//        Pedido pedidoBD = dao.agregarPedido(pedido);
//        assertEquals(pedido.getNumeroPedido(), pedidoBD.getNumeroPedido());
//    }
//
//    /**
//     * Test of cambiarEstadoPedido method, of class PedidosDAO.
//     */
//    @Test
//        void testObtenerTodosPedidos_DebeRegresarDos() throws PersistenciaException {
//            PedidosDAO pedidosDAO = new PedidosDAO(); 
//      
//            List<Pedido> pedidos = pedidosDAO.obtenerTodosPedidos();
//
//            assertNotNull(pedidos, "La lista de pedidos no debería ser nula.");
//
//            assertEquals(2, pedidos.size(), "La lista debería contener 2 pedidos (según los inserts de prueba).");
//    }
//        
//   @Test
//    void testTraerUnPedido() throws PersistenciaException{
//        PedidosDAO pedidosDAO = new PedidosDAO(); 
//        Pedido p = pedidosDAO.obtenerPedidoIndividual(1L);
//        assertNotNull(p);
//    }
//    
//    @Test
//    public void cambiarEstadoPedido() throws PersistenciaException {
//        PedidosDAO dao = new PedidosDAO();
//        Long idPedido = 1L;
//
//        dao.cambiarEstadoPedido(idPedido, EstadoPedido.ENTREGADO);
//
//        Pedido pedidoActualizado = dao.obtenerPedidoIndividual(idPedido);
//
//        assertEquals(EstadoPedido.ENTREGADO, pedidoActualizado.getEstado(),
//            "El estado del pedido debería haberse actualizado.");
//    }
//    
//    @Test
//    public void buscarPedidoUsuario() throws PersistenciaException{
//        PedidosDAO dao = new PedidosDAO();
//        Long idUsuario = 1L;
//        
//        List<Pedido> pedidos = dao.obtenerPedidosPorUsuario(idUsuario);
//        
//        assertNotNull(pedidos);
//        assertEquals(2, pedidos.size());
//    }
    
}
