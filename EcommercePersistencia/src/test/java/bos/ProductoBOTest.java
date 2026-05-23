/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bos;

import dtos.ProductoDTO;
import dtos.ReseñaDTO;
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
public class ProductoBOTest {
    
    public ProductoBOTest() {
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

//    /**
//     * Test of eliminarProducto method, of class ProductoBO.
//     */
//    @Test
//    public void testEliminarProducto() throws Exception {
//        System.out.println("eliminarProducto");
//        Long id = null;
//        ProductoBO instance = new ProductoBO();
//        boolean expResult = false;
//        boolean result = instance.eliminarProducto(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of agregarProducto method, of class ProductoBO.
//     */
//    @Test
//    public void testAgregarProducto() throws Exception {
//        System.out.println("agregarProducto");
//        ProductoDTO nuevoProducto = null;
//        ProductoBO instance = new ProductoBO();
//        ProductoDTO expResult = null;
//        ProductoDTO result = instance.agregarProducto(nuevoProducto);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of editarProducto method, of class ProductoBO.
//     */
//    @Test
//    public void testEditarProducto() throws Exception {
//        System.out.println("editarProducto");
//        Long id = null;
//        ProductoDTO nuevoProducto = null;
//        ProductoBO instance = new ProductoBO();
//        instance.editarProducto(id, nuevoProducto);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obtenerProductos method, of class ProductoBO.
//     */
//    @Test
//    public void testObtenerProductos() throws Exception {
//        System.out.println("obtenerProductos");
//        ProductoBO instance = new ProductoBO();
//        List<ProductoDTO> expResult = null;
//        List<ProductoDTO> result = instance.obtenerProductos();
//        System.out.println(result);
//    }
//
//    /**
//     * Test of obtenerProductoPorId method, of class ProductoBO.
//     */
//    @Test
//    public void testObtenerProductoPorId() throws Exception {
//        System.out.println("obtenerProductoPorId");
//        Long id = null;
//        ProductoBO instance = new ProductoBO();
//        ProductoDTO expResult = null;
//        ProductoDTO result = instance.obtenerProductoPorId(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of buscarProductos method, of class ProductoBO.
//     */
//    @Test
//    public void testBuscarProductos() throws Exception {
//        System.out.println("buscarProductos");
//        String nombre = "";
//        Double precioMin = null;
//        Double precioMax = null;
//        ProductoBO instance = new ProductoBO();
//        List<ProductoDTO> expResult = null;
//        List<ProductoDTO> result = instance.buscarProductos(nombre, precioMin, precioMax);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obtenerReseñas method, of class ProductoBO.
//     */
//    @Test
//    public void testObtenerReseñas() throws Exception {
//        System.out.println("obtenerRese\u00f1as");
//        Long id = null;
//        ProductoBO instance = new ProductoBO();
//        List<ReseñaDTO> expResult = null;
//        List<ReseñaDTO> result = instance.obtenerReseñas(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of eliminarProducto method, of class ProductoBO.
//     */
//    @Test
//    public void testEliminarProducto() throws Exception {
//        System.out.println("eliminarProducto");
//        Long id = null;
//        ProductoBO instance = new ProductoBO();
//        boolean expResult = false;
//        boolean result = instance.eliminarProducto(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of agregarProducto method, of class ProductoBO.
//     */
//    @Test
//    public void testAgregarProducto() throws Exception {
//        System.out.println("agregarProducto");
//        ProductoDTO nuevoProducto = null;
//        ProductoBO instance = new ProductoBO();
//        ProductoDTO expResult = null;
//        ProductoDTO result = instance.agregarProducto(nuevoProducto);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of editarProducto method, of class ProductoBO.
//     */
//    @Test
//    public void testEditarProducto() throws Exception {
//        System.out.println("editarProducto");
//        Long id = null;
//        ProductoDTO nuevoProducto = null;
//        ProductoBO instance = new ProductoBO();
//        instance.editarProducto(id, nuevoProducto);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obtenerProductos method, of class ProductoBO.
//     */
//    @Test
//    public void testObtenerProductos() throws Exception {
//        System.out.println("obtenerProductos");
//        ProductoBO instance = new ProductoBO();
//        List<ProductoDTO> expResult = null;
//        List<ProductoDTO> result = instance.obtenerProductos();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obtenerProductoPorId method, of class ProductoBO.
//     */
//    @Test
//    public void testObtenerProductoPorId() throws Exception {
//        System.out.println("obtenerProductoPorId");
//        Long id = null;
//        ProductoBO instance = new ProductoBO();
//        ProductoDTO expResult = null;
//        ProductoDTO result = instance.obtenerProductoPorId(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of buscarProductos method, of class ProductoBO.
//     */
//    @Test
//    public void testBuscarProductos() throws Exception {
//        System.out.println("buscarProductos");
//        String nombre = "";
//        Double precioMin = null;
//        Double precioMax = null;
//        ProductoBO instance = new ProductoBO();
//        List<ProductoDTO> expResult = null;
//        List<ProductoDTO> result = instance.buscarProductos(nombre, precioMin, precioMax);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obtenerReseñas method, of class ProductoBO.
//     */
//    @Test
//    public void testObtenerReseñas() throws Exception {
//        System.out.println("obtenerRese\u00f1as");
//        Long id = null;
//        ProductoBO instance = new ProductoBO();
//        List<ReseñaDTO> expResult = null;
//        List<ReseñaDTO> result = instance.obtenerReseñas(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
