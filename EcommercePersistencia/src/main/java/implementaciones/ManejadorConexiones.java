package implementaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManejadorConexiones {

    // Crea una instancia de EntityManagerFactory utilizando la unidad de persistencia definida en persistence.xml
    // Para que no se creen varias instancias de EntityManagerFactory, se declara como static final
    
    private static final EntityManagerFactory emFactory =
            Persistence.createEntityManagerFactory(
                    "com.mycompany_ecommercePersistencia_jar_1.0-SNAPSHOTPU");

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    // MAIN PARA MAPEAR LAS TABLAS EN MYSQL
    public static void main(String[] args) {
        System.out.println("Iniciando JPA...");

        EntityManager em = ManejadorConexiones.getEntityManager();

        //Iniciamos una transacción para que se creen las tablas en la base de datos
        em.getTransaction().begin();
        em.getTransaction().commit();
        //Y cerramos el EntityManager

        em.close();
        // Se cierra 

        System.out.println("Base construida correctamente, eso es todo");
    }
}
