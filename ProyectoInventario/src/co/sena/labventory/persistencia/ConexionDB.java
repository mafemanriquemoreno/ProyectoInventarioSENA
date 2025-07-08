package co.sena.labventory.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase se encarga de gestionar la conexión con la base de datos PostgreSQL.
 * Utiliza el patrón Singleton para asegurar una única instancia de la conexión.
 */
public class ConexionDB {

    // 1. Atributos para la conexión
    // URL de conexión: jdbc:postgresql://<servidor>:<puerto>/<base_de_datos>
    private static final String URL = "jdbc:postgresql://localhost:5432/labventory_db";
    private static final String USUARIO = "postgres"; // El usuario por defecto de PostgreSQL
    private static final String CONTRASENA = "0000";

    private static Connection conexion;

    // 2. Método para obtener la conexión
    public static Connection obtenerConexion() {
        try {
            // Preguntamos si la conexión no existe O si existe pero ya está cerrada.
            if (conexion == null || conexion.isClosed()) {
                // Si es así, la creamos de nuevo.
                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                System.out.println("¡Conexión a la base de datos abierta/reabierta con éxito!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de PostgreSQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }
        return conexion;
    }

    // 3. Método para cerrar la conexión
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null; // Importante para poder reabrir la conexión si es necesario
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}