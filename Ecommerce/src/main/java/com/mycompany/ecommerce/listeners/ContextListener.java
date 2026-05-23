
package com.mycompany.ecommerce.listeners;

import entidades.Producto;
import entidades.Usuario;
import entidades.RolUsuario;
import entidades.Disponibilidad;
import implementaciones.ProductoDAO;
import implementaciones.UsuariosDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.List;
/**
 * Listener que monitorea el arranque del servidor para verificar el estado de los datos.
 * Si el catálogo está vacío, inyecta el inventario inicial deportivo y el usuario admin.
 * @author Fernando garces
 */
@WebListener 
public class ContextListener implements ServletContextListener {

    private final ProductoDAO productoDAO  = new ProductoDAO();
    private final UsuariosDAO usuariosDAO  = new UsuariosDAO();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=== SportsZone: verificando datos iniciales ===");
        try {
            // --- administrador por defecto ---
            Usuario adminExistente = usuariosDAO.buscarPorCorreo("admin@sports.com");
            if (adminExistente == null) {
                System.out.println("Creando administrador inicial...");
                Usuario admin = new Usuario();
                admin.setNombre("Fernando Garcés");
                admin.setCorreo("admin@sports.com");
                // La contraseña en texto plano es "admin123"
                // el DAO la hashea con SHA-256 antes de persistirla
                admin.setContrasenia("admin123");
                admin.setTelefono("6441234567");
                admin.setDireccion("Ciudad Obregón, Sonora");
                admin.setEsActivo(true);
                admin.setRol(RolUsuario.ADMINISTRADOR);
                usuariosDAO.registrarUsuario(admin);
                System.out.println("Administrador creado. correo: admin@sports.com  pass: admin123");
            }

            // --- catalogo deportivo inicial ---
            List<Producto> listaActual = productoDAO.obtenerProductos();
            if (listaActual == null || listaActual.isEmpty()) {
                System.out.println("Catalogo vacío. Insertando productos de ejemplo...");

                productoDAO.agregarProducto(new Producto(
                    "Tenis Running Volt Pro", 2499.00, 37,
                    "Calzado ligero con amortiguación optimizada para asfalto y entrenamientos de alta intensidad.",
                    Disponibilidad.DISPONIBLE, "prod-shoes.jpg", "Suela goma, Malla Pro-Fit, 240 g"));

                productoDAO.agregarProducto(new Producto(
                    "Jersey Fútbol Pro-Fit", 1199.00, 50,
                    "Playera transpirable con tecnología de secado rápido para máximo rendimiento.",
                    Disponibilidad.DISPONIBLE, "prod-jersey.jpg", "100% Poliéster, Ajuste atlético"));

                productoDAO.agregarProducto(new Producto(
                    "Balón Baloncesto Official", 850.00, 25,
                    "Balón reglamentario con grip texturizado para duela interior y exterior.",
                    Disponibilidad.DISPONIBLE, "prod-ball.jpg", "Tamaño No. 7, Composite"));

                productoDAO.agregarProducto(new Producto(
                    "Smartwatch Endurance Pro", 4550.00, 15,
                    "Monitor cardíaco y GPS integrado para llevar tu entrenamiento al siguiente nivel.",
                    Disponibilidad.DISPONIBLE, "prod-smartwatch.jpg", "Batería 7 días, 50m resistencia al agua"));

                System.out.println("Catálogo inicial cargado correctamente.");
            }

        } catch (Exception e) {
            // Si falla la conexion a la BD aquí solo se avisa; el servidor sigue arrancando
            System.err.println("ADVERTENCIA — No se pudieron cargar los datos iniciales: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("=== SportZone: verificación finalizada ===");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       
    }
}