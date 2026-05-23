///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package implementaciones;
//
//import entidades.Carrito;
//import entidades.Categoria;
//import entidades.Producto;
//import interfaces.ICarritosDAO;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// *
// * @author
// */
//public class CarritosDAOTest {
//    
//    public CarritosDAOTest() {
//    }
//
//    /**
//     * Test of obtenerCarritos method, of class CarritosDAO.
//     */
//    @Test
//    public void testObtenerCarritos() throws Exception {
//        ICarritosDAO carritos = new CarritosDAO();
//        List<Carrito> carritosLista = carritos.obtenerCarritos();
//        for (Carrito carrito : carritosLista) {
//            System.out.println(carrito.getUsuario().getId());
//            
//        }
//        
//    }
//
//    /**
//     * Test of obtenerCarritoUsuario method, of class CarritosDAO.
//     */
//    @Test
//    public void testObtenerCarritoUsuario() throws Exception {
//        ICarritosDAO carritos = new CarritosDAO();
//        Carrito carrito = carritos.obtenerCarritoUsuario(1l);
//        System.out.println(carrito);
//    }
//    
//    
//    @Test 
//    public void testAgregarCarrito() throws Exception {
//
//    }
//    
//}
